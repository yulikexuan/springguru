//: guru.springframework.services.IIngredientService.java


package guru.springframework.services;


import guru.springframework.commands.IngredientCommand;


public interface IIngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,
                                                    Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    void deleteIngredientCommand(Long recipeId, Long ingredientId);
}///:~