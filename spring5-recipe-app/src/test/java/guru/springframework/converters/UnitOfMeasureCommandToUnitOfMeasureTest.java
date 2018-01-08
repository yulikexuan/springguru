//: guru.springframework.converters.UnitOfMeasureCommandToUnitOfMeasureTest.java


package guru.springframework.converters;


import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;


public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private Random random;
    private String id;
    private String description;

    private UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        this.random = new Random(System.currentTimeMillis());
        this.id = this.random.nextLong() + "";
        this.description = UUID.randomUUID().toString();
        this.converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void result_Can_Be_Null() throws Exception {
        assertNull(this.converter.convert(null));
    }

    @Test
    public void result_Can_Be_Empty() throws Exception {
        assertNotNull(this.converter.convert(
                new UnitOfMeasureCommand.Builder().createUnitOfMeasureCommand()));
    }

    @Test
    public void able_To_Convert_UnitOfMeasureCommand_To_UnitOfMeasure() {

        // Given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand.Builder()
                .setId(this.id)
                .setDescription(this.description)
                .createUnitOfMeasureCommand();

        // When
        UnitOfMeasure uom = this.converter.convert(command);

        // Then
        assertEquals(this.id, uom.getId());
        assertEquals(this.description, uom.getDescription());

    }

}///:~