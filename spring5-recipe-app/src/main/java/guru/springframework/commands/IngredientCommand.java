//: guru.springframework.commands.IngredientCommand.java


package guru.springframework.commands;


import guru.springframework.domain.UnitOfMeasure;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private String id;
    private String recipeId;

    @NotBlank
    private String description;

    @NotNull
    @Min(1)
    private BigDecimal amount;

    @NotNull
    private UnitOfMeasureCommand uomc;

    private IngredientCommand(String id, String recipeId, String description,
                              BigDecimal amount, UnitOfMeasureCommand uomc) {
        this.id = id;
        this.recipeId = recipeId;
        this.description = description;
        this.amount = amount;
        this.uomc = uomc;
    }

    public static final class Builder {

        private String id;
        private String recipeId;
        private String description;
        private BigDecimal amount;
        private UnitOfMeasureCommand uomc;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setRecipeId(String recipeId) {
            this.recipeId = recipeId;
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
            return new IngredientCommand(this.id, this.recipeId,
                    this.description, this.amount,this.uomc);
        }

    }//: End of IngredientCommand

}///~