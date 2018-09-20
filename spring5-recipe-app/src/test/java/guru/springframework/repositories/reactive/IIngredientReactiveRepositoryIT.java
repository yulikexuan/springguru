//: guru.springframework.repositories.reactive.IIngredientReactiveRepositoryIT.java


package guru.springframework.repositories.reactive;


import guru.springframework.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


@DataMongoTest
@RunWith(SpringRunner.class)
public class IIngredientReactiveRepositoryIT {

    @Autowired
    IIngredientReactiveRepository ingredientReactiveRepository;

    private String desc;
    private Ingredient ingredient;

    @Before
    public void setUp() throws Exception {
        this.ingredientReactiveRepository.deleteAll().block();
        this.desc = UUID.randomUUID().toString();
        this.ingredient = new Ingredient();
        this.ingredient.setDescription(this.desc);
    }

    @Test
    public void able_To_Save_A_New_Ingredient() {

        // Given

        // When
        this.ingredientReactiveRepository.save(ingredient).block();

        // Then
        assertThat(this.ingredientReactiveRepository.count().block(), is(1L));
    }

    @Test
    public void findByDescription() throws Exception {

        // Given
        this.ingredientReactiveRepository.save(ingredient).block();

        // When
        Ingredient targetIngredient = this.ingredientReactiveRepository.findByDescription(this.desc).block();

        // Then
        assertThat(targetIngredient.getDescription(), is(this.desc));
    }

}///:~