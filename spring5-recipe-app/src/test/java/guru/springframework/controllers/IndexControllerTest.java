//: guru.springframework.controllers.IndexControllerTest.java


package guru.springframework.controllers;


import guru.springframework.domain.Recipe;
import guru.springframework.services.IRecipeReactiveService;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import reactor.core.publisher.Flux;

import java.util.*;

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

    @Mock private IRecipeReactiveService recipeReactiveService;
    @Mock private Model model;

    private IndexController indexController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.indexController = new IndexController(this.recipeReactiveService);
    }

    @Test
    public void able_To_Inject_All_Recipes_Into_Index_Page() {

        // Given
        List<Recipe> recipes = new ArrayList<>();
        Recipe r1 = new Recipe();
        r1.setDescription(UUID.randomUUID().toString());
        Recipe r2 = new Recipe();
        r2.setDescription(UUID.randomUUID().toString());

        recipes.add(r1);
        recipes.add(r2);

        when(this.recipeReactiveService.getAllRecipes())
                .thenReturn(Flux.fromIterable(recipes));

        ArgumentCaptor<List<Recipe>> recipesCaptor = ArgumentCaptor.forClass(
                List.class);

        // When
        String pageName = this.indexController.getIndexPage(this.model);

        // Then
        assertEquals("index", pageName);
        verify(this.model, times(1)).addAttribute(
                eq("allRecipes"), recipesCaptor.capture());

        List<Recipe> actualRecipes = recipesCaptor.getValue();
        assertEquals(actualRecipes.size(), 2);
    }

    @DataPoint public static String rootUrl = "/";
    @DataPoint public static String pageNameUrl = "/home";
    @DataPoint public static String defaultUrl = "";
    @Theory
    public void able_To_Access_Index_Page(String url) throws Exception {

        // Given
        List<Recipe> recipes = new ArrayList<>();
        Recipe r1 = new Recipe();
        r1.setDescription(UUID.randomUUID().toString());
        Recipe r2 = new Recipe();
        r2.setDescription(UUID.randomUUID().toString());

        recipes.add(r1);
        recipes.add(r2);

        when(this.recipeReactiveService.getAllRecipes())
                .thenReturn(Flux.fromIterable(recipes));

        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(this.indexController)
                .build();

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

}///:~