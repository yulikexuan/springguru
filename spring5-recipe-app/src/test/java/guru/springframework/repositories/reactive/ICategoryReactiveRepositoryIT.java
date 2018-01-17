//: guru.springframework.repositories.reactive.ICategoryReactiveRepositoryIT.java


package guru.springframework.repositories.reactive;


import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataMongoTest
public class ICategoryReactiveRepositoryIT {

    public static final String CHINESE = "Chinese";

    @Autowired
    ICategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        this.categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void able_To_Save_New_Category() throws Exception {

        // Given
        Category category = new Category();
        category.setDescription(CHINESE);

        this.categoryReactiveRepository.save(category).block();

        // When
        Long count = this.categoryReactiveRepository.count().block();

        // Then
        assertThat(count, is(1L));
    }

    @Test
    public void able_To_Query_Category_By_Desc() throws Exception {

        // Given
        Category category = new Category();
        category.setDescription(CHINESE);

        this.categoryReactiveRepository.save(category).then().block();

        // When
        Category result = this.categoryReactiveRepository
                .findByDescription(CHINESE)
                .block();

        // Then
        assertThat(result.getDescription(), is(CHINESE));
    }

}///~