//: guru.springframework.domain.Category.java

package guru.springframework.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;


@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"description"})
@ToString(of = "description")
@Document
public class Category {

    @Id
    private String id;

    private String description;

    @DBRef
    private Set<Recipe> recipes;

}///~