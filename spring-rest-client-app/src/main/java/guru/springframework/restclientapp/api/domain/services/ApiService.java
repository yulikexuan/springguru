//: guru.springframework.restclientapp.api.domain.services.ApiService.java


package guru.springframework.restclientapp.api.domain.services;


import guru.springframework.restclientapp.api.domain.model.User;
import guru.springframework.restclientapp.api.domain.model.UserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ApiService implements IApiService {

    // static final String USER_DATA_URI = "http://localhost:8080/api/users";

    // private final String api_uri_scheme;
    private final String api_uri_users;

    private final RestTemplate restTemplate;

    /*
     * @Value("${}") other than @Value("$()")
     */
    @Autowired
    public ApiService(RestTemplate restTemplate,
            //	                  @Value("${api.uri.scheme}") String api_uri_scheme,
                      @Value("${api.uri.users}") String api_uri_users) {

        this.restTemplate = restTemplate;
        //		this.api_uri_scheme = api_uri_scheme;
        this.api_uri_users = api_uri_users;
    }

    @Override
    public List<User> getUsers(int limit) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(this.api_uri_users).queryParam("limit", limit);

        //		A different way to generate uri
        //		UriComponents uriComponents = UriComponentsBuilder
        //				.newInstance()
        //				.scheme(this.api_uri_scheme)
        //				.host(this.api_uri_users)
        //				.queryParam("limit", limit)
        //				.build();
        //		String uriStr = uriComponents.toUriString();

        String uriStr = uriComponentsBuilder.toUriString();
        log.debug(">>>>>>> The uri to get data of users: " + uriStr);

        UserData userData = this.restTemplate.getForObject(uriStr, UserData.class);

        List<User> users = new ArrayList<>();

        if (userData != null) {
            users = userData.getData();
        }

        return users;
    }

    @Override
    public Flux<User> getUsers(Mono<Integer> limit) {

        return WebClient.create(this.api_uri_users).get().uri(uriBuilder -> uriBuilder.queryParam("limit", limit.block()).build()).accept(MediaType.APPLICATION_JSON).exchange().flatMap(r -> r.bodyToMono(UserData.class)).flatMapIterable(UserData::getData);
    }

}///~