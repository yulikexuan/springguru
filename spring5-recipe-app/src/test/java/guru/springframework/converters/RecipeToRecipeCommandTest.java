//: guru.springframework.converters.RecipeToRecipeCommandTest.java


package guru.springframework.converters;


import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.*;
import guru.springframework.domain.builders.RecipeBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class RecipeToRecipeCommandTest {

    @Mock
    private NotesToNotesCommand notesConverter;

    @Mock
    private CategoryToCategoryCommand categoryConverter;

    @Mock
    private IngredientToIngredientCommand ingredientConverter;

    private RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.converter = new RecipeToRecipeCommand(this.notesConverter, this.categoryConverter, this.ingredientConverter);
    }

    @Test
    public void can_Convert_To_Null() throws Exception {
        assertThat(this.converter.convert(null), nullValue());
    }

    @Test
    public void can_Convert_Empty_Recipe() throws Exception {
        assertThat(this.converter.convert(new Recipe()), notNullValue());
    }

    @Test
    public void able_To_Convert_Recope_To_RecopeCommand() {

        // Given
        Random random = new Random(System.currentTimeMillis());

        String id = random.nextLong() + "";
        String description = UUID.randomUUID().toString();
        Integer cookTime = random.nextInt();
        Integer prepTime = random.nextInt();
        Difficulty difficulty = Difficulty.values()[random.nextInt(Difficulty.values().length)];
        String directions = UUID.randomUUID().toString();
        Integer servings = random.nextInt();
        String source = UUID.randomUUID().toString();
        String url = UUID.randomUUID().toString();

        Notes notes = mock(Notes.class);
        NotesCommand notesCommand = mock(NotesCommand.class);

        Category[] categoryArr = {mock(Category.class), mock(Category.class), mock(Category.class),};

        CategoryCommand[] categoryCommandArr = {mock(CategoryCommand.class), mock(CategoryCommand.class), mock(CategoryCommand.class),};

        Ingredient[] ingredientArr = {mock(Ingredient.class), mock(Ingredient.class), mock(Ingredient.class),};

        IngredientCommand[] ingredientCommandArr = {mock(IngredientCommand.class), mock(IngredientCommand.class), mock(IngredientCommand.class),};

        Set<Category> categories = Arrays.stream(categoryArr).collect(Collectors.toSet());

        Set<Ingredient> ingredients = Arrays.stream(ingredientArr).collect(Collectors.toSet());

        when(this.categoryConverter.convert(categoryArr[0])).thenReturn(categoryCommandArr[0]);
        when(this.categoryConverter.convert(categoryArr[1])).thenReturn(categoryCommandArr[1]);
        when(this.categoryConverter.convert(categoryArr[2])).thenReturn(categoryCommandArr[2]);

        when(this.ingredientConverter.convert(ingredientArr[0])).thenReturn(ingredientCommandArr[0]);
        when(this.ingredientConverter.convert(ingredientArr[1])).thenReturn(ingredientCommandArr[1]);
        when(this.ingredientConverter.convert(ingredientArr[2])).thenReturn(ingredientCommandArr[2]);

        when(this.notesConverter.convert(notes)).thenReturn(notesCommand);

        Byte[] image = new Byte[10];

        final Recipe recipe = new RecipeBuilder().setId(id).setCookTime(cookTime).setPrepTime(prepTime).setDescription(description).setDifficulty(difficulty).setDirections(directions).setServings(servings).setSource(source).setUrl(url).setNotes(notes).setCategories(categories).setIngredients(ingredients).setImage(image).build();

        // When
        RecipeCommand command = this.converter.convert(recipe);

        // Then
        assertThat(command, notNullValue());
        assertThat(command.getId(), is(id));
        assertThat(command.getCookTime(), is(cookTime));
        assertThat(command.getPrepTime(), is(prepTime));
        assertThat(command.getDescription(), is(description));
        assertThat(command.getDifficulty(), is(difficulty));
        assertThat(command.getDirections(), is(directions));
        assertThat(command.getServings(), is(servings));
        assertThat(command.getSource(), is(source));
        assertThat(command.getUrl(), is(url));
        assertThat(command.getNotes(), is(notesCommand));
        assertThat(command.getImage(), is(image));

        assertThat(command.getCategories().size(), is(categoryCommandArr.length));


        assertThat(command.getIngredients().size(), is(ingredientCommandArr.length));

    }// End of able_To_Convert_Recope_To_RecopeCommand()

}///:~