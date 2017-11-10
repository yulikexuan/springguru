//: guru.springframework.services.RecipeServiceTest.java


package guru.springframework.services;


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

    @Before
    public void setUp() throws Exception {
        this.random = new Random(System.currentTimeMillis());
        MockitoAnnotations.initMocks(this);
        this.recipeService = new RecipeService(this.recipeRepository);
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

}///:~