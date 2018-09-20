//: guru.springframework.repositories.reactive.IRecipeReactiveRepository.java


package guru.springframework.repositories.reactive;


import guru.springframework.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface IRecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {

}///:~