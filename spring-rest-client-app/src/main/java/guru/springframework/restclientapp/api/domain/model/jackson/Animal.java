//: guru.springframework.restclientapp.api.domain.model.jackson.Animal.java


package guru.springframework.restclientapp.api.domain.model.jackson;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;


@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY,
		property = "@class")
@JsonSubTypes({
		@JsonSubTypes.Type(value = Lion.class, name = "lion"),
		@JsonSubTypes.Type(value = Elephant.class, name = "elephant")
})
public abstract class Animal {

	String name;

	public Animal(String nema) {
		this.name = nema;
	}

}///~