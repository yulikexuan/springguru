//: guru.springframework.spring5webapp.controllers.AuthorController.java

package guru.springframework.spring5webapp.controllers;


import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.repositories.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class AuthorController {

    public static final String AUTHOR_LIST_NAME = "authors";

    private final IAuthorRepository authorRepository;

    @Autowired
    public AuthorController(IAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/" + AUTHOR_LIST_NAME)
    public String getAllAuthors(Model model) {
        List<Author> authors = this.authorRepository.findAll();
        model.addAttribute(AUTHOR_LIST_NAME, authors);
        return AUTHOR_LIST_NAME;
    }

}///~