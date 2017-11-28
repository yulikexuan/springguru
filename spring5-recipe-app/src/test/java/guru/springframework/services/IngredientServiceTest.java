//: guru.springframework.services.IngredientServiceTest.java


package guru.springframework.services;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IRecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class IngredientServiceTest {

    @Mock
    private IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    private IRecipeRepository recipeRepository;

    @Mock
    private IngredientCommand ingredientCommand;

    private IngredientService ingredientService;

    private Random random;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.random = new Random(System.currentTimeMillis());
        this.ingredientService = new IngredientService(
                this.ingredientToIngredientCommand, this.recipeRepository);
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

}///:~