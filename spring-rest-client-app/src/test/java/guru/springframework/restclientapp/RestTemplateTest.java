//: guru.springframework.restclientapp.RestTemplateExamples.java


package guru.springframework.restclientapp;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;


@Slf4j
public class RestTemplateTest {

	static final String API_ROOT = "https://api.predic8.de:443/shop";

	private RestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {

		URL url = new URL("https://api.predic8.de/");
		System.out.println(url.getHost());

		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.connect();

		if (con.getResponseCode() != SC_OK) {
			throw new RuntimeException("No internet is available!");
		}

		this.restTemplate = new RestTemplate();
	}

	@Test
	public void getCategories() {

		// Given
		String apiUrl = API_ROOT + "/categories/";

		// When
		JsonNode jsonNode = this.restTemplate.getForObject(apiUrl,
				JsonNode.class);

		// Then
		System.out.println(">>>>>>> Response: " + jsonNode.toString());
		assertThat(jsonNode.get("categories").size(), greaterThan(0));

	}// End of getCategories

	@Test
	public void getCustomers() throws Exception {

		// Given
		String apiUrl = API_ROOT + "/customers/";

		// When
		JsonNode jsonNode = this.restTemplate.getForObject(apiUrl,
				JsonNode.class);

		// Then
		System.out.println(jsonNode.toString());
		assertThat(jsonNode.get("customers").size(), greaterThan(0));
	}

	@Test
	public void create_Customers() throws Exception {

		// Given
		String apiUrl = API_ROOT + "/customers/";

		Map<String, String> postMap = new HashMap<>();
		postMap.put("firstname", "Bill");
		postMap.put("lastname", "Gates");

		// When
		JsonNode jsonNode = this.restTemplate.postForObject(apiUrl, postMap,
				JsonNode.class);

		// Theen
		System.out.println(jsonNode.toString());
	}

	@Test
	public void update_Customers() {

		// Given
		String apiUrl = API_ROOT + "/customers/";     
		                                              
		Map<String, String> postMap = new HashMap<>();
		postMap.put("firstname", "Mike");
		postMap.put("lastname", "Lee");
		JsonNode jsonNode = this.restTemplate.postForObject(apiUrl, postMap,
				JsonNode.class);

		String newCustomerUrl = jsonNode.get("customer_url").textValue();
		String id = newCustomerUrl.split("/")[3];

		System.out.println("Created customer id is " + id);

		postMap.clear();
		postMap.put("firstname", "Wengui");
		postMap.put("lastname", "Guo");

		// When
		this.restTemplate.put(apiUrl + id, postMap);

		// Then
		JsonNode updateJsonNode = this.restTemplate.getForObject(
				apiUrl + id, JsonNode.class);
		System.out.println(updateJsonNode);
	}

	@Test
	public void delete_Customer() throws Exception {

		// Given
		String apiUrl = API_ROOT + "/customers/";

		Map<String, String> postMap = new HashMap<>();
		postMap.put("firstname", UUID.randomUUID().toString());
		postMap.put("lastname", UUID.randomUUID().toString());
		JsonNode jsonNode = this.restTemplate.postForObject(apiUrl, postMap,
				JsonNode.class);

		String newCustomerUrl = jsonNode.get("customer_url").textValue();
		String id = newCustomerUrl.split("/")[3];

		System.out.println("Created customer id is " + id);

		// When
		this.restTemplate.delete(apiUrl + id);

		// Then

		JsonNode updateJsonNode = null;
		try {
			updateJsonNode = this.restTemplate.getForObject(
					apiUrl + id, JsonNode.class);
			System.out.println(updateJsonNode);
		} catch (HttpClientErrorException e) {
			int sc = e.getRawStatusCode();
			System.out.println(sc);
		}
	}

}///~