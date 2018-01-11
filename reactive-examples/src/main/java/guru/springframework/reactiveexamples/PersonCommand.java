//: guru.springframework.reactiveexamples.PersonCommand.java


package guru.springframework.reactiveexamples;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
public class PersonCommand {

    private String firstName;
    private String lastName;

    public PersonCommand(@NonNull Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
    }

    public String sayMyName() {
        return "My name is " + this.firstName + " " + this.lastName + ".";
    }

}///~