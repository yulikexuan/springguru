//: guru.springframework.converters.IngredientToIngredientCommand.java


package guru.springframework.converters;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.builders.IngredientBuilder;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(@NotNull UnitOfMeasureToUnitOfMeasureCommand uomConverter) {

        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {

        if (ingredient == null) {
            return null;
        }

        return new IngredientCommand.Builder().setId(ingredient.getId()).setDescription(ingredient.getDescription()).setAmount(ingredient.getAmount()).setUomc(this.uomConverter.convert(ingredient.getUom())).createIngredientCommand();
    }

}///~