//: guru.springframework.commands.IngredientCommand.java


package guru.springframework.commands;


import guru.springframework.domain.UnitOfMeasure;
import lombok.*;

import java.math.BigDecimal;

/*
 * command (of something)
 *     Your knowledge of something; your ability to do or use something,
 *     especially a language
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
public class IngredientCommand {

    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uomc;

    private IngredientCommand(Long id, String description, BigDecimal amount,
                              UnitOfMeasureCommand uomc) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.uomc = uomc;
    }

    public static final class Builder {

        private Long id;
        private String description;
        private BigDecimal amount;
        private UnitOfMeasureCommand uomc;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder setUomc(UnitOfMeasureCommand uomc) {
            this.uomc = uomc;
            return this;
        }

        public IngredientCommand createIngredientCommand() {
            return new IngredientCommand(this.id, this.description, this.amount,
                    this.uomc);
        }

    }//: End of IngredientCommand

}///~