//: guru.springframework.repositories.reactive.IUnitOfMeasureReactiveRepository.java


package guru.springframework.repositories.reactive;


import guru.springframework.domain.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface IUnitOfMeasureReactiveRepository extends
        ReactiveMongoRepository<UnitOfMeasure, String> {



}///:~