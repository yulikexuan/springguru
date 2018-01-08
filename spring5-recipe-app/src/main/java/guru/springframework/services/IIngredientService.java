//: guru.springframework.services.IIngredientService.java


package guru.springframework.services;


import guru.springframework.commands.IngredientCommand;


public interface IIngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(String recipeId,
                                                    String ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    void deleteIngredientCommand(String recipeId, String ingredientId);
}///:~