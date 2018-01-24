//: guru.springframework.domain.Ingredient.java

package guru.springframework.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@EqualsAndHashCode(of = {"id", "description"})
@ToString(of = {"id", "description"})
public class Ingredient {

    private String id;
    private String description;
    private BigDecimal amount;

    @DBRef
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
//        this.recipe = recipe;
    }

}///~