//: guru.springframework.commands.NotesCommand.java


package guru.springframework.commands;


import lombok.Getter;
import lombok.ToString;

/*
 * command (of something)
 *     Your knowledge of something; your ability to do or use something,
 *     especially a language
 */
@Getter
@ToString(of = "recipeNotes")
public class NotesCommand {

    private Long id;
    private String recipeNotes;

    private NotesCommand(Long id, String recipeNotes) {
        this.id = id;
        this.recipeNotes = recipeNotes;
    }

    public static final class Builder {

        private Long id;
        private String recipeNotes;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setRecipeNotes(String recipeNotes) {
            this.recipeNotes = recipeNotes;
            return this;
        }

        public NotesCommand createNotesCommand() {
            return new NotesCommand(id, recipeNotes);
        }
    }

}///~