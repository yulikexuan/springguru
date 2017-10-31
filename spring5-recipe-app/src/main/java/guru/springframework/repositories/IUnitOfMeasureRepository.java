//: guru.springframework.repositories.IUnitOfMeasureRepository.java


package guru.springframework.repositories;


import guru.springframework.domain.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//@Repository: not needed as this class extends JpaRepository
public interface IUnitOfMeasureRepository extends
        JpaRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);

}///:~