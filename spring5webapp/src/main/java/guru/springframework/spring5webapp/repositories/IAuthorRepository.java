//: guru.springframework.spring5webapp.repositories.IAuthorRepository.java


package guru.springframework.spring5webapp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.spring5webapp.model.Author;


public interface IAuthorRepository extends JpaRepository<Author, Long> {

}///:~