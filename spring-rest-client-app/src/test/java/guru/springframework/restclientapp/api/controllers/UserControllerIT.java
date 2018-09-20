//: guru.springframework.restclientapp.api.controllers.UserControllerTest.java

package guru.springframework.restclientapp.api.controllers;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerIT {

    @Autowired
    private ApplicationContext applicationContext;

    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        this.webTestClient = WebTestClient.bindToApplicationContext(this.applicationContext).build();
    }

    @Test
    public void having_Index_Page() {
        this.webTestClient.get().uri("/").exchange().expectStatus().isOk();
    }

    @Test
    public void able_To_Response_A_List_Of_Users_For_Post_Request() {

        // Given
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("limit", Integer.toString(3));

        // When
        this.webTestClient.post().uri("users").contentType(MediaType.APPLICATION_FORM_URLENCODED).body(BodyInserters.fromFormData(formData)).exchange().expectStatus().isOk();
    }

}///:~