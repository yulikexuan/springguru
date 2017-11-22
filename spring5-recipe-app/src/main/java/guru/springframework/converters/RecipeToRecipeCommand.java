//: guru.springframework.converters.RecipeToRecipeCommand.java


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
import java.util.stream.Collectors;


@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(
            NotesToNotesCommand notesConverter,
            CategoryToCategoryCommand categoryConverter,
            IngredientToIngredientCommand ingredientConverter) {

        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {

        if (recipe == null) {
            return null;
        }

        Set<CategoryCommand> categorieCommands = recipe.getCategories()
                .stream()
                .map(this.categoryConverter::convert)
                .collect(Collectors.toSet());

        Set<IngredientCommand> ingredientCommands = recipe.getIngredients()
                .stream()
                .map(this.ingredientConverter::convert)
                .collect(Collectors.toSet());

        return new RecipeCommand.Builder()
                .setId(recipe.getId())
                .setCookTime(recipe.getCookTime())
                .setPrepTime(recipe.getPrepTime())
                .setDescription(recipe.getDescription())
                .setDifficulty(recipe.getDifficulty())
                .setDirections(recipe.getDirections())
                .setServings(recipe.getServings())
                .setSource(recipe.getSource())
                .setUrl(recipe.getUrl())
                .setNotes(this.notesConverter.convert(recipe.getNotes()))
                .setCategories(categorieCommands)
                .setIngredients(ingredientCommands)
                .createRecipeCommand();
    }

}///~