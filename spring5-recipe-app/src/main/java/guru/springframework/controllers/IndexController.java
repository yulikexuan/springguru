//: guru.springframework.controllers.IndexController.java

package guru.springframework.controllers;


import guru.springframework.IRecipeService;
import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.ICategoryRepository;
import guru.springframework.repositories.IRecipeRepository;
import guru.springframework.repositories.IUnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Controller
public class IndexController {

    private final IRecipeService recipeService;

    @Autowired
    public IndexController(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage(Model model) {

        model.addAttribute("allRecipes",
                this.recipeService.getAllRecipes());

        return "index";
    }

}///~