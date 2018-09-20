//: guru.springframework.services.RecipeReactiveServiceTest.java


package guru.springframework.services;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.reactive.IRecipeReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(Theories.class)
public class RecipeReactiveServiceTest {

    @Mock
    private IRecipeReactiveRepository recipeReactiveRepository;

    private Random random;
    private RecipeReactiveService recipeReactiveService;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() {
        this.random = new Random(System.currentTimeMillis());
        MockitoAnnotations.initMocks(this);
        this.recipeReactiveService = new RecipeReactiveService(this.recipeReactiveRepository, this.recipeCommandToRecipe, this.recipeToRecipeCommand);
    }

    @Test
    public void able_To_Get_A_Recipe_By_Its_ID() {

        // Given
        String id = this.random.nextLong() + "";
        Recipe recipe = new Recipe();
        recipe.setId(id);

        when(this.recipeReactiveRepository.findById(id)).thenReturn(Mono.just(recipe));

        // When
        Recipe recipeReturned = this.recipeReactiveService.findById(id).block();

        // Then
        assertNotNull("Null recipe returned!", recipeReturned);

        verify(this.recipeReactiveRepository, times(1)).findById(id);
        verify(this.recipeReactiveRepository, never()).findAll();
    }

    @Test
    public void able_To_Fetch_All_Recipes() {

        // Arrange
        Recipe recipe = new Recipe();
        List<Recipe> expectedRecipes = new ArrayList<>();
        expectedRecipes.add(recipe);

        when(this.recipeReactiveRepository.findAll()).thenReturn(Flux.just(recipe));

        // Action
        List<Recipe> recipes = this.recipeReactiveService.getAllRecipes().collectList().block();

        // Assert
        assertThat(recipes.get(0), is(recipe));
        assertThat(recipes.size(), is(1));
    }

    @Test
    public void saveRecipeCommand() throws Exception {

        // Given
        Recipe recipe = mock(Recipe.class);
        Recipe savedRecipe = mock(Recipe.class);
        RecipeCommand originalCommand = mock(RecipeCommand.class);
        RecipeCommand returnedCommand = mock(RecipeCommand.class);

        when(this.recipeCommandToRecipe.convert(originalCommand)).thenReturn(recipe);

        when(this.recipeReactiveRepository.save(recipe)).thenReturn(Mono.just(savedRecipe));

        when(this.recipeToRecipeCommand.convert(savedRecipe)).thenReturn(returnedCommand);

        // When
        RecipeCommand result = this.recipeReactiveService.saveRecipeCommand(originalCommand).block();

        // Then
        assertThat(result, is(returnedCommand));
    }

    @Test
    public void able_To_find_The_RecipeCommand_By_Id() throws Exception {

        // Given
        String id = this.random.nextLong() + "";

        Recipe recipe = mock(Recipe.class);
        RecipeCommand command = mock(RecipeCommand.class);

        String recipeId = UUID.randomUUID().toString();
        when(command.getId()).thenReturn(recipeId);

        when(this.recipeReactiveRepository.findById(id)).thenReturn(Mono.just(recipe));

        when(this.recipeToRecipeCommand.convert(recipe)).thenReturn(command);

        IngredientCommand ic = mock(IngredientCommand.class);
        List<IngredientCommand> ics = new ArrayList<>();
        ics.add(ic);
        when(command.getIngredients()).thenReturn(ics);

        // When
        RecipeCommand result = this.recipeReactiveService.findCommandById(id).block();

        // Then
        assertThat(result, is(command));
        verify(ic, times(1)).setRecipeId(recipeId);
    }

    @Test
    public void able_To_Delete_A_Recipe_By_Its_Id() {

        // Given
        String id = this.random.nextLong() + "";

        when(this.recipeReactiveRepository.deleteById(id)).thenReturn(Mono.empty());

        // When
        this.recipeReactiveService.deleteById(id);

        // Then
        verify(this.recipeReactiveRepository, times(1)).deleteById(id);
    }

    @DataPoint
    public static boolean positive = true;
    @DataPoint
    public static boolean negative = false;

    @Theory
    public void able_To_Know_If_A_Recipe_Id_Exists(boolean exists) throws Exception {

        // Give
        String recipeId = this.random.nextLong() + "";

        when(this.recipeReactiveRepository.existsById(recipeId)).thenReturn(Mono.just(exists));

        // When
        boolean actuallyExists = this.recipeReactiveService.existById(recipeId).block();

        // Then
        assertThat(actuallyExists, is(exists));
    }

}///:~