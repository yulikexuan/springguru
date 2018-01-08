//: guru.springframework.commands.CategoryCommand.java


package guru.springframework.commands;


import lombok.*;


/*
 * command (of something)
 *     Your knowledge of something; your ability to do or use something,
 *     especially a language
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "description")
@ToString(of = "description")
public class CategoryCommand {

    private String id;
    private String description;

    private CategoryCommand(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public static final class Builder {

        private String id;
        private String description;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public CategoryCommand createCategoryCommand() {
            return new CategoryCommand(id, description);
        }
    }

}///~