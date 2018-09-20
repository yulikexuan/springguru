//: guru.springframework.restclientapp.api.domain.services.IApiService.java


package guru.springframework.restclientapp.api.domain.services;


import guru.springframework.restclientapp.api.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public interface IApiService {

    List<User> getUsers(int limit);

    Flux<User> getUsers(Mono<Integer> limit);

}///:~