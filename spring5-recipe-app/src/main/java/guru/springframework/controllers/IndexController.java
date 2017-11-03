//: guru.springframework.controllers.IndexController.java

package guru.springframework.controllers;


import guru.springframework.services.IRecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class IndexController {

    private final IRecipeService recipeService;

    @Autowired
    public IndexController(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage(Model model) {

        this.log.info(">>>>>>> Adding recipes to Model of index page ... ...");
        model.addAttribute("allRecipes",
                this.recipeService.getAllRecipes());

        return "index";
    }

}///~