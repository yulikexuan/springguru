//: guru.springframework.services.RecipeServiceTest.java


package guru.springframework.services;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IRecipeRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
/*
 * @DataJpaTest: Cannot use DataJpaTest Annotation here because:
 * - Regular @Component beans like converters will not be loaded into the
 *   ApplicationContext, so use @SpringBootTest instead
 */
@SpringBootTest
@Ignore
public class RecipeServiceIT {

    @Autowired
    private IRecipeRepository recipeRepository;

    @Autowired
    private IRecipeService recipeService;

    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;

    /*
     * @Transactional: Run test in transaction, rollback when complete by
     * default when used for Integration Test
     */
    @Transactional
    @Test
    public void able_To_Save_A_RecipeCommand_As_A_Recipe() {

        // Given
        Iterable<Recipe> recipes = this.recipeRepository.findAll();

        Recipe testRecipe = recipes.iterator().next();
        String revicedDesc = "Command : " + testRecipe.getDescription();
        testRecipe.setDescription(revicedDesc);

        RecipeCommand testCommand = this.recipeToRecipeCommand.convert(
                testRecipe);

        // When
        RecipeCommand savedCommand = this.recipeService.saveRecipeCommand(
                testCommand);

        // Then
        assertThat(savedCommand.getDescription(), is(revicedDesc));
        assertThat(testRecipe.getId(), is(savedCommand.getId()));
        assertThat(testRecipe.getCategories().size(),
                is(savedCommand.getCategories().size()));
        assertThat(testRecipe.getIngredients().size(),
                is(savedCommand.getIngredients().size()));
    }



}///:~