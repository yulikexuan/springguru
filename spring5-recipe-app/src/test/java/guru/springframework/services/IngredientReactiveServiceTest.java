//: guru.springframework.services.IngredientReactiveServiceTest.java


package guru.springframework.services;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.reactive.IRecipeReactiveRepository;
import guru.springframework.repositories.reactive.IUnitOfMeasureReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class IngredientReactiveServiceTest {

    @Mock
    private IRecipeReactiveRepository recipeRepository;

    @Mock
    private IUnitOfMeasureReactiveRepository uomRepository;

    @Mock
    private IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    private IngredientCommandToIngredient ingredientCommandToIngredient;

    private IngredientReactiveService ingredientService;
    private String recipeId;
    private String ingredientId;
    private Recipe recipe;
    private Ingredient ingredient;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        this.recipeId = UUID.randomUUID().toString();
        this.ingredientId = UUID.randomUUID().toString();
        this.recipe = new Recipe();
        this.recipe.setId(this.recipeId);
        this.ingredient = new Ingredient();
        this.ingredient.setId(this.ingredientId);

        this.ingredientService = new IngredientReactiveService(
                this.ingredientToIngredientCommand,
                this.ingredientCommandToIngredient,
                this.recipeRepository, this.uomRepository);
    }

    @Test
    public void able_To_Find_Ingredient_By_RecipeId_And_IngredientId() {

        // Given
        this.recipe.addIngredient(ingredient);

        when(this.recipeRepository.findById(this.recipeId))
                .thenReturn(Mono.just(this.recipe));

        IngredientCommand command = new IngredientCommand();

        when(this.ingredientToIngredientCommand.convert(ingredient))
                .thenReturn(command);

        // When
        IngredientCommand ic = this.ingredientService
                .findByRecipeIdAndIngredientId(this.recipeId, this.ingredientId)
                .block();

        // Then
        assertThat(ic, is(command));
    }

    @Test
    public void able_To_Save_IngredientCommand() throws Exception {

        // Given
        String desc = UUID.randomUUID().toString();
        IngredientCommand command = new IngredientCommand();
        command.setRecipeId(this.recipeId);
        command.setId(this.ingredientId);
        command.setDescription(desc);

        this.ingredient.setDescription(desc);

        when(this.recipeRepository.findById(this.recipeId))
                .thenReturn(Mono.just(this.recipe));

        when(this.ingredientCommandToIngredient.convert(command))
                .thenReturn(this.ingredient);

        when(this.recipeRepository.save(this.recipe))
                .thenReturn(Mono.just(this.recipe));

        when(this.ingredientToIngredientCommand.convert(this.ingredient))
                .thenReturn(command);

        // When
        IngredientCommand ic = this.ingredientService
                .saveIngredientCommand(command)
                .block();

        // Then
        verify(this.recipeRepository, times(1))
                .save(this.recipe);

        assertThat(ic, is(command));
    }

    @Test
    public void able_To_Delete_Ingredient_Command() throws Exception {

        // Given
        this.recipe.addIngredient(this.ingredient);

        boolean previouslyHas = this.recipe.getIngredients().contains(
                this.ingredient);

        when(this.recipeRepository.findById(this.recipeId))
                .thenReturn(Mono.just(this.recipe));

        when(this.recipeRepository.save(this.recipe))
                .thenReturn(Mono.just(this.recipe));

        // When
        this.ingredientService.deleteIngredientCommand(this.recipeId,
                this.ingredientId);

        // Then
        assertThat(previouslyHas, is(true));
    }

}///:~