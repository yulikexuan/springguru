//: guru.springframework.services.IngredientReactiveService.java


package guru.springframework.services;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.reactive.IRecipeReactiveRepository;
import guru.springframework.repositories.reactive.IUnitOfMeasureReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Service
public class IngredientReactiveService implements IIngredientReactiveService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    private final IRecipeReactiveRepository recipeRepository;
    private final IUnitOfMeasureReactiveRepository uomRepository;

    @Autowired
    public IngredientReactiveService(IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, IRecipeReactiveRepository recipeRepository, IUnitOfMeasureReactiveRepository uomRepository) {

        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
    }

    @Override
    public Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {

        Recipe r = this.recipeRepository.findById(recipeId).block();

        /*
         * Mono::flatMapIterable
         * - Transform the item emitted by this Mono into Iterable
         * - then forward its elements into the returned Flux
         * - The prefetch argument allows to give an arbitrary prefetch size to
         *   the inner Iterable
         *
         * Flux::single
         * - Expect and emit a single item from this Flux source or signal
         *   NoSuchElementException for an empty source, or
         *   IndexOutOfBoundsException for a source with more than one element
         */
        Mono<IngredientCommand> icm = this.recipeRepository.findById(recipeId).flatMapIterable(Recipe::getIngredients).filter(i -> i.getId().equalsIgnoreCase(ingredientId)).single().map(i -> {
            IngredientCommand command = this.ingredientToIngredientCommand.convert(i);
            command.setRecipeId(recipeId);
            return command;
        });

        return icm;
    }

    @Override
    public Mono<IngredientCommand> saveIngredientCommand(final IngredientCommand ingredientCommand) {

        IngredientCommand savedIngredientCommand = null;

        if (ingredientCommand == null) {
            return Mono.empty();
        }

        String recipeId = ingredientCommand.getRecipeId();
        String ingredientId = ingredientCommand.getId();

        // Find recipe
        Recipe recipe = this.recipeRepository.findById(recipeId).block();

        if (recipe == null) {
            savedIngredientCommand = new IngredientCommand();
            log.error("The recipe does not exist.");
        } else {

            Optional<Ingredient> ingredientOpt = recipe.getIngredients().stream().filter(i -> i.getId().equals(ingredientId)).findFirst();

            if (ingredientOpt.isPresent()) {
                Ingredient foundIngredient = ingredientOpt.get();
                foundIngredient.setDescription(ingredientCommand.
                        getDescription());
                foundIngredient.setAmount(ingredientCommand.getAmount());
                String uomId = ingredientCommand.getUomc().getId();
                UnitOfMeasure foundUom = this.uomRepository.findById(uomId).block();
                if (foundUom == null) {
                    throw new RuntimeException("UOM not found!");
                } else {
                    foundIngredient.setUom(foundUom);
                }
            } else {
                String newDesc = ingredientCommand.getDescription();
                if (!recipe.getIngredients().stream().filter(i -> i.getDescription().equals(newDesc)).findFirst().isPresent()) {
                    recipe.addIngredient(this.ingredientCommandToIngredient.
                            convert(ingredientCommand));
                } else {
                    throw new RuntimeException("Ingredient already exists in recipe: " + recipe.getId());
                }
            }

            Recipe savedRecipe = this.recipeRepository.save(recipe).block();

            Ingredient savedIngredient = savedRecipe.getIngredients().stream().filter(i -> i.getDescription().equals(ingredientCommand.getDescription())).findFirst().get();

            savedIngredientCommand = this.ingredientToIngredientCommand.convert(savedIngredient);
            savedIngredientCommand.setRecipeId(savedRecipe.getId());

        }// End of if (!recipoeOpt.isPresent())

        return Mono.just(savedIngredientCommand);

    }// End of saveIngredientCommand

    @Override
    public Mono<Void> deleteIngredientCommand(String recipeId, String ingredientId) {

        Recipe recipe = this.recipeRepository.findById(recipeId).block();

        if (recipe == null) {
            throw new RuntimeException("Recipe not found!:");
        }
        recipe.removeIngredient(ingredientId);

        recipe = this.recipeRepository.save(recipe).block();

        log.info(">>>>>>> Ingredient is deleted ? " + (recipe.getIngredients().stream().filter(i -> i.getId().equals(ingredientId)).count() == 0));

        return Mono.empty();
    }

}///~