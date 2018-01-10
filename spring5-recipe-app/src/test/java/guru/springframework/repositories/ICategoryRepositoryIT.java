//: guru.springframework.repositories.ICategoryRepositoryTest.java


package guru.springframework.repositories;


import guru.springframework.bootstrap.RecipeBootstrap;
import guru.springframework.domain.builders.IngredientBuilder;
import guru.springframework.domain.builders.RecipeBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;


@DataMongoTest
@RunWith(SpringRunner.class)
public class ICategoryRepositoryIT {

    @Autowired
    private IUnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    private IRecipeRepository recipeRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        this.categoryRepository.deleteAll();
        this.unitOfMeasureRepository.deleteAll();
        this.recipeRepository.deleteAll();
        RecipeBootstrap recipeBootstrap = new RecipeBootstrap(
                this.recipeRepository, this.categoryRepository,
                this.unitOfMeasureRepository, new RecipeBuilder(),
                new IngredientBuilder());
        recipeBootstrap.onApplicationEvent(null);
    }

    @Test
    public void findByDescription() throws Exception {

        // Given
        String[] categories = {
                "American", "Italian", "Mexican", "Fast Food",
        };

        // When
        Arrays.stream(categories)
                .forEach(c -> assertTrue(categoryRepository
                        .findByDescription(c).isPresent()));
    }

}///:~