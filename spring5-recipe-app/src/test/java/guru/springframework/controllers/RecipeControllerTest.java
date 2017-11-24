//: guru.springframework.controllers.RecipeControllerTest.java


package guru.springframework.controllers;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.Random;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
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

        String url = "/recipe/show/" + id;

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
        this.mockMvc.perform(get("/recipe/show/" + Long.toString(id)))
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
    public void able_To_Save_Or_Update_RecipeCommand_From_Front_End()
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
                        .param("description", "sladfjslad"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/recipe/show/" + id));
    }

}///~