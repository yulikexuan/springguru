//: guru.springframework.repositories.ICategoryRepository.java


package guru.springframework.repositories;


import guru.springframework.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//@Repository: not needed as this class extends JpaRepository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByDescription(String description);

}///:~