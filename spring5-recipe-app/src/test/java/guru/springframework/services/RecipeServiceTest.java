//: guru.springframework.services.RecipeServiceTest.java


package guru.springframework.services;


import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IRecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class RecipeServiceTest {

    @Mock
    private IRecipeRepository recipeRepository;

    private IRecipeService recipeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.recipeService = new RecipeService(this.recipeRepository);
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