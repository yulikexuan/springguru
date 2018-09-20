//: guru.springframework.repositories.reactive.ICategoryReactiveRepository.java


package guru.springframework.repositories.reactive;


import guru.springframework.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;


public interface ICategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {

    Mono<Category> findByDescription(String description);

}///:~