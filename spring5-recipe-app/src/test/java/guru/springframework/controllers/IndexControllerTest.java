//: guru.springframework.controllers.IndexControllerTest.java


package guru.springframework.controllers;


import guru.springframework.domain.Recipe;
import guru.springframework.services.IRecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(Theories.class)
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
                eq("allRecipes"), recipesCaptor.capture());

        Set<Recipe> actualRecipes = recipesCaptor.getValue();
        assertEquals(actualRecipes.size(), 2);
    }

    @DataPoint public static String rootUrl = "/";
    @DataPoint public static String pageNameUrl = "/home";
    @DataPoint public static String defaultUrl = "";
    @Theory
    public void able_To_Access_Index_Page(String url) throws Exception {

        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(this.indexController)
                .build();

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

}///:~