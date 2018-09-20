//: guru.springframework.services.RecipeReactiveService.java


package guru.springframework.services;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.IRecipeRepository;
import guru.springframework.repositories.reactive.IRecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class RecipeReactiveService implements IRecipeReactiveService {

    private final IRecipeReactiveRepository recipeReactiveRepository;

    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    public RecipeReactiveService(IRecipeReactiveRepository recipeReactiveRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {

        this.recipeReactiveRepository = recipeReactiveRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Flux<Recipe> getAllRecipes() {

        this.log.info(">>>>>>> Retrieving all recipes from repository ... ...");
        return this.recipeReactiveRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {
        return this.recipeReactiveRepository.findById(id);
    }

    @Override
    public Mono<RecipeCommand> findCommandById(String id) {

        return this.recipeReactiveRepository.findById(id).map(r -> {
            RecipeCommand rc = this.recipeToRecipeCommand.convert(r);
            rc.getIngredients().forEach(i -> i.setRecipeId(rc.getId()));
            return rc;
        });
    }

    @Override
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand recipeCommand) {

        return this.recipeReactiveRepository.save(this.recipeCommandToRecipe.convert(recipeCommand)).map(this.recipeToRecipeCommand::convert);
    }

    @Override
    public Mono<Boolean> existById(String id) {
        return this.recipeReactiveRepository.existsById(id);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        this.recipeReactiveRepository.deleteById(id).block();
        return Mono.empty();
    }

}///~