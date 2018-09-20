//: guru.springframework.services.IRecipeService.java


package guru.springframework.services;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Optional;
import java.util.Set;


public interface IRecipeService {

    Set<Recipe> getAllRecipes();

    Optional<Recipe> findById(String id);

    RecipeCommand findCommandById(String id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    boolean existById(String id);

    void deleteById(String id);

}///~