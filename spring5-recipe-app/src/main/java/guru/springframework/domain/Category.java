//: guru.springframework.domain.Category.java

package guru.springframework.domain;


import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"description"})
@ToString(of = "description")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}///~