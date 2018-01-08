//: guru.springframework.commands.NotesCommand.java


package guru.springframework.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * command (of something)
 *     Your knowledge of something; your ability to do or use something,
 *     especially a language
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(of = "recipeNotes")
public class NotesCommand {

    private String id;
    private String recipeNotes;

    private NotesCommand(String id, String recipeNotes) {
        this.id = id;
        this.recipeNotes = recipeNotes;
    }

    public static final class Builder {

        private String id;
        private String recipeNotes;

        public Builder setId(String id) {
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