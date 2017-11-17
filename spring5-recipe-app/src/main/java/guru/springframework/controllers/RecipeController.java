//: guru.springframework.controllers.RecipeController.java


package guru.springframework.controllers;


import guru.springframework.domain.Recipe;
import guru.springframework.services.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
public class RecipeController {

    private final IRecipeService recipeService;

    @Autowired
    public RecipeController(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model) {

        Optional<Recipe> recipeOptional =
                this.recipeService.findById(Long.valueOf(id));

        if (recipeOptional.isPresent()) {
            model.addAttribute("recipe", recipeOptional.get());
        }

        return "recipe/show";
     }

}///~