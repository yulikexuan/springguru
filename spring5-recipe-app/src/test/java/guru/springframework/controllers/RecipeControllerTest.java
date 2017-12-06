//: guru.springframework.controllers.RecipeControllerTest.java


package guru.springframework.controllers;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
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


public class RecipeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RecipeService recipeService;

    private RecipeController recipeController;

    private Random random;

    @Before
    public void setUp() throws Exception {
        this.random = new Random(System.currentTimeMillis());
        MockitoAnnotations.initMocks(this);
        this.recipeController = new RecipeController(this.recipeService);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(this.recipeController).build();
    }

    @Test
    public void able_To_Get_A_Recipe_From_Service_To_The_View()
            throws Exception {

        // Given
        Long id = this.random.nextLong();
        Recipe recipe = new Recipe();
        recipe.setId(id);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        String url = "/recipe/" + id + "/show";

        // When
        when(this.recipeService.findById(id)).thenReturn(recipeOptional);

        // Then
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/show"));
    }

    @Test
    public void null_Recipe_Is_Not_Set_In_Model_()
            throws Exception {

        // Given
        Long id = this.random.nextLong();

        Optional<Recipe> recipeOptional = Optional.empty();

        // When
        when(this.recipeService.findById(id)).thenReturn(recipeOptional);

        // Then
        this.mockMvc.perform(get(
                "/recipe/" + Long.toString(id) + "/show"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("recipe",
                        Matchers.nullValue()))
                .andExpect(view().name("recipe/show"));
    }

    @Test
    public void able_To_Show_A_Recipe_Form_For_Creating_New_Recipe() throws Exception {

        // When
        this.mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(model().attribute("recipe",
                        Matchers.any(RecipeCommand.class)))
                .andExpect(view().name("recipe/recipeform"));
    }

    @Test
    public void able_To_Save_New_Recipe_Or_Update_Exist_Recipe()
            throws Exception {

        // Given
        RecipeCommand savedCommand = Mockito.mock(RecipeCommand.class);

        when(this.recipeService
                .saveRecipeCommand(Mockito.any(RecipeCommand.class)))
                .thenReturn(savedCommand);

        Long id = this.random.nextLong();
        when(savedCommand.getId()).thenReturn(id);

        // When
        this.mockMvc.perform(post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("description",
                                UUID.randomUUID().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/recipe/" + id + "/show"));
    }

    @Test
    public void able_To_Send_An_Exist_Recipe_To_A_Recipe_Form_To_Edit_It()
            throws Exception {

        // Given
        Long id = this.random.nextLong();

        RecipeCommand command = Mockito.mock(RecipeCommand.class);

        when(this.recipeService.findCommandById(id)).thenReturn(command);

        // When
        this.mockMvc.perform(get("/recipe/" + id + "/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void able_To_Handle_Delete_Recipe_By_Id_Request() throws Exception {

        // Given
        Long id = this.random.nextLong();
        String idParam = Long.toString(id);

        // When
        this.mockMvc.perform(get("/recipe/" + idParam + "/delete"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));

        // Then
        verify(this.recipeService, times(1))
                .deleteById(id);
    }

    @Test
    public void able_To_Show_404error_Page_When_Getting_NotFoundException()
            throws Exception {

        // Given
        Long id = this.random.nextLong();

        String url = "/recipe/" + id + "/update";

        when(this.recipeService.findCommandById(id))
                .thenThrow(NotFoundException.class);

        // When
        this.mockMvc.perform(get(url))
                .andExpect(status().isNotFound())
                .andExpect(model().attributeExists("exception"))
                .andExpect(view().name("404error"));
    }

    @Test
    public void able_To_Handle_NumberFormatException() throws Exception {

        // When
        this.mockMvc.perform(get("/recipe/23lkfj34/show"))
                .andExpect(status().isBadRequest())
                .andExpect(model().attributeExists("exception"))
                .andExpect(view().name("400error"));
    }

}///~