//: guru.springframework.restclientapp.api.domain.services.ApiServiceIT.java


package guru.springframework.restclientapp.api.domain.services;


import guru.springframework.restclientapp.api.domain.model.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;


/*
 * We should start this web service before running this test,
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiServiceIT {

    @Autowired
    private IApiService apiService;

    @Before
    public void setUp() {
    }

    @Test
    public void able_To_Get_Users() {

        // Given

        // When
        List<User> users = this.apiService.getUsers(3);

        // Then
        assertThat(users.size(), greaterThan(0));
    }

}///:~