//: guru.springframework.domain.Notes.java

package guru.springframework.domain;


import lombok.*;


@Data
@ToString(of = {"recipe", "recipeNotes"})
@EqualsAndHashCode(of = {"recipe"})
public class Notes {
    private String id;
    private Recipe recipe;
    private String recipeNotes;
}///~