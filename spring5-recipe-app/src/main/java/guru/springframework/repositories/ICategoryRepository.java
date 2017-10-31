//: guru.springframework.repositories.ICategoryRepository.java


package guru.springframework.repositories;


import guru.springframework.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;


//@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

}///:~