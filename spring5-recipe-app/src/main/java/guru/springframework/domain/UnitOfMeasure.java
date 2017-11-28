//: guru.springframework.domain.UnitOfMeasure.java

package guru.springframework.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@ToString(of = {"id", "description"})
@EqualsAndHashCode(of = {"id", "description"})
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

}///~