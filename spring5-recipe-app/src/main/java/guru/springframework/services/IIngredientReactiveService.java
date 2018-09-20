//: guru.springframework.services.IIngredientReactiveService.java


package guru.springframework.services;


import guru.springframework.commands.IngredientCommand;
import reactor.core.publisher.Mono;


public interface IIngredientReactiveService {
    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand ingredientCommand);

    Mono<Void> deleteIngredientCommand(String recipeId, String ingredientId);
}///:~