//: guru.springframework.restclientapp.api.domain.model.jackson.Elephant.java


package guru.springframework.restclientapp.api.domain.model.jackson;


public class Elephant extends Animal {

    public Elephant(String name) {
        super(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Elephant{");
        sb.append("name: ").append(this.name).append('}');

        return sb.toString();
    }
}///~