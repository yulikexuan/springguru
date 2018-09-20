//: guru.springframework.converters.UnitOfMeasureCommandToUnitOfMeasure.java


package guru.springframework.converters;


import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand command) {

        if (command == null) {
            return null;
        }

        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(command.getId());
        uom.setDescription(command.getDescription());

        return uom;
    }

}///~