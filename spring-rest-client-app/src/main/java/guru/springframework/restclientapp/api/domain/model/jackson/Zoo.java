//: guru.springframework.restclientapp.api.domain.model.jackson.Zoo.java


package guru.springframework.restclientapp.api.domain.model.jackson;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Data
public class Zoo {

    private String nsme;
    private String city;

    private List<Animal> animals = new ArrayList<>();

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(this.animals);
    }

    public boolean addAnimal(Animal animal) {
        return this.animals.add(animal);
    }

}///~