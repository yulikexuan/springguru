//: guru.springframework.repositories.IIngredientRepository.java


package guru.springframework.repositories;


import guru.springframework.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//@Repository: not needed as this class extends JpaRepository
public interface IIngredientRepository extends JpaRepository<Ingredient, Long> {

    Optional<Ingredient> findByDescription(String description);

}///:~