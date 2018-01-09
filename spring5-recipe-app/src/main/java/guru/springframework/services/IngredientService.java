//: guru.springframework.services.IngredientService.java


package guru.springframework.services;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.IRecipeRepository;
import guru.springframework.repositories.IUnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Service
public class IngredientService implements IIngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IRecipeRepository recipeRepository;
    private final IUnitOfMeasureRepository uomRepository;

    @Autowired
    public IngredientService(
            IngredientToIngredientCommand ingredientToIngredientCommand,
            IngredientCommandToIngredient ingredientCommandToIngredient,
            IRecipeRepository recipeRepository,
            IUnitOfMeasureRepository uomRepository) {

        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
    }

    @Transactional
    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(String recipeId,
                                                           String ingredientId) {

        Optional<Recipe> recipeOptional = this.recipeRepository.findById(
                recipeId);

        if (!recipeOptional.isPresent()) {
            String errMsg = ">>>>>>> Recipe not found by id:" + recipeId;
            this.log.debug(errMsg);
            throw new RuntimeException(errMsg);
        }

        Recipe recipe = recipeOptional.get();

        Set<Ingredient> ingredients = recipe.getIngredients();

        Set<IngredientCommand> ingredientCommands = recipe.getIngredients()
                .stream()
                .filter(i -> i.getId().equals(ingredientId))
                .map(i -> ingredientToIngredientCommand.convert(i))
                .collect(Collectors.toSet());


        if (ingredientCommands.size() == 0) {
            String errMsg = ">>>>>>> Ingredient not found by id:" + ingredientId;
            this.log.debug(errMsg);
            throw new RuntimeException(errMsg);
        }

        return ingredientCommands.iterator().next();
    }

    @Override
    public IngredientCommand saveIngredientCommand(
            final IngredientCommand ingredientCommand) {

        IngredientCommand savedIngredientCommand = null;

        if (ingredientCommand == null) {
            return savedIngredientCommand;
        }

        String recipeId = ingredientCommand.getRecipeId();
        String ingredientId = ingredientCommand.getId();

        // Find recipe
        Optional<Recipe> recipoeOpt = this.recipeRepository.findById(recipeId);

        if (!recipoeOpt.isPresent()) {
            savedIngredientCommand = new IngredientCommand();
            log.error("The recipe does not exist.");
        } else {

            Recipe foundRecipe = recipoeOpt.get();

            Optional<Ingredient> ingredientOpt = foundRecipe.getIngredients()
                    .stream()
                    .filter(i -> i.getId().equals(ingredientId))
                    .findFirst();

            if (ingredientOpt.isPresent()) {

                Ingredient foundIngredient = ingredientOpt.get();
                foundIngredient.setDescription(ingredientCommand.
                        getDescription());
                foundIngredient.setAmount(ingredientCommand.getAmount());

                String uomId = ingredientCommand.getUomc().getId();

                Optional<UnitOfMeasure> foundUom = this.uomRepository.findById(
                        uomId);

                foundIngredient.setUom(foundUom.orElseThrow(
                        () -> new RuntimeException("UOM not found!")));

            } else {
                String newDesc = ingredientCommand.getDescription();
                if (!foundRecipe.getIngredients().stream()
                        .filter(i -> i.getDescription().equals(newDesc))
                        .findFirst()
                        .isPresent()) {
                    foundRecipe.addIngredient(this.ingredientCommandToIngredient.
                            convert(ingredientCommand));
                } else {
                    throw new RuntimeException(
                            "Ingredient already exists in recipe: "
                                    + foundRecipe.getId());
                }
            }

            Recipe savedRecipe = this.recipeRepository.save(foundRecipe);

            Ingredient savedIngredient = savedRecipe.getIngredients()
                    .stream()
                    .filter(i -> i.getDescription().equals(
                            ingredientCommand.getDescription()))
                    .findFirst()
                    .get();

            savedIngredientCommand = this.ingredientToIngredientCommand.convert(
                    savedIngredient);
            savedIngredientCommand.setRecipeId(savedRecipe.getId());

        }// End of if (!recipoeOpt.isPresent())

        return savedIngredientCommand;
    }

    @Transactional
    @Override
    public void deleteIngredientCommand(String recipeId, String ingredientId) {
        Optional<Recipe> recipeOpt = this.recipeRepository.findById(recipeId);
        Recipe foundRecipe = recipeOpt.orElseThrow(
                () -> new RuntimeException("Recipe not found!:"));
        foundRecipe.removeIngredient(ingredientId);
        Recipe deleted = this.recipeRepository.save(foundRecipe);
        Set<Ingredient> fi = deleted.getIngredients().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .collect(Collectors.toSet());
        log.info("Deleted ? " + (fi.size() == 0));
    }

}///~