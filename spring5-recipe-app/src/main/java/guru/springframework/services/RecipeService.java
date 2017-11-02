//: guru.springframework.services.RecipeService.java


package guru.springframework.services;


import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class RecipeService implements IRecipeService {

    private final IRecipeRepository recipeRepository;

    @Autowired
    public RecipeService(IRecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getAllRecipes() {

        Set<Recipe> allRecipes = new HashSet<>();
        this.recipeRepository.findAll().iterator().forEachRemaining(
                allRecipes::add);

        return allRecipes;
    }

}///~