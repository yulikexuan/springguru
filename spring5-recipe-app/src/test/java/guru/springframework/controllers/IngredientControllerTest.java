//: guru.springframework.controllers.IngredientControllerTest.java


package guru.springframework.controllers;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class IngredientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RecipeService recipeService;

    @Mock
    private IngredientService ingredientService;

    private IngredientController controller;

    private Random random;

    @Before
    public void setUp() throws Exception {
        this.random = new Random(System.currentTimeMillis());
        MockitoAnnotations.initMocks(this);
        this.controller = new IngredientController(this.recipeService,
                this.ingredientService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
    }

    @Test
    public void able_To_Handle_Get_Request_To_Get_A_List_Of_Ingredient_Of_A_Recipe()
            throws Exception {

        // Given
        Long id = this.random.nextLong();
        RecipeCommand command = mock(RecipeCommand.class);

        when(this.recipeService.findCommandById(id)).thenReturn(command);

        // When
        this.mockMvc.perform(get("/recipe/" + id + "/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        // Then
        verify(this.recipeService, times(1))
                .findCommandById(id);
    }

    @Test
    public void able_To_Handle_Get_Request_To_Get_An_Ingredient_Of_A_Recipe()
            throws Exception {

        // Given
        Long id = 1L;
        Long recipeId = 2L;

        IngredientCommand command = mock(IngredientCommand.class);

        when(this.ingredientService.findByRecipeIdAndIngredientId(recipeId, id))
                .thenReturn(command);

        StringBuilder apiBuilder = new StringBuilder();
        String api = apiBuilder.append("/recipe/")
                .append(recipeId)
                .append("/ingredient/")
                .append(id)
                .append("/show")
                .toString();

        // When
        this.mockMvc.perform(
                get(api))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }

}///~