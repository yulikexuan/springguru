//: guru.springframework.domain.CategoryTest.java


package guru.springframework.domain;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CategoryTest {

    private Category category;

    @Before
    public void setup() {
        this.category = new Category();
    }

    @Test
    public void getId() throws Exception {
        // Arrange
        Long id = 4L;
        this.category.setId(4L);

        // Action & Assert
        assertEquals(id, this.category.getId());
    }

    @Test
    public void getDescription() throws Exception {
    }

    @Test
    public void getRecipes() throws Exception {
    }

}///:~