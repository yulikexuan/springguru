//: guru.springframework.spring5webapp.controllers.BookController.java

package guru.springframework.spring5webapp.controllers;


import guru.springframework.spring5webapp.repositories.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    public static final String BOOK_LIST_NAME = "books";

    private final IBookRepository bookRepository;

    @Autowired
    public BookController(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute(BOOK_LIST_NAME, this.bookRepository.findAll());
        return BOOK_LIST_NAME;
    }

}///~