//: guru.springframework.spring5webapp.bootstrap.DevBootstrap.java


package guru.springframework.spring5webapp.bootstrap;


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.IAuthorRepository;
import guru.springframework.spring5webapp.repositories.IBookRepository;


@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final IAuthorRepository authorRepository;
    private final IBookRepository bookRepository;

    public DevBootstrap(IAuthorRepository authorRepository, IBookRepository bookRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        this.initData();
    }

    private void initData() {

        //Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", "Harper Collins");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        this.authorRepository.save(eric);
        this.bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", "Wrox");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        this.authorRepository.save(rod);
        this.bookRepository.save(noEJB);
    }

}///:~