//: guru.springframework.domain.builders.IngredientBuilder.java


package guru.springframework.domain.builders;


import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;


@Component()
@Scope("prototype")
public class IngredientBuilder implements IModelBuilder<Ingredient> {

    private String id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasure uom;
    private Recipe recipe;

    public IngredientBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public IngredientBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public IngredientBuilder setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public IngredientBuilder setUom(UnitOfMeasure uom) {
        this.uom = uom;
        return this;
    }

    public IngredientBuilder setRecipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }

    @Override
    public Ingredient build() {

        Ingredient ingredient = new Ingredient();

        ingredient.setId(UUID.randomUUID().toString());
        ingredient.setDescription(this.description);
        ingredient.setAmount(this.amount);
        ingredient.setUom(this.uom);

        return ingredient;
    }

    @Override
    public void clear() {
        this.id = null;
        this.description = null;
        this.amount = null;
        this.uom = null;
        this.recipe = null;
    }

}///~