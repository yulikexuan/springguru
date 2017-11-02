//: guru.springframework.controllers.IndexController.java

package guru.springframework.controllers;


import guru.springframework.services.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


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