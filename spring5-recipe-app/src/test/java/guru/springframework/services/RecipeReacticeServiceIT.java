//: guru.springframework.services.RecipeReactiveServiceTest.java


package guru.springframework.services;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.reactive.IRecipeReactiveRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
/*
 * @DataJpaTest: Cannot use DataJpaTest Annotation here because:
 * - Regular @Component beans like converters will not be loaded into the
 *   ApplicationContext, so use @SpringBootTest instead
 */
@SpringBootTest
public class RecipeReacticeServiceIT {

    @Autowired
    private IRecipeReactiveRepository recipeReacticeRepository;

    @Autowired
    private IRecipeReactiveService recipeReactiveService;

    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;

    /*
     * @Transactional: Run test in transaction, rollback when complete by
     * default when used for Integration Test
     */
    @Test
    public void able_To_Save_A_RecipeCommand_As_A_Recipe() {

        // Given
        Recipe recipe = this.recipeReacticeRepository.findAll().blockFirst();

        String revicedDesc = "Command : " + recipe.getDescription();
        recipe.setDescription(revicedDesc);

        RecipeCommand rc = this.recipeToRecipeCommand.convert(recipe);

        // When
        RecipeCommand savedCommand = this.recipeReactiveService
                .saveRecipeCommand(rc)
                .block();

        // Then
        assertThat(savedCommand.getDescription(), is(revicedDesc));
        assertThat(recipe.getId(), is(savedCommand.getId()));
        assertThat(recipe.getCategories().size(),
                is(savedCommand.getCategories().size()));
        assertThat(recipe.getIngredients().size(),
                is(savedCommand.getIngredients().size()));
    }

}///:~