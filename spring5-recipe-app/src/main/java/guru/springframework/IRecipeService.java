//: guru.springframework.IRecipeService.java


package guru.springframework;


import guru.springframework.domain.Recipe;

import java.util.Set;


public interface IRecipeService {

    Set<Recipe> getAllRecipes();

}///~