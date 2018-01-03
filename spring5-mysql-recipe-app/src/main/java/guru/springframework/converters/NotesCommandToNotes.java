//: guru.springframework.converters.NotesCommandToNotes.java


package guru.springframework.converters;


import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand command) {

        if (command == null) {
            return null;
        }

        final Notes notes = new Notes();

        notes.setId(command.getId());
        notes.setRecipeNotes(command.getRecipeNotes());

        return notes;
    }

}///~