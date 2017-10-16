//: guru.springframework.spring5webapp.repositories.IBookRepository.java


package guru.springframework.spring5webapp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.spring5webapp.model.Book;


public interface IBookRepository extends JpaRepository<Book, Long> {
}///:~