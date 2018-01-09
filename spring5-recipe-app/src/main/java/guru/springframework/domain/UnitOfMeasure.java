//: guru.springframework.domain.UnitOfMeasure.java

package guru.springframework.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@ToString(of = {"id", "description"})
@EqualsAndHashCode(of = {"id", "description"})
@Document
public class UnitOfMeasure {
    @Id
    private String id;
    private String description;
}///~