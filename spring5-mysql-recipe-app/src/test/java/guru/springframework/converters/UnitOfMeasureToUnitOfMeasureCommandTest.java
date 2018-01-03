//: guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommandTest.java


package guru.springframework.converters;


import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;


public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private Random random;
    private Long id;
    private String description;

    private UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        this.random = new Random(System.currentTimeMillis());
        this.id = this.random.nextLong();
        this.description = UUID.randomUUID().toString();
        this.converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void result_Can_Be_Null() throws Exception {
        assertNull(this.converter.convert(null));
    }

    @Test
    public void result_Can_Be_Empty() throws Exception {
        assertNotNull(this.converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void able_To_Convert_UnitOfMeasureCommand_To_UnitOfMeasure() {

        // Given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(this.id);
        uom.setDescription(this.description);

        // When
        UnitOfMeasureCommand command = this.converter.convert(uom);

        // Then
        assertEquals(this.id, command.getId());
        assertEquals(this.description, command.getDescription());
    }

}///:~