//: guru.springframework.converters.NotesCommandToNotesTest.java


package guru.springframework.converters;


import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class NotesCommandToNotesTest {

    private Random random;

    private Long id;
    private String recipeNotes;

    private NotesCommandToNotes converter;

    @Before
    public void setUp() {
        this.random = new Random(System.currentTimeMillis());
        this.id = this.random.nextLong();
        this.recipeNotes = UUID.randomUUID().toString();
        this.converter = new NotesCommandToNotes();
    }

    @Test
    public void can_Generate_Null() throws Exception {
        // When
        Notes notes = this.converter.convert(null);

        // Then
        assertNull(notes);
    }

    @Test
    public void can_Generate_Empty_Object() {
        assertNotNull(this.converter.convert(
                new NotesCommand.Builder().createNotesCommand()));
    }

    @Test
    public void able_To_Converte_NotesCommand_To_Notes() {

        // Given
        NotesCommand command = new NotesCommand.Builder()
                .setId(this.id)
                .setRecipeNotes(this.recipeNotes)
                .createNotesCommand();

        // When
        Notes notes = this.converter.convert(command);

        // Then
        assertThat(notes.getId(), is(this.id));
        assertThat(notes.getRecipeNotes(), is(this.recipeNotes));
    }

}///:~