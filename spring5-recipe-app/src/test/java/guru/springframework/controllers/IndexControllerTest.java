//: guru.springframework.controllers.IndexControllerTest.java


package guru.springframework.controllers;


import guru.springframework.domain.Recipe;
import guru.springframework.services.IRecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class IndexControllerTest {

    @Mock private IRecipeService recipeService;
    @Mock private Model model;

    private IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.indexController = new IndexController(this.recipeService);
    }

    @Test
    public void able_To_Inject_All_Recipes_Into_Index_Page() {

        // Arrange

        // Given
        Set<Recipe> recipes = Mockito.mock(Set.class);

        when(this.recipeService.getAllRecipes()).thenReturn(recipes);

        // Action
        String pageName = this.indexController.getIndexPage(this.model);

        // Assert
        assertEquals("index", pageName);
        verify(this.model, times(1)).addAttribute(
                "allRecipes", recipes);
    }

}///:~