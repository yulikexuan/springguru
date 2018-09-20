//: guru.springframework.repositories.reactive.IIngredientReactiveRepository.java


package guru.springframework.repositories.reactive;


import guru.springframework.domain.Ingredient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;


public interface IIngredientReactiveRepository extends ReactiveMongoRepository<Ingredient, String> {

    Mono<Ingredient> findByDescription(String description);

}///:~