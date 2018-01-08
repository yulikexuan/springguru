//: guru.springframework.domain.Category.java

package guru.springframework.domain;


import lombok.*;

import java.util.Set;


@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"description"})
@ToString(of = "description")
public class Category {
    private String id;
    private String description;
    private Set<Recipe> recipes;
}///~