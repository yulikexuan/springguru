//: guru.springframework.restclientapp.api.domain.services.ApiService.java


package guru.springframework.restclientapp.api.domain.services;


import guru.springframework.restclientapp.api.domain.model.User;
import guru.springframework.restclientapp.api.domain.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class ApiService implements IApiService {

	static final String USER_DATA_URI = "http://localhost:8080/api/users";

	private final RestTemplate restTemplate;

	@Autowired
	public ApiService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<User> getUsers() {

		UserData userData = this.restTemplate.getForObject(
				USER_DATA_URI, UserData.class);

		List<User> users = new ArrayList<>();

		if (userData != null) {
			users = userData.getData();
		}

		return users;
	}

}///~