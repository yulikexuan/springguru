//: guru.springframework.services.RecipeServiceTest.java


package guru.springframework.services;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.IRecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(Theories.class)
public class RecipeServiceTest {

    @Mock
    private IRecipeRepository recipeRepository;

    private Random random;
    private RecipeService recipeService;

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
        String id = this.random.nextLong() + "";
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

    @Test(expected = NotFoundException.class)
    public void able_To_Throw_NotFoundException_When_Recipe_Not_Fount()
            throws Exception {

        // Given
        Optional<Recipe> recipeOpt = Optional.empty();
        String id = this.random.nextLong() + "";
        when(this.recipeRepository.findById(id)).thenReturn(recipeOpt);

        // When
        this.recipeService.findById(id);
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

    @Test
    public void able_To_find_The_RecipeCommand_By_Id() throws Exception {

        // Given
        String id = this.random.nextLong() + "";

        Recipe recipe = mock(Recipe.class);
        RecipeCommand command = mock(RecipeCommand.class);
        Optional<Recipe> recipeOpt = Optional.of(recipe);

        when(this.recipeRepository.findById(id)).thenReturn(recipeOpt);

        when(this.recipeToRecipeCommand.convert(recipe)).thenReturn(command);

        // When
        RecipeCommand result = this.recipeService.findCommandById(id);

        // Then
        assertThat(result, is(command));
    }

    @Test(expected = RuntimeException.class)
    public void maybe_Not_Able_To_Find_RecipeCommand_By_Id() {

        // Given
        String id = this.random.nextLong() + "";

        Optional<Recipe> recipeOpt = Optional.empty();
        when(this.recipeRepository.findById(id)).thenReturn(recipeOpt);

        // When
        this.recipeService.findCommandById(id);
    }

    @Test
    public void able_To_Delete_A_Recipe_By_Its_Id() {

        // Given
        String id = this.random.nextLong() + "";

        // When
        this.recipeService.deleteById(id);

        // Then
        verify(this.recipeRepository, times(1))
                .deleteById(id);
    }

    @DataPoint public static boolean positive = true;
    @DataPoint public static boolean negative = false;
    @Theory
    public void able_To_Know_If_A_Recipe_Id_Exists(boolean exists) throws
            Exception {

        // Give
        String recipeId = this.random.nextLong()  + "";

        when(this.recipeRepository.existsById(recipeId)).thenReturn(exists);

        // When
        boolean actuallyExists = this.recipeService.existById(recipeId);

        // Then
        assertThat(actuallyExists, is(exists));
    }

}///:~