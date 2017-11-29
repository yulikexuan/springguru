//: guru.springframework.controllers.IngredientControllerTest.java


package guru.springframework.controllers;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.services.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class IngredientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RecipeService recipeService;

    @Mock
    private IIngredientService ingredientService;

    @Mock
    private IUnitOfMeasureService unitOfMeasureService;

    @Mock
    private IngredientCommand ingredientCommand;

    private IngredientController controller;

    private Random random;
    private Long ingredientId;
    private Long recipeId;

    @Before
    public void setUp() throws Exception {
        this.random = new Random(System.currentTimeMillis());
        this.ingredientId = this.random.nextLong();
        this.recipeId = this.random.nextLong();

        MockitoAnnotations.initMocks(this);

        this.controller = new IngredientController(this.recipeService,
                this.ingredientService, this.unitOfMeasureService);
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
    public void able_To_Handle_Get_Request_To_Show_An_Ingredient_Of_A_Recipe()
            throws Exception {

        // Given
        when(this.ingredientService.findByRecipeIdAndIngredientId(
                this.recipeId, this.ingredientId)).thenReturn(
                        this.ingredientCommand);

        StringBuilder apiBuilder = new StringBuilder();
        String api = apiBuilder.append("/recipe/")
                .append(this.recipeId)
                .append("/ingredient/")
                .append(this.ingredientId)
                .append("/show")
                .toString();

        // When
        this.mockMvc.perform(
                get(api))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    public void able_To_Handle_Get_Request_To_Show_An_Ingredient_Form()
            throws Exception {

        // Given
        when(this.ingredientService.findByRecipeIdAndIngredientId(
                this.recipeId, this.ingredientId)).thenReturn(
                this.ingredientCommand);

        Set<UnitOfMeasureCommand> uomcs = new HashSet<>();
        when(this.unitOfMeasureService.findAllUnitOfMeasureCommands())
                .thenReturn(uomcs);

        StringBuilder apiBuilder = new StringBuilder();
        String api = apiBuilder.append("/recipe/")
                .append(this.recipeId)
                .append("/ingredient/")
                .append(this.ingredientId)
                .append("/update")
                .toString();

        // When
        this.mockMvc.perform(
                get(api))
                .andExpect(status().isOk())
                .andExpect(view().name(
                        "/recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("unitOfMeasures"));

    }

    @Test
    public void able_To_Handle_Post_Request_To_Save_Or_Update_Ingredient()
            throws Exception {

        // Given
        when(this.ingredientService
                .saveIngredientCommand(Mockito.any(IngredientCommand.class)))
                .thenReturn(this.ingredientCommand);

        when(this.ingredientCommand.getRecipeId()).thenReturn(this.recipeId);
        when(this.ingredientCommand.getId()).thenReturn(this.ingredientId);

        String postUrl = "/recipe/" + this.recipeId + "/ingredient";

        StringBuilder redirectedUrlBuilder = new StringBuilder();
        String redirectedUrl = redirectedUrlBuilder
                .append("/recipe/")
                .append(this.recipeId)
                .append("/ingredient/")
                .append(this.ingredientId)
                .append("/show")
                .toString();


        // When
        this.mockMvc.perform(post(postUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", UUID.randomUUID().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(redirectedUrl));
    }

}///~