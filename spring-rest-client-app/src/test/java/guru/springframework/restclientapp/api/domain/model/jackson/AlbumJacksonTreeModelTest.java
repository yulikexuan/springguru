//: guru.springframework.restclientapp.api.domain.model.jackson.AlbumJacksonTreeModelTest.java


package guru.springframework.restclientapp.api.domain.model.jackson;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


public class AlbumJacksonTreeModelTest {

	private JsonGenerator jsonGenerator;
	private ObjectMapper objectMapper;
	private JsonNodeFactory jsonNodeFactory;

	/*
	 * JsonNode is the base class for all JSON nodes, which form the basis of
	 * JSON Tree Model that Jackson implements
	 * One way to think of these nodes is to consider them similar to DOM nodes
	 * in XML DOM trees
	 *
	 * ObjectNode is a JsonNode that maps to JSON Object structures in JSON
	 * content
	 */
	private ObjectNode albumJsonNode;
	private ObjectNode artistJsonNode;

	@Before
	public void setUp() throws IOException {

		/*
		 * The main factory class of Jackson package, used to configure and
		 * construct reader (aka parser, JsonParser) and writer (aka generator,
		 * JsonGenerator) instances.
		 */
		JsonFactory jsonFactory = new JsonFactory();

		/*
		 * Base class that defines public API for writing JSON content.
		 * Instances are created using factory methods of a JsonFactory instance.
		 */
		this.jsonGenerator = jsonFactory.createGenerator(System.out);
		this.objectMapper = new ObjectMapper();

		// Create the node factory to create nodes
		this.jsonNodeFactory = new JsonNodeFactory(false);
		this.albumJsonNode = this.jsonNodeFactory.objectNode();
		this.artistJsonNode = this.jsonNodeFactory.objectNode();
	}

	@Test
	public void write_Empty_Node() throws Exception {

		// Given
		// When
		this.objectMapper.writeTree(this.jsonGenerator, this.albumJsonNode);
	}

	@Test
	public void write_Node_With_Primary_Properties_Inside() throws Exception {

		// Given
		this.albumJsonNode.put("Album-Title", "Kind of Blue");

		// When
		this.objectMapper.writeTree(this.jsonGenerator, this.albumJsonNode);
	}

	@Test
	public void write_Json_Node_With_Array_Property() throws Exception {

		// Given
		ArrayNode linksArrayNode = this.jsonNodeFactory.arrayNode();
		linksArrayNode.add("link1").add("link2");
		this.albumJsonNode.set("links", linksArrayNode);

		// When
		this.objectMapper.writeTree(this.jsonGenerator, this.albumJsonNode);
	}

	@Test
	public void write_Json_Node_With_Object_Property() throws Exception {

		// Given
		this.artistJsonNode.put("name", "Whiles Davis")
				.put("birthDay", "26 May 1926");
		this.albumJsonNode.set("artist", this.artistJsonNode);

		// When
		this.objectMapper.writeTree(this.jsonGenerator, this.artistJsonNode);
		this.objectMapper.writeTree(this.jsonGenerator, this.albumJsonNode);
	}

	@Test
	public void write_Json_Node_With_Map_Property() throws Exception {

		// Given
		ObjectNode musicians = this.jsonNodeFactory.objectNode();

		musicians.put("Julian Adderley", "Alto Saxophone");
		musicians.put("Miles Davis", "Trumpet, Band leader");

		this.albumJsonNode.set("musicians", musicians);

		// When
		this.objectMapper.writeTree(this.jsonGenerator, this.albumJsonNode);
	}

}///~