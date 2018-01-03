//: guru.springframework.domain.Recipe.java

package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@ToString(of = {"description"})
@EqualsAndHashCode(of = {"description"})
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    /*
     * Using Byte[] other than byte[]: this is a hibernate recommendation
     */
    @Lob
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

    public void removeIngredient(Long ingredientId) {

        Set<Ingredient> removed = this.ingredients.stream()
                .filter(i -> i.getId().equals(ingredientId))
                .collect(Collectors.toSet());

        // Very important: Let Hibernate remove the relationship between
        // the recipe and the deleted ingredient
        removed.stream().forEach(i -> i.setRecipe(null));

        this.ingredients.removeAll(removed);
    }

}///~