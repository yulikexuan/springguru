//: guru.springframework.controllers.RecipeController.java


package guru.springframework.controllers;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.IRecipeReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@Controller
public class RecipeController {

    static final String RECIPE_FORM_URL = "recipe/recipeform";

    private final IRecipeReactiveService recipeReactiveService;

    @Autowired
    public RecipeController(IRecipeReactiveService recipeReactiveService) {
        this.recipeReactiveService = recipeReactiveService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", this.recipeReactiveService.findById(id));
        return "recipe/show";
     }

    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe",
                new RecipeCommand.Builder().createRecipeCommand());
        return RECIPE_FORM_URL;
    }

    /*
     * Annotation for mapping HTTP POST requests onto specific handler methods.
     * - Specifically, @PostMapping is a composed annotation that acts as a
     *   shortcut for @RequestMapping(method = RequestMethod.POST)
     *
     * @Valid:
     * - Marks a property, method parameter or method return type for
     *   validation cascading
     * - Constraints defined on the object and its properties are be validated
     *   when the property, method parameter or method return type is validated
     * - This behavior is applied recursively
     *
     * @ModelAttribute
     * - The @ModelAttribute is an annotation that binds a method parameter or
     *   method return value to a named model attribute and then exposes it to
     *   a web view
     * - When used as a method argument, it indicates the argument should be
     *   retrieved from the model
     *   - When not present, it should be first instantiated and then added to
     *     the model
     *   - Once present in the model, the arguments fields should be populated
     *     from all request parameters that have matching names
     *
     *   "recipe" is required!!!  @ModelAttribute("recipe")
     */
    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(
            @Valid @ModelAttribute("recipe") RecipeCommand command,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(
                    err -> log.debug(">>>>>>> " + err.toString()));
            return RECIPE_FORM_URL;
        }

        RecipeCommand savedCommand = this.recipeReactiveService
                .saveRecipeCommand(command).block();
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

        RecipeCommand command = this.recipeReactiveService.findCommandById(id)
                .block();

        model.addAttribute("recipe", command);

        return "/recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {
        log.debug(">>>>>>> Deleting recipe by id: " + id);
        this.recipeReactiveService.deleteById(id);
        return "redirect:/";
    }

    /*
     * We have to explicitly annotate this handler method with
     * @ResponseStatus(HttpStatus.NOT_FOUND) here because the NotFoundException
     * class has already been annotated by @ResponseStatus (taking precedence)
     * and makes this method be bypassed
     */
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NotFoundException.class)
//    public ModelAndView handleNotFound(Exception e) {
//        log.error(">>>>>>> Handling NotFoundException: " + e.getMessage());
//        return this.handleException(e, "404error");
//    }
//
//    private ModelAndView handleException(Exception e, String viewName) {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName(viewName);
//        mav.addObject("exception", e);
//
//        return mav;
//    }

}///~