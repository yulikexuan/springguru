//: guru.springframework.controllers.RecipeController.java


package guru.springframework.controllers;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe",
                new RecipeCommand.Builder().createRecipeCommand());
        return "recipe/recipeform";
    }

    /*
     * Annotation for mapping HTTP POST requests onto specific handler methods.
     * - Specifically, @PostMapping is a composed annotation that acts as a
     *   shortcut for @RequestMapping(method = RequestMethod.POST)
     */
    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = this.recipeService.saveRecipeCommand(
                command);
        /*
         * By using the prefix redirect:
         *    – the redirect view name is injected into the controller like any
         *      other logical view name. The controller is not even aware that
         *      redirection is happening.
         * When a view name is returned with the prefix redirect:
         *   – the UrlBasedViewResolver (and all its subclasses) will recognize
         *     this as a special indication that a redirect needs to happen. The
         *     rest of the view name will be used as the redirect URL
         * A quick but important note here is that
         *   – when we use this logical view name here – redirect:/redirectedUrl
         *     we’re doing a redirect relative to the current Servlet context
         */
        return "redirect:/recipe/show/" + savedCommand.getId();
    }

}///~