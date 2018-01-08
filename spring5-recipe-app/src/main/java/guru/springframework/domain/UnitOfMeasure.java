//: guru.springframework.domain.UnitOfMeasure.java

package guru.springframework.domain;


import lombok.*;


@Data
@ToString(of = {"id", "description"})
@EqualsAndHashCode(of = {"id", "description"})
public class UnitOfMeasure {
    private String id;
    private String description;
}///~