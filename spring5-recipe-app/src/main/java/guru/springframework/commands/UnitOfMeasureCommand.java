//: guru.springframework.commands.UnitOfMeasureCommand.java


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
public class UnitOfMeasureCommand {

    private Long id;
    private String description;

    private UnitOfMeasureCommand(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public static final class Builder {

        private Long id;
        private String description;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public UnitOfMeasureCommand createUnitOfMeasureCommand() {
            return new UnitOfMeasureCommand(this.id, this.description);
        }
    }

}///~