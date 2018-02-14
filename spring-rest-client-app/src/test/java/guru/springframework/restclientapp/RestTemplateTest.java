//: guru.springframework.restclientapp.RestTemplateExamples.java


package guru.springframework.restclientapp;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;

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



	}

}///~