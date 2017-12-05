//: guru.springframework.services.RecipeService.java


package guru.springframework.services;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.IRecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Slf4j
@Service
public class RecipeService implements IRecipeService {

    private final IRecipeRepository recipeRepository;

    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    public RecipeService(IRecipeRepository recipeRepository,
                         RecipeCommandToRecipe recipeCommandToRecipe,
                         RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Set<Recipe> getAllRecipes() {

        Set<Recipe> allRecipes = new HashSet<>();

        this.log.info(">>>>>>> Retrieving all recipes from repository ... ...");
        this.recipeRepository.findAll().iterator().forEachRemaining(
                allRecipes::add);

        return allRecipes;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Optional<Recipe> findById(Long id) {
        Optional<Recipe> recipeOpt = this.recipeRepository.findById(id);
        if (!recipeOpt.isPresent()) {
            throw new NotFoundException("Recipe not found according to the id "
                    + id);
        }
        return recipeOpt;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public RecipeCommand findCommandById(Long id) {

        Optional<Recipe> recipeOpt = this.findById(id);

        if (!recipeOpt.isPresent()) {
            throw new RuntimeException("Recipe not found by id: " + id);
        }

        Recipe recipe = recipeOpt.get();

        log.info(">>>>>>> Categories of " + recipe.getDescription() + ": " +
                recipe.getCategories());

        return this.recipeToRecipeCommand.convert(recipe);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {

        Recipe recipe = this.recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = this.recipeRepository.save(recipe);

        log.info(">>>>>>> Saved recipe id is " + savedRecipe.getId());

        return this.recipeToRecipeCommand.convert(savedRecipe);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public boolean existById(Long id) {
        return this.recipeRepository.existsById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) {
        this.recipeRepository.deleteById(id);
    }

}///~