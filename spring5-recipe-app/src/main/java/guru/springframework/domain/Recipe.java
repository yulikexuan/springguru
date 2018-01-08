//: guru.springframework.domain.Recipe.java

package guru.springframework.domain;


import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@ToString(of = {"description"})
@EqualsAndHashCode(of = {"description"})
public class Recipe {

    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private Set<Ingredient> ingredients = new HashSet<>();
    private Notes notes;
    private Set<Category> categories = new HashSet<>();
    private Byte[] image;

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            this.notes.setRecipe(this);
        }
    }

    public Recipe addIngredient(Ingredient ingredient) {
        if (ingredient != null) {
            this.ingredients.add(ingredient);
            ingredient.setRecipe(this);
        }
        return this;
    }

    public void removeIngredient(String ingredientId) {

        Set<Ingredient> removed = this.ingredients.stream()
                .filter(i -> i.getId().equals(ingredientId))
                .collect(Collectors.toSet());

        // Very important: Let Hibernate remove the relationship between
        // the recipe and the deleted ingredient
        removed.stream().forEach(i -> i.setRecipe(null));

        this.ingredients.removeAll(removed);
    }

}///~