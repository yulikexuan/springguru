//: guru.springframework.converters.NotesToNotesCommand.java


package guru.springframework.converters;


import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {

        if (notes == null) {
            return null;
        }

       return new NotesCommand.Builder()
                .setId(notes.getId())
                .setRecipeNotes(notes.getRecipeNotes())
                .createNotesCommand();
    }

}///~