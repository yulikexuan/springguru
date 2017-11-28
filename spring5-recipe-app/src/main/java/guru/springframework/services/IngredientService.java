//: guru.springframework.services.IngredientService.java


package guru.springframework.services;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IRecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class IngredientService implements IIngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IRecipeRepository recipeRepository;

    @Autowired
    public IngredientService(
            IngredientToIngredientCommand ingredientToIngredientCommand,
            IRecipeRepository recipeRepository) {

        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,
                                                           Long ingredientId) {

        Optional<Recipe> recipeOptional = this.recipeRepository.findById(
                recipeId);

        if (!recipeOptional.isPresent()) {
            String errMsg = ">>>>>>> Recipe not found by id:" + recipeId;
            this.log.debug(errMsg);
            throw new RuntimeException(errMsg);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientOptioanl = recipe.getIngredients()
                .stream()
                .filter(i -> i.getId().equals(ingredientId))
                .map(i -> ingredientToIngredientCommand.convert(i))
                .findFirst();

        if (!ingredientOptioanl.isPresent()) {
            String errMsg = ">>>>>>> Ingredient not found by id:" + ingredientId;
            this.log.debug(errMsg);
            throw new RuntimeException(errMsg);
        }

        return ingredientOptioanl.get();
    }

}///~