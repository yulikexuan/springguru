//: guru.springframework.reactiveexamples.Person.java


package guru.springframework.reactiveexamples;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String firstName;
    private String lastName;

    public String sayMyName() {
        return "My name is " + this.firstName + " " + this.lastName + ".";
    }

}///~