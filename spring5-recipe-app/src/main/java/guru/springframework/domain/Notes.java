//: guru.springframework.domain.Notes.java

package guru.springframework.domain;


import lombok.*;
import org.springframework.data.annotation.Id;


@Data
@ToString(of = {"recipe", "recipeNotes"})
@EqualsAndHashCode(of = {"recipe"})
public class Notes {

    @Id
    private String id;
    private Recipe recipe;
    private String recipeNotes;
}///~