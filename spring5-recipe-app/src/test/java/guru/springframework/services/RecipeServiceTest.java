//: guru.springframework.services.RecipeServiceTest.java


package guru.springframework.services;


import com.sun.org.apache.regexp.internal.RE;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IRecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class RecipeServiceTest {

    @Mock
    private IRecipeRepository recipeRepository;

    private Random random;
    private IRecipeService recipeService;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        this.random = new Random(System.currentTimeMillis());
        MockitoAnnotations.initMocks(this);
        this.recipeService = new RecipeService(this.recipeRepository,
                this.recipeCommandToRecipe, this.recipeToRecipeCommand);
    }

    @Test
    public void able_To_Get_A_Recipe_By_Its_ID() {

        // Given
        Long id = this.random.nextLong();
        Recipe recipe = new Recipe();
        recipe.setId(id);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(this.recipeRepository.findById(id)).thenReturn(recipeOptional);

        // When
        Optional<Recipe> recipeReturned = this.recipeService.findById(id);

        // Then
        assertNotNull("Null recipe returned!", recipeReturned);

        assertThat(recipeReturned.get(), is(recipe));

        verify(this.recipeRepository, times(1))
                .findById(id);
        verify(this.recipeRepository, never()).findAll();
    }

    @Test
    public void getAllRecipes() throws Exception {

        // Arrange
        Recipe recipe = new Recipe();
        List<Recipe> expectedRecipes = new ArrayList<>();
        expectedRecipes.add(recipe);

        when(this.recipeRepository.findAll()).thenReturn(expectedRecipes);

        // Action
        Set<Recipe> recipes = this.recipeService.getAllRecipes();

        // Assert
        assertEquals(recipes.size(), 1);
    }

    @Test
    public void saveRecipeCommand() throws Exception {

        // Given
        Recipe recipe = mock(Recipe.class);
        Recipe savedRecipe = mock(Recipe.class);
        RecipeCommand originalCommand = mock(RecipeCommand.class);
        RecipeCommand returnedCommand = mock(RecipeCommand.class);

        when(this.recipeCommandToRecipe.convert(originalCommand))
                .thenReturn(recipe);

        when(this.recipeRepository.save(recipe)).thenReturn(savedRecipe);

        when(this.recipeToRecipeCommand.convert(savedRecipe))
                .thenReturn(returnedCommand);

        // When
        RecipeCommand result = this.recipeService.saveRecipeCommand(
                originalCommand);

        // Then
        assertThat(result, is(returnedCommand));
    }

}///:~