//: guru.springframework.converters.IngredientCommandToIngredient.java


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
public class IngredientCommandToIngredient implements
        Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(
            @NotNull UnitOfMeasureCommandToUnitOfMeasure uomConverter) {

        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand command) {

        if (command == null) {
            return null;
        }

        return new IngredientBuilder()
                .setId(command.getId())
                .setDescription(command.getDescription())
                .setAmount(command.getAmount())
                .setUom(this.uomConverter.convert(command.getUomc()))
                .build();
    }

}///~