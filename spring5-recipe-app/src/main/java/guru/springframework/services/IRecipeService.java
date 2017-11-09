//: guru.springframework.services.IRecipeService.java


package guru.springframework.services;


import guru.springframework.domain.Recipe;

import java.util.Set;


public interface IRecipeService {

    Set<Recipe> getAllRecipes();

}///~