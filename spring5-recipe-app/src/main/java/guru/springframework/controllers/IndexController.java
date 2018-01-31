//: guru.springframework.controllers.IndexController.java

package guru.springframework.controllers;


import guru.springframework.services.IRecipeReactiveService;
import guru.springframework.services.IRecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class IndexController {

    private final IRecipeReactiveService recipeReactiveService;

    @Autowired
    public IndexController(IRecipeReactiveService recipeReactiveService) {
        this.recipeReactiveService = recipeReactiveService;
    }

    @RequestMapping({"", "/", "/home"})
    public String getIndexPage(Model model) {

        this.log.info(">>>>>>> Adding recipes to Model of index page ... ...");
        model.addAttribute("allRecipes",
                this.recipeReactiveService.getAllRecipes().collectList().block());

        return "index";
    }

}///~