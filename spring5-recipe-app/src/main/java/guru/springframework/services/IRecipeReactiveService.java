//: guru.springframework.services.IRecipeReactiveService.java


package guru.springframework.services;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IRecipeReactiveService {

    Flux<Recipe> getAllRecipes();

    Mono<Recipe> findById(String id);

    Mono<RecipeCommand> findCommandById(String id);

    Mono<RecipeCommand> saveRecipeCommand(RecipeCommand recipeCommand);

    Mono<Boolean> existById(String id);

    Mono<Void> deleteById(String id);

}///~