//: guru.springframework.controllers.IngredientControllerTest.java


package guru.springframework.controllers;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.services.IIngredientReactiveService;
import guru.springframework.services.IUnitOfMeasureService;
import guru.springframework.services.RecipeReactiveService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Ignore
public class IngredientControllerTest extends AbstractControllerTest {

    @Mock
    private RecipeReactiveService recipeReactiveService;

    @Mock
    private IIngredientReactiveService ingredientService;

    @Mock
    private IUnitOfMeasureService unitOfMeasureService;

    @Mock
    private IngredientCommand ingredientCommand;

    private IngredientController controller;

    private String ingredientId;
    private String recipeId;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.ingredientId = this.random.nextLong() + "";
        this.recipeId = this.random.nextLong() + "";
    }

    @Override
    Object initController() {
        this.controller = new IngredientController(this.recipeReactiveService, this.ingredientService, this.unitOfMeasureService);
        return this.controller;
    }

    @Override
    String getInvalidUrl() {
        return "/recipe/123abc456/ingredients";
    }

    @Test
    public void able_To_Handle_Get_Request_To_Get_A_List_Of_Ingredient_Of_A_Recipe() throws Exception {

        // Given
        String id = this.random.nextLong() + "";
        RecipeCommand command = mock(RecipeCommand.class);

        when(this.recipeReactiveService.findCommandById(id)).thenReturn(Mono.just(command));

        // When
        this.mockMvc.perform(get("/recipe/" + id + "/ingredients")).andExpect(status().isOk()).andExpect(view().name("/recipe/ingredient/list")).andExpect(model().attributeExists("recipe"));

        // Then
        verify(this.recipeReactiveService, times(1)).findCommandById(id);
    }

    @Test
    public void able_To_Handle_Get_Request_To_Show_An_Ingredient_Of_A_Recipe() throws Exception {

        // Given
        when(this.ingredientService.findByRecipeIdAndIngredientId(this.recipeId, this.ingredientId)).thenReturn(Mono.just(this.ingredientCommand));

        StringBuilder apiBuilder = new StringBuilder();
        String api = apiBuilder.append("/recipe/").append(this.recipeId).append("/ingredient/").append(this.ingredientId).append("/show").toString();

        // When
        this.mockMvc.perform(get(api)).andExpect(status().isOk()).andExpect(view().name("/recipe/ingredient/show")).andExpect(model().attributeExists("ingredient"));
    }

    @Test
    public void able_To_Handle_Get_Request_To_Show_An_Ingredient_Form() throws Exception {

        // Given
        when(this.ingredientService.findByRecipeIdAndIngredientId(this.recipeId, this.ingredientId)).thenReturn(Mono.just(this.ingredientCommand));

        List<UnitOfMeasureCommand> uomcs = new ArrayList<>();
        when(this.unitOfMeasureService.findAllUnitOfMeasureCommands()).thenReturn(Flux.just());

        StringBuilder apiBuilder = new StringBuilder();
        String api = apiBuilder.append("/recipe/").append(this.recipeId).append("/ingredient/").append(this.ingredientId).append("/update").toString();

        // When
        this.mockMvc.perform(get(api)).andExpect(status().isOk()).andExpect(view().name("/recipe/ingredient/ingredientform")).andExpect(model().attributeExists("ingredient")).andExpect(model().attributeExists("unitOfMeasures"));

    }

    @Test
    public void able_To_Handle_Post_Request_To_Save_Or_Update_Ingredient() throws Exception {

        // Given
        when(this.ingredientService.saveIngredientCommand(Mockito.any(IngredientCommand.class))).thenReturn(Mono.just(this.ingredientCommand));

        when(this.ingredientCommand.getRecipeId()).thenReturn(this.recipeId);
        when(this.ingredientCommand.getId()).thenReturn(this.ingredientId);

        String postUrl = "/recipe/" + this.recipeId + "/ingredient";

        StringBuilder redirectedUrlBuilder = new StringBuilder();
        String redirectedUrl = redirectedUrlBuilder.append("/recipe/").append(this.recipeId).append("/ingredient/").append(this.ingredientId).append("/show").toString();


        // When
        this.mockMvc.perform(post(postUrl).contentType(MediaType.APPLICATION_FORM_URLENCODED).param("description", UUID.randomUUID().toString())).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl(redirectedUrl));
    }

    @Test
    public void able_To_Show_Ingredient_Form_For_Creating_New_Ingredient() throws Exception {

        // Given
        String requestUrl = "/recipe/" + this.recipeId + "/ingredient/new";
        String formUrl = "/recipe/ingredient/ingredientform";

        when(this.recipeReactiveService.existById(this.recipeId)).thenReturn(Mono.just(true));

        Set<UnitOfMeasureCommand> unitOfMeasures = new HashSet<>();
        when(this.unitOfMeasureService.findAllUnitOfMeasureCommands()).thenReturn(Flux.just());

        // When
        this.mockMvc.perform(get(requestUrl)).andExpect(status().isOk()).andExpect(view().name(formUrl)).andExpect(model().attributeExists("ingredient")).andExpect(model().attributeExists("unitOfMeasures"));

        // Then
        verify(this.recipeReactiveService, times(1)).existById(this.recipeId);
    }

    @Test
    public void able_To_Delete_An_Ingredient_From_A_Recipe() throws Exception {

        // Given
        String delUrl = new StringBuilder().append("/recipe/").append(this.recipeId).append("/ingredient/").append(this.ingredientId).append("/delete").toString();

        String redirectUrl = new StringBuilder().append("/recipe/").append(this.recipeId).append("/ingredients").toString();

        when(this.ingredientService.deleteIngredientCommand(this.recipeId, this.ingredientId)).thenReturn(Mono.empty());

        // When
        this.mockMvc.perform(get(delUrl)).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl(redirectUrl));

        // Then
        verify(this.ingredientService, times(1)).deleteIngredientCommand(this.recipeId, this.ingredientId);
    }

}///~