//: guru.springframework.converters.RecipeCommandToRecipe.java


package guru.springframework.converters;


import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;

    public RecipeCommandToRecipe(
            NotesCommandToNotes notesConverter,
            CategoryCommandToCategory categoryConverter,
            IngredientCommandToIngredient ingredientConverter) {

        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand command) {

        if (command == null) {
            return null;
        }

        final Recipe recipe = new Recipe();

        recipe.setId(command.getId());
        recipe.setCookTime(command.getCookTime());
        recipe.setPrepTime(command.getPrepTime());
        recipe.setDescription(command.getDescription());
        recipe.setDifficulty(command.getDifficulty());
        recipe.setDirections(command.getDirections());
        recipe.setServings(command.getServings());
        recipe.setSource(command.getSource());
        recipe.setUrl(command.getUrl());

        recipe.setNotes(this.notesConverter.convert(command.getNotes()));

        Set<CategoryCommand> categorieCommands = command.getCategories();
        Set<Category> categories = recipe.getCategories();
        if (categorieCommands != null) {
            categorieCommands.iterator().forEachRemaining(
                    c -> categories.add(this.categoryConverter.convert(c)));
        }

        Set<IngredientCommand> ingredientCommands = command.getIngredients();
        if (ingredientCommands != null) {
            ingredientCommands.iterator().forEachRemaining(
                    i -> recipe.addIngredient(this.ingredientConverter.convert(i)));
        }

        return recipe;
    }

}///~