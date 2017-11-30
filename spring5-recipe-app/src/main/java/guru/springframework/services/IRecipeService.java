//: guru.springframework.services.IRecipeService.java


package guru.springframework.services;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Optional;
import java.util.Set;


public interface IRecipeService {

    Set<Recipe> getAllRecipes();
    Optional<Recipe> findById(Long id);
    RecipeCommand findCommandById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    boolean existById(Long id);

    void deleteById(Long id);

}///~