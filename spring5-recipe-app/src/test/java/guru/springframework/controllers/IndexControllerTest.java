//: guru.springframework.controllers.IndexControllerTest.java


package guru.springframework.controllers;


import guru.springframework.domain.Recipe;
import guru.springframework.services.IRecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

        // Given
        Set<Recipe> recipes = new HashSet<>();
        Recipe r1 = new Recipe();
        r1.setDescription(UUID.randomUUID().toString());
        Recipe r2 = new Recipe();
        r2.setDescription(UUID.randomUUID().toString());

        recipes.add(r1);
        recipes.add(r2);

        when(this.recipeService.getAllRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> recipesCaptor = ArgumentCaptor.forClass(
                Set.class);

        // When
        String pageName = this.indexController.getIndexPage(this.model);

        // Then
        assertEquals("index", pageName);
        verify(this.model, times(1)).addAttribute(
                "allRecipes", recipesCaptor.capture());

        Set<Recipe> actualRecipes = recipesCaptor.getValue();
        assertEquals(actualRecipes.size(), 2);
    }

}///:~