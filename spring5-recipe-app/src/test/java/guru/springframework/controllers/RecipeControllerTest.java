//: guru.springframework.controllers.RecipeControllerTest.java


package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.Random;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    private RecipeController recipeController;

    private Random random;

    @Before
    public void setUp() throws Exception {
        this.random = new Random(System.currentTimeMillis());
        MockitoAnnotations.initMocks(this);
        this.recipeController = new RecipeController(this.recipeService);
    }

    @Test
    public void able_To_Get_A_Recipe_From_Service_To_The_View()
            throws Exception {

        // Given
        Long id = this.random.nextLong();
        Recipe recipe = new Recipe();
        recipe.setId(id);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(this.recipeController).build();

        // When
        when(this.recipeService.findById(id)).thenReturn(recipeOptional);

        // Then
        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
    }

    @Test
    public void null_Recipe_Is_Not_Set_In_Model_()
            throws Exception {

        // Given
        Long id = this.random.nextLong();

        Optional<Recipe> recipeOptional = Optional.empty();

        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(this.recipeController).build();

        // When
        when(this.recipeService.findById(id)).thenReturn(recipeOptional);

        // Then
        mockMvc.perform(get("/recipe/show/" + Long.toString(id)))
                .andExpect(status().isOk())
                .andExpect(model().attribute("recipe",
                        Matchers.nullValue()))
                .andExpect(view().name("recipe/show"));
    }

}///~