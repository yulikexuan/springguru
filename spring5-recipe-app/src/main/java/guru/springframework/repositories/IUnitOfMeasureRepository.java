//: guru.springframework.repositories.IUnitOfMeasureRepository.java


package guru.springframework.repositories;


import guru.springframework.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


//@Repository: not needed as this class extends JpaRepository
public interface IUnitOfMeasureRepository extends
        CrudRepository<UnitOfMeasure, String> {

    Optional<UnitOfMeasure> findByDescription(String description);

}///:~