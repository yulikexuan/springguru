//: guru.springframework.repositories.ICategoryRepositoryTest.java


package guru.springframework.repositories;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;


@DataJpaTest
@RunWith(SpringRunner.class)
public class ICategoryRepositoryIT {

    @Autowired
    private ICategoryRepository categoryRepository;

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