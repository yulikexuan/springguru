//: guru.springframework.domain.builders.RecipeBuilder.java


package guru.springframework.domain.builders;


import guru.springframework.domain.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component()
@Scope("prototype")
public class RecipeBuilder implements IModelBuilder<Recipe> {

    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private Set<Ingredient> ingredients;
    private Notes notes;
    private Set<Category> categories;
    private Byte[] image;

    public RecipeBuilder() {
    }

    public RecipeBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public RecipeBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public RecipeBuilder setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
        return this;
    }

    public RecipeBuilder setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    public RecipeBuilder setServings(Integer servings) {
        this.servings = servings;
        return this;
    }

    public RecipeBuilder setSource(String source) {
        this.source = source;
        return this;
    }

    public RecipeBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public RecipeBuilder setDirections(String directions) {
        this.directions = directions;
        return this;
    }

    public RecipeBuilder setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public RecipeBuilder setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public RecipeBuilder setNotes(Notes notes) {
        this.notes = notes;
        return this;
    }

    public RecipeBuilder setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public RecipeBuilder setImage(Byte[] image) {
        this.image = image;
        return this;
    }

    @Override
    public Recipe build() {

        Recipe recipe = new Recipe();

        recipe.setId(this.id);
        recipe.setDescription(this.description);
        recipe.setPrepTime(this.prepTime);
        recipe.setCookTime(this.cookTime);
        recipe.setServings(this.servings);
        recipe.setSource(this.source);
        recipe.setUrl(this.url);
        recipe.setDirections(this.directions);
        recipe.setDifficulty(this.difficulty);
        recipe.setIngredients(this.ingredients);
        recipe.setNotes(this.notes);
        recipe.setCategories(this.categories);
        recipe.setImage(this.image);

        return recipe;
    }

    @Override
    public void clear() {
        this.description = null;
        this.prepTime = null;
        this.cookTime = null;
        this.servings = null;
        this.source = null;
        this.url = null;
        this.directions = null;
        this.difficulty = null;
        this.ingredients = null;
        this.notes = null;
        this.categories = null;
        this.image = null;
    }

}///~