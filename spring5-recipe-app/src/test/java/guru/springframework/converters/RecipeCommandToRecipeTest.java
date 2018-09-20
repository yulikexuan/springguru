//: guru.springframework.converters.RecipeCommandToRecipeTest.java


package guru.springframework.converters;


import guru.springframework.commands.*;
import guru.springframework.domain.*;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class RecipeCommandToRecipeTest {

    private Random random;

    private String id;
    private String description;
    private Integer cookTime;
    private Integer prepTime;
    private Difficulty difficulty;
    private String directions;
    private Integer servings;
    private String source;
    private String url;

    @Mock
    private Category category_0;

    @Mock
    private Category category_1;

    @Mock
    private Category category_2;

    @Mock
    private CategoryCommand categoryCommand_0;

    @Mock
    private CategoryCommand categoryCommand_1;

    @Mock
    private CategoryCommand categoryCommand_2;

    @Mock
    private CategoryCommandToCategory categoryConverter;

    @Mock
    private Ingredient ingtedient_0;

    @Mock
    private Ingredient ingtedient_1;

    @Mock
    private Ingredient ingtedient_2;

    @Mock
    private IngredientCommand ingredientCommand_0;

    @Mock
    private IngredientCommand ingredientCommand_1;

    @Mock
    private IngredientCommand ingredientCommand_2;

    @Mock
    private IngredientCommandToIngredient ingredientConverter;

    @Mock
    private Notes notes;

    @Mock
    private NotesCommand notesCommand;

    @Mock
    private NotesCommandToNotes notesConverter;

    private RecipeCommandToRecipe converter;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        this.random = new Random(System.currentTimeMillis());

        this.id = this.random.nextLong() + "";
        this.description = UUID.randomUUID().toString();
        this.cookTime = this.random.nextInt();
        this.prepTime = this.random.nextInt();
        this.difficulty = Difficulty.values()[this.random.nextInt(Difficulty.values().length)];
        this.directions = UUID.randomUUID().toString();
        this.servings = this.random.nextInt();
        this.source = UUID.randomUUID().toString();
        this.url = UUID.randomUUID().toString();

        this.converter = new RecipeCommandToRecipe(this.notesConverter, this.categoryConverter, this.ingredientConverter);
    }

    @Test
    public void can_Generate_Null() throws Exception {
        // When
        Recipe recipe = this.converter.convert(null);

        // Then
        assertNull(recipe);
    }

    @Test
    public void can_Generate_Empty_Object() {
        assertNotNull(this.converter.convert(new RecipeCommand.Builder().createRecipeCommand()));
    }

    @Test
    public void able_To_Converte_RecipeCommand_To_Recipe() {

        // Given
        Set<CategoryCommand> categoryCommands = new HashSet<>();
        categoryCommands.add(this.categoryCommand_0);
        categoryCommands.add(this.categoryCommand_1);
        categoryCommands.add(this.categoryCommand_2);

        Set<IngredientCommand> ingredientCommands = new HashSet<>();
        ingredientCommands.add(this.ingredientCommand_0);
        ingredientCommands.add(this.ingredientCommand_1);
        ingredientCommands.add(this.ingredientCommand_2);

        RecipeCommand command = new RecipeCommand.Builder().setId(this.id).setDescription(this.description).setCookTime(this.cookTime).setPrepTime(this.prepTime).setDifficulty(this.difficulty).setDirections(this.directions).setServings(this.servings).setSource(this.source).setUrl(this.url).setNotes(this.notesCommand).setCategories(categoryCommands).setIngredients(ingredientCommands).createRecipeCommand();

        when(this.notesConverter.convert(this.notesCommand)).
                thenReturn(this.notes);

        when(this.categoryConverter.convert(this.categoryCommand_0)).
                thenReturn(this.category_0);
        when(this.categoryConverter.convert(this.categoryCommand_1)).
                thenReturn(this.category_1);
        when(this.categoryConverter.convert(this.categoryCommand_2)).
                thenReturn(this.category_2);

        when(this.ingredientConverter.convert(this.ingredientCommand_0)).thenReturn(this.ingtedient_0);
        when(this.ingredientConverter.convert(this.ingredientCommand_1)).thenReturn(this.ingtedient_1);
        when(this.ingredientConverter.convert(this.ingredientCommand_2)).thenReturn(this.ingtedient_2);

        // When
        Recipe recipe = this.converter.convert(command);

        // Then
        assertThat(recipe.getId(), is(this.id));
        assertThat(recipe.getDescription(), is(this.description));
        assertThat(recipe.getCookTime(), is(this.cookTime));
        assertThat(recipe.getPrepTime(), is(this.prepTime));
        assertThat(recipe.getDifficulty(), is(this.difficulty));
        assertThat(recipe.getDirections(), is(this.directions));
        assertThat(recipe.getServings(), is(this.servings));
        assertThat(recipe.getSource(), is(this.source));
        assertThat(recipe.getUrl(), is(this.url));
        assertThat(recipe.getNotes(), is(this.notes));
        assertThat(recipe.getCategories().size(), is(3));
        assertThat(recipe.getIngredients().size(), is(3));
        assertThat(recipe.getCategories().contains(this.category_0), is(true));
        assertThat(recipe.getCategories().contains(this.category_1), is(true));
        assertThat(recipe.getCategories().contains(this.category_2), is(true));
        assertThat(recipe.getIngredients().contains(this.ingtedient_0), is(true));
        assertThat(recipe.getIngredients().contains(this.ingtedient_1), is(true));
        assertThat(recipe.getIngredients().contains(this.ingtedient_2), is(true));
    }

}///:~