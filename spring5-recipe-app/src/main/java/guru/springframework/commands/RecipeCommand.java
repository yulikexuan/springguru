//: guru.springframework.commands.RecipeCommand.java


package guru.springframework.commands;


import guru.springframework.domain.Difficulty;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "description")
@ToString(of = "description")
public class RecipeCommand {

    private String id;

    /*
     * max=255:
     * Because in Recipe entity class, description is mapping to db with a
     * length of 255 characters by default and there is no any constraint,
     *
     */
    @NotBlank
    @Size(min = 4, max = 255)
    private String description;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @Min(1)
    @Max(100)
    private Integer servings;

    private String source;

    @URL
    private String url;

    @NotBlank
    private String directions;

    private Difficulty difficulty;
    private List<IngredientCommand> ingredients = new ArrayList<>();
    private NotesCommand notes;
    private List<CategoryCommand> categories = new ArrayList<>();
    private Byte[] image;

    private RecipeCommand(String id, String description, Integer prepTime, Integer cookTime, Integer servings, String source, String url, String directions, Difficulty difficulty, Set<IngredientCommand> ingredients, NotesCommand notes, Set<CategoryCommand> categories, Byte[] image) {
        this.id = id;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.directions = directions;
        this.difficulty = difficulty;
        this.ingredients.addAll(ingredients);
        this.notes = notes;
        this.categories.addAll(categories);
        this.image = image;
    }

    public static final class Builder {

        private String id;
        private String description;
        private Integer prepTime;
        private Integer cookTime;
        private Integer servings;
        private String source;
        private String url;
        private String directions;
        private Difficulty difficulty;
        private Set<IngredientCommand> ingredients = new HashSet<>();
        private NotesCommand notes;
        private Set<CategoryCommand> categories = new HashSet<>();
        private Byte[] image;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrepTime(Integer prepTime) {
            this.prepTime = prepTime;
            return this;
        }

        public Builder setCookTime(Integer cookTime) {
            this.cookTime = cookTime;
            return this;
        }

        public Builder setServings(Integer servings) {
            this.servings = servings;
            return this;
        }

        public Builder setSource(String source) {
            this.source = source;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setDirections(String directions) {
            this.directions = directions;
            return this;
        }

        public Builder setDifficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public Builder setIngredients(Set<IngredientCommand> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public Builder setNotes(NotesCommand notes) {
            this.notes = notes;
            return this;
        }

        public Builder setCategories(Set<CategoryCommand> categories) {
            this.categories = categories;
            return this;
        }

        public Builder setImage(Byte[] image) {
            this.image = image;
            return this;
        }

        public RecipeCommand createRecipeCommand() {
            return new RecipeCommand(id, description, prepTime, cookTime, servings, source, url, directions, difficulty, ingredients, notes, categories, image);
        }

    }//: End of class Builder

}///~