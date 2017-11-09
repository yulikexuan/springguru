//: guru.springframework.domain.Notes.java

package guru.springframework.domain;


import lombok.*;

import javax.persistence.*;


@Data
@ToString(of = {"recipe", "recipeNotes"})
@EqualsAndHashCode(of = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "notes")
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}///~