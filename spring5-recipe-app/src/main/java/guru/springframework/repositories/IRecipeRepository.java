//: guru.springframework.repositories.IRecipeRepository.java


package guru.springframework.repositories;


import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;


//@Repository: not needed as this class extends JpaRepository
public interface IRecipeRepository extends CrudRepository<Recipe, String> {

}///:~