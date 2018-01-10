//: guru.springframework.repositories.IUnitOfMeasureRepositoryIT.java


package guru.springframework.repositories;


import guru.springframework.bootstrap.RecipeBootstrap;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.domain.builders.IngredientBuilder;
import guru.springframework.domain.builders.RecipeBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/*
 *
 */
@DataMongoTest
@RunWith(SpringRunner.class)
public class IUnitOfMeasureRepositoryIT {

    @Autowired
    private IUnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    private IRecipeRepository recipeRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    private RecipeBuilder recipeBuilder;

    private IngredientBuilder ingredientBuilder;

    @Before
    public void setUp() throws Exception {

        this.recipeBuilder = new RecipeBuilder();
        this.ingredientBuilder = new IngredientBuilder();

        this.categoryRepository.deleteAll();
        this.unitOfMeasureRepository.deleteAll();
        this.recipeRepository.deleteAll();

        RecipeBootstrap recipeBootstrap = new RecipeBootstrap(
                this.recipeRepository, this.categoryRepository,
                this.unitOfMeasureRepository, this.recipeBuilder,
                this.ingredientBuilder);
        recipeBootstrap.onApplicationEvent(null);
    }

    @Test
    public void should_Have_Teaspoon_UOM() throws Exception {

        // Given
        String uom = "Teaspoon";

        // When
        Optional<UnitOfMeasure> uomOptional =
                this.unitOfMeasureRepository.findByDescription(uom);

        // Then
        assertThat(uom, is(uomOptional.get().getDescription()));
    }

    @Test
    public void should_Have_Tablespoon_UOM() throws Exception {

        // Given
        String uom = "Tablespoon";

        // When
        Optional<UnitOfMeasure> uomOptional =
                this.unitOfMeasureRepository.findByDescription(uom);

        // Then
        assertThat(uom, is(uomOptional.get().getDescription()));
    }

}///:~