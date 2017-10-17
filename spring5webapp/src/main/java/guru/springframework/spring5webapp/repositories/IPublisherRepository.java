//: guru.springframework.spring5webapp.repositories.IPublisherRepository.java

package guru.springframework.spring5webapp.repositories;


import guru.springframework.spring5webapp.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPublisherRepository extends JpaRepository<Publisher, Long> {

}///~