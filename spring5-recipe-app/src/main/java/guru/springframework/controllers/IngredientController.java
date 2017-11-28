//: guru.springframework.controllers.IngredientController.java


package guru.springframework.controllers;


import guru.springframework.services.IIngredientService;
import guru.springframework.services.IRecipeService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class IngredientController {

    private final IRecipeService recipeService;
    private final IIngredientService ingredientService;

    @Autowired
    public IngredientController(RecipeService recipeService,
                                IIngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String getIngredients(@PathVariable String id, Model model) {

        log.debug(">>>>>>> Getting ingredient list for recipoe id: " + id);

        model.addAttribute("recipe",
                this.recipeService.findCommandById(Long.valueOf(id)));

        return "/recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String getIngredientFromRecipe(@PathVariable String recipeId,
                                 @PathVariable String id, Model model) {

        log.debug(">>>>>>> Getting the ingredient of the recipoe: " + id + "/"
                + recipeId);

        model.addAttribute("ingredient",
                this.ingredientService.findByRecipeIdAndIngredientId(
                        Long.valueOf(recipeId), Long.valueOf(id)));

        return "/recipe/ingredient/show";
    }

}///~