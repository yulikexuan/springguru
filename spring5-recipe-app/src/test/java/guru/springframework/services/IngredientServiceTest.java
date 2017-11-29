//: guru.springframework.services.IngredientServiceTest.java


package guru.springframework.services;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.IRecipeRepository;
import guru.springframework.repositories.IUnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class IngredientServiceTest {

    @Mock
    private IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    private IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    private IRecipeRepository recipeRepository;

    @Mock
    private IUnitOfMeasureRepository uomRepository;

    @Mock
    private IngredientCommand ingredientCommand;

    private IngredientService ingredientService;

    private Random random;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.random = new Random(System.currentTimeMillis());
        this.ingredientService = new IngredientService(
                this.ingredientToIngredientCommand,
                this.ingredientCommandToIngredient,
                this.recipeRepository, this.uomRepository);
    }

    @Test
    public void able_To_Find_Ingredient_By_RecipeId_And_IngredientId()
            throws Exception {

        // Given
        Long recipeId = this.random.nextLong();

        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        Ingredient ingredient_0 = new Ingredient();
        ingredient_0.setId(1L);

        Ingredient ingredient_1 = new Ingredient();
        ingredient_0.setId(1L);

        Ingredient ingredient_3 = new Ingredient();
        ingredient_0.setId(3L);

        recipe.addIngredient(ingredient_0);
        recipe.addIngredient(ingredient_1);
        recipe.addIngredient(ingredient_3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(this.recipeRepository.findById(recipeId)).thenReturn(
                recipeOptional);

        when(this.ingredientToIngredientCommand.convert(ingredient_3))
                .thenReturn(ingredientCommand);

        // When
        IngredientCommand result = this.ingredientService
                .findByRecipeIdAndIngredientId(recipeId, 3L);

        // Then
        assertThat(result, is(ingredientCommand));
    }

    @Test
    public void able_To_Update_Existing_Ingredient() throws Exception {

        // Given
        Long recipeId = this.random.nextLong();
        Long ingredientId = this.random.nextLong();
        Long uomId = this.random.nextLong();
        String ingredientDesc = UUID.randomUUID().toString();

        // - The ingredient command from ingredient-form
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredientId);
        ingredientCommand.setRecipeId(recipeId);
        ingredientCommand.setDescription(ingredientDesc);
        BigDecimal amount = BigDecimal.valueOf(1);
        ingredientCommand.setAmount(amount);

        UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
        uomc.setId(uomId);
        ingredientCommand.setUomc(uomc);

        // - The recipe from database
        Recipe foundRecipe = new Recipe();
        Ingredient oldIngredient = Mockito.mock(Ingredient.class);
        foundRecipe.addIngredient(oldIngredient);

        when(oldIngredient.getId()).thenReturn(ingredientId);

        Optional<Recipe> recipeOpt = Optional.of(foundRecipe);
        when(this.recipeRepository.findById(recipeId)).thenReturn(recipeOpt);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(uomId);
        Optional<UnitOfMeasure> uomOpt = Optional.of(uom);
        when(this.uomRepository.findById(uomId)).thenReturn(uomOpt);

        // - The saved recipe
        Recipe savedRecipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientId);
        ingredient.setDescription(ingredientDesc);
        savedRecipe.addIngredient(ingredient);

        when(this.recipeRepository.save(foundRecipe))
                .thenReturn(savedRecipe);

        IngredientCommand updatedIngredientCommand = Mockito.mock(
                IngredientCommand.class);
        when(this.ingredientToIngredientCommand.convert(ingredient)).thenReturn(
                updatedIngredientCommand);

        // When
        IngredientCommand result = this.ingredientService
                .saveIngredientCommand(ingredientCommand);

        // Then
        assertThat(result, is(updatedIngredientCommand));

        verify(oldIngredient, times(1))
                .setDescription(ingredientDesc);
        verify(oldIngredient, times(1)).setUom(uom);
        verify(oldIngredient, times(1))
                .setAmount(amount);

    }// End of able_To_Update_Existing_Ingredient()

}///:~