//: guru.springframework.converters.IngredientCommandToIngredientTest.java


package guru.springframework.converters;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class IngredientCommandToIngredientTest {

    private Random random;

    private String id;
    private String description;
    private BigDecimal amount;

    @Mock
    private UnitOfMeasure uom;

    @Mock
    private UnitOfMeasureCommand uomc;

    @Mock
    private UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    private IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        this.random = new Random(System.currentTimeMillis());
        this.id = UUID.randomUUID().toString();
        this.description = UUID.randomUUID().toString();
        this.amount = new BigDecimal(this.random.nextDouble());

        this.converter = new IngredientCommandToIngredient(this.uomConverter);
    }

    @Test
    public void can_Convert_Null_To_Null() throws Exception {
        assertNull(this.converter.convert(null));
    }

    @Test
    public void can_Convert_Empty_To_Empty() throws Exception {
        assertNotNull(this.converter.convert(
                new IngredientCommand.Builder().createIngredientCommand()));
    }

    @Test
    public void able_To_Convert_IngredientCommand_To_Ingredient() {

        // Given
        IngredientCommand command = new IngredientCommand.Builder()
                .setId(this.id)
                .setDescription(this.description)
                .setAmount(this.amount)
                .setUomc(this.uomc)
                .createIngredientCommand();

        when(this.uomConverter.convert(this.uomc)).thenReturn(this.uom);

        // When
        Ingredient ingredient = this.converter.convert(command);

        // Then
        assertNotNull(ingredient);
        assertThat(ingredient.getUom(), is(this.uom));
        assertThat(ingredient.getId(), is(this.id));
        assertThat(ingredient.getDescription(), is(this.description));
        assertThat(ingredient.getAmount(), is(this.amount));
    }

    @Test
    public void able_To_Convert_IngredientCommand_To_Ingredient_With_Null_UoM() {

        // Given
        IngredientCommand command = new IngredientCommand.Builder()
                .setId(this.id)
                .setDescription(this.description)
                .setAmount(this.amount)
                .createIngredientCommand();

        when(this.uomConverter.convert(null)).thenReturn(null);

        // When
        Ingredient ingredient = this.converter.convert(command);

        // Then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertThat(ingredient.getId(), is(this.id));
        assertThat(ingredient.getDescription(), is(this.description));
        assertThat(ingredient.getAmount(), is(this.amount));
    }

}///:~