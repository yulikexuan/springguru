//: guru.springframework.repositories.reactive.IRecipeReactiveRepositoryIT.java


package guru.springframework.repositories.reactive;


import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataMongoTest
public class IRecipeReactiveRepositoryIT {

    @Autowired
    private IRecipeReactiveRepository recipeReactiveRepository;

    @Before
    public void setUp() throws Exception {
        this.recipeReactiveRepository.deleteAll().then().block();
    }

    @Test
    public void able_To_Save_New_Recipe() throws Exception {

        // Given
        String desc = UUID.randomUUID().toString();
        Recipe recipe = new Recipe();
        recipe.setDescription(desc);

        this.recipeReactiveRepository.save(recipe).then().block();

        // When
        Long count = this.recipeReactiveRepository.count().block();

        // Then
        assertThat(count, is(1L));
    }

}///~