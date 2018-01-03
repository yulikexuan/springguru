//: guru.springframework.converters.CategoryToCategoryCommandTest.java


package guru.springframework.converters;


import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class CategoryToCategoryCommandTest {

    private Random random;

    private Long id;
    private String description;

    private CategoryToCategoryCommand converter;

    @Before
    public void setUp() {
        this.random = new Random(System.currentTimeMillis());
        this.id = this.random.nextLong();
        this.description = UUID.randomUUID().toString();
        this.converter = new CategoryToCategoryCommand();
    }

    @Test
    public void can_Generate_Null() throws Exception {
        CategoryCommand cc = this.converter.convert(null);
        assertNull(cc);
    }

    @Test
    public void can_Generate_Empty_Object() {
        assertNotNull(this.converter.convert(new Category()));
    }

    @Test
    public void able_To_Convert_Category_To_CategoryCommand() throws Exception {

        // Given
        Category category = new Category();
        category.setId(this.id);
        category.setDescription(this.description);

        // When
        CategoryCommand cc = this.converter.convert(category);

        // Then
        assertThat(this.id, is(cc.getId()));
        assertThat(this.description, is(cc.getDescription()));
    }

}///:~