//: guru.springframework.repositories.IIngredientRepository.java


package guru.springframework.repositories;


import guru.springframework.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


//@Repository: not needed as this class extends JpaRepository
public interface IIngredientRepository extends
        CrudRepository<Ingredient, String> {

    Optional<Ingredient> findByDescription(String description);

}///:~