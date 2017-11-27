//: guru.springframework.controllers.RecipeController.java


package guru.springframework.controllers;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.IRecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Slf4j
@Controller
public class RecipeController {

    private final IRecipeService recipeService;

    @Autowired
    public RecipeController(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {

        Optional<Recipe> recipeOptional =
                this.recipeService.findById(Long.valueOf(id));

        if (recipeOptional.isPresent()) {
            model.addAttribute("recipe", recipeOptional.get());
        }

        return "recipe/show";
     }

    @GetMapping
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
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/update")
    public String showFormToUpdateRecipe(@PathVariable String id, Model model) {

        RecipeCommand command = this.recipeService.findCommandById(
                Long.valueOf(id));

        model.addAttribute("recipe", command);

        return "/recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {
        log.debug(">>>>>>> Deleting recipe by id: " + id);
        this.recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

}///~