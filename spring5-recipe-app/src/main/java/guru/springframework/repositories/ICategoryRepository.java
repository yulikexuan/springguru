//: guru.springframework.repositories.ICategoryRepository.java


package guru.springframework.repositories;


import guru.springframework.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


//@Repository: not needed as this class extends JpaRepository
public interface ICategoryRepository extends CrudRepository<Category, String> {

    Optional<Category> findByDescription(String description);

}///:~