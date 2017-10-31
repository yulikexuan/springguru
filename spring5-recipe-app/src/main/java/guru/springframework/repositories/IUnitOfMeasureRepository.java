//: guru.springframework.repositories.IUnitOfMeasureRepository.java


package guru.springframework.repositories;


import guru.springframework.domain.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;


//@Repository
public interface IUnitOfMeasureRepository extends
        JpaRepository<UnitOfMeasure, Long> {

}///:~