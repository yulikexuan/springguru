//: guru.springframework.commands.RecipeCommand.java


package guru.springframework.commands;


import guru.springframework.domain.Difficulty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "description")
@ToString(of = "description")
public class RecipeCommand {

    private Long id;
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

    private RecipeCommand(Long id, String description, Integer prepTime,
                          Integer cookTime, Integer servings, String source,
                          String url, String directions, Difficulty difficulty,
                          Set<IngredientCommand> ingredients,
                          NotesCommand notes, Set<CategoryCommand> categories,
                          Byte[] image) {
        this.id = id;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.directions = directions;
        this.difficulty = difficulty;
        this.ingredients = ingredients;
        this.notes = notes;
        this.categories = categories;
        this.image = image;
    }

    public static final class Builder {

        private Long id;
        private String description;
        private Integer prepTime;
        private Integer cookTime;
        private Integer servings;
        private String source;
        private String url;
        private String directions;
        private Difficulty difficulty;
        private Set<IngredientCommand> ingredients;
        private NotesCommand notes;
        private Set<CategoryCommand> categories;
        private Byte[] image;

        public Builder setId(Long id) {
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
            return new RecipeCommand(id, description, prepTime, cookTime,
                    servings, source, url, directions, difficulty, ingredients,
                    notes, categories, image);
        }

    }//: End of class Builder

}///~