//: guru.springframework.domain.Ingredient.java

package guru.springframework.domain;


import lombok.*;

import java.math.BigDecimal;


@Data
@EqualsAndHashCode(of = {"id", "description"})
@ToString(of = {"id", "description", "recipe"})
public class Ingredient {

    private String id;
    private String description;
    private BigDecimal amount;

    private Recipe recipe;
    private UnitOfMeasure uom;

    public Ingredient() {}

    public Ingredient(String description, BigDecimal amount,
                      UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom,
                      Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

    public String getRecipeId() {
        return (this.recipe == null) ? null : this.recipe.getId();
    }

}///~