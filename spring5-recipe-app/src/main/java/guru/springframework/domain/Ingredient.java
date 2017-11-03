//: guru.springframework.domain.Ingredient.java

package guru.springframework.domain;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@EqualsAndHashCode(of = {"description", "recipe"})
@ToString(of = {"id", "description", "recipe"})
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    public Ingredient() {}

}///~