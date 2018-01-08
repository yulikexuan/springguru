//: guru.springframework.converters.IngredientToIngredientCommandTest.java


package guru.springframework.converters;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.domain.builders.IngredientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class IngredientToIngredientCommandTest {

    private Random random;

    private String id;
    private String description;
    private BigDecimal amount;

    @Mock
    private UnitOfMeasure uom;

    @Mock
    private UnitOfMeasureCommand uomc;

    @Mock
    private UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    private IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        this.random = new Random(System.currentTimeMillis());
        this.id = this.random.nextLong() + "";
        this.description = UUID.randomUUID().toString();
        this.amount = new BigDecimal(this.random.nextDouble());

        this.converter = new IngredientToIngredientCommand(this.uomConverter);
    }

    @Test
    public void can_Convert_Null_To_Null() throws Exception {
        assertNull(this.converter.convert(null));
    }

    @Test
    public void can_Convert_Empty_To_Empty() throws Exception {
        assertNotNull(this.converter.convert(new IngredientBuilder().build()));
    }

    private Ingredient createIngredient() {
        String recipeId = this.random.nextLong() + "";
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        return new IngredientBuilder()
                .setId(this.id)
                .setRecipe(recipe)
                .setDescription(this.description)
                .setAmount(this.amount)
                .setUom(this.uom)
                .build();
    }

    @Test
    public void able_To_Convert_Ingredient_To_IngredientCommand() {

        // Given
        Ingredient ingredient = createIngredient();

        when(this.uomConverter.convert(this.uom)).thenReturn(this.uomc);

        // When
        IngredientCommand command = this.converter.convert(ingredient);

        // Then
        assertNotNull(command);
        assertThat(command.getUomc(), is(this.uomc));
        assertThat(command.getId(), is(this.id));
        assertThat(command.getDescription(), is(this.description));
        assertThat(command.getAmount(), is(this.amount));
    }

    @Test
    public void able_To_Convert_Ingredient_To_IngredientCommand_With_Null_UoM() {

        // Given
        Ingredient ingredient = createIngredient();
        ingredient.setUom(null);

        when(this.uomConverter.convert(null)).thenReturn(null);

        // When
        IngredientCommand command = this.converter.convert(ingredient);

        // Then
        assertNotNull(command);
        assertThat(command.getUomc(), nullValue());
        assertThat(command.getId(), is(this.id));
        assertThat(command.getDescription(), is(this.description));
        assertThat(command.getAmount(), is(this.amount));
    }

}///:~