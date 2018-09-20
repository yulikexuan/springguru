//: guru.springframework.restclientapp.api.domain.model.jackson.ZooListSerializationTest.java


package guru.springframework.restclientapp.api.domain.model.jackson;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ZooListSerializationTest {

    private Zoo zoo;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {

        this.objectMapper = new ObjectMapper();
        //		this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT,
        //				true);

        this.zoo = new Zoo();
        this.zoo.setNsme("London Zoo");
        this.zoo.setCity("London");
        this.zoo.addAnimal(new Lion("Simba"));
        this.zoo.addAnimal(new Elephant("Manny"));
    }

    @Ignore
    @Test //(expected = InvalidDefinitionException.class)
    public void serialize_List_Element_Without_Type_Info() throws Exception {

        // Given
        List<Animal> animals = new ArrayList<Animal>();
        Lion lion = new Lion("Samba");
        Elephant elephant = new Elephant("Manny");
        animals.add(lion);
        animals.add(elephant);
        String animalsJson = this.objectMapper.writerFor(new TypeReference<Animal>() {
        }).writeValueAsString(animals);
        System.out.println(animalsJson);

        // When
        //		Zoo zooCopy = this.objectMapper.readValue(this.objectMapper.writeValueAsString(this.zoo), Zoo.class);

        // Then
    }

}///:~