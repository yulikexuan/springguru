//: guru.springframework.converters.CategoryCommandToCategoryTest.java


package guru.springframework.converters;


import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class CategoryCommandToCategoryTest {

    private Random random;

    private Long id;
    private String description;

    private CategoryCommandToCategory converter;

    @Before
    public void setUp() {
        this.random = new Random(System.currentTimeMillis());
        this.id = this.random.nextLong();
        this.description = UUID.randomUUID().toString();
        this.converter = new CategoryCommandToCategory();
    }

    @Test
    public void can_Generate_Null() throws Exception {
        // When
        Category category = this.converter.convert(null);

        // Then
        assertNull(category);
    }

    @Test
    public void can_Generate_Empty_Object() {
        assertNotNull(this.converter.convert(
                new CategoryCommand.Builder().createCategoryCommand()));
    }

    @Test
    public void able_To_Converte_CategoryCommand_To_Category() {

        // Given
        CategoryCommand command = new CategoryCommand.Builder()
                .setId(this.id)
                .setDescription(this.description)
                .createCategoryCommand();

        // When
        Category category = this.converter.convert(command);

        // Then
        assertThat(category.getId(), is(this.id));
        assertThat(category.getDescription(), is(this.description));

    }

}///:~