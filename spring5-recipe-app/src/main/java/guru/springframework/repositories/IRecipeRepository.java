//: guru.springframework.repositories.IRecipeRepository.java


package guru.springframework.repositories;


import guru.springframework.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;


//@Repository
public interface IRecipeRepository extends JpaRepository<Recipe, Long> {

}///:~