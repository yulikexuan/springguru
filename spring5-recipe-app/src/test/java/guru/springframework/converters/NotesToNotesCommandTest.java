//: guru.springframework.converters.NotesToNotesCommandTest.java


package guru.springframework.converters;


import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class NotesToNotesCommandTest {

    private Random random;

    private Long id;
    private String recipeNotes;

    private NotesToNotesCommand converter;

    @Before
    public void setUp() {
        this.random = new Random(System.currentTimeMillis());
        this.id = this.random.nextLong();
        this.recipeNotes = UUID.randomUUID().toString();
        this.converter = new NotesToNotesCommand();
    }

    @Test
    public void can_Generate_Null() throws Exception {
        // When
        NotesCommand command = this.converter.convert(null);

        // Then
        assertNull(command);
    }

    @Test
    public void can_Generate_Empty_Object() {
        assertNotNull(this.converter.convert(new Notes()));
    }

    @Test
    public void able_To_Converte_Notes_To_NotesCommand() {

        // Given
        Notes notes = new Notes();
        notes.setId(this.id);
        notes.setRecipeNotes(this.recipeNotes);

        // When
        NotesCommand command = this.converter.convert(notes);

        // Then
        assertThat(command.getId(), is(this.id));
        assertThat(command.getRecipeNotes(), is(this.recipeNotes));
    }

}///:~