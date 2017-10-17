//: guru.springframework.spring5webapp.bootstrap.DevBootstrap.java


package guru.springframework.spring5webapp.bootstrap;


import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.IPublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.IAuthorRepository;
import guru.springframework.spring5webapp.repositories.IBookRepository;


@Component
public class DevBootstrap 
        implements ApplicationListener<ContextRefreshedEvent> {

    private final IAuthorRepository authorRepository;
    private final IBookRepository bookRepository;
    private final IPublisherRepository publisherRepository;

    @Autowired
    public DevBootstrap(IAuthorRepository authorRepository,
                        IBookRepository bookRepository,
                        IPublisherRepository publisherRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        this.initData();
    }
    
    private void initData(){

        Publisher wesley = new Publisher("Addison-Wesley Professional",
                "800 East 96th Street, Indianapolis, Indiana 46240");

        //Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234",
                wesley);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        this.publisherRepository.save(wesley);
        this.authorRepository.save(eric);
        this.bookRepository.save(ddd);

        //Rod
        Publisher wrox = new Publisher("Wrox",
                "111 River Street Hoboken, NJ 07030-5774");

        Author rod = new Author("Rod", "Johnson");

        Book noEJB = new Book("J2EE Development without EJB",
                "23444", wrox);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        this.publisherRepository.save(wrox);
        this.authorRepository.save(rod);
        this.bookRepository.save(noEJB);
    }

}///:~