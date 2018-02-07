//: guru.springframework.controllers.IngredientController.java


package guru.springframework.controllers;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.services.IIngredientReactiveService;
import guru.springframework.services.IRecipeReactiveService;
import guru.springframework.services.IUnitOfMeasureService;
import guru.springframework.services.RecipeReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;


@Slf4j
@Controller
public class IngredientController {

    private final IRecipeReactiveService recipeReactiveService;
    private final IIngredientReactiveService ingredientService;
    private final IUnitOfMeasureService unitOfMeasureService;

    private WebDataBinder webDataBinder;

    @Autowired
    public IngredientController(RecipeReactiveService recipeReactiveService,
                                IIngredientReactiveService ingredientService,
                                IUnitOfMeasureService unitOfMeasureService) {

        this.recipeReactiveService = recipeReactiveService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @InitBinder("ingredient")
    public void initWebDataBinder(WebDataBinder webDataBinder) {
        this.webDataBinder = webDataBinder;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String getIngredients(@PathVariable String id, Model model) {

        log.debug(">>>>>>> Getting ingredient list for recipoe id: " + id);

        model.addAttribute("recipe",
                this.recipeReactiveService.findCommandById(id));

        return "/recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showIngredientOfRecipe(@PathVariable String recipeId,
                                         @PathVariable String id, Model model) {

        log.debug(">>>>>>> Getting the ingredient of the recipe: " + id + "/"
                + recipeId);

        model.addAttribute("ingredient",
                this.ingredientService.findByRecipeIdAndIngredientId(recipeId,
                        id));

        return "/recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String showIngredientFormForCreatingNew(
            @PathVariable String recipeId, Model model) {

        String viewName = "/";
        if (this.recipeReactiveService.existById(recipeId).block()) {
            IngredientCommand ingredient = new IngredientCommand.Builder()
                    .setRecipeId(recipeId).createIngredientCommand();
            model.addAttribute("ingredient", ingredient);

//            Use the defined @ModelAttribute method instead
//            Flux<UnitOfMeasureCommand> unitOfMeasures =
//                    getAllUnitOfMeasureCommands();
//            model.addAttribute("unitOfMeasures", unitOfMeasures);

            viewName = "/recipe/ingredient/ingredientform";
        }
        return viewName;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String showUpdateFormForIngredientOfRecipe(
            @PathVariable String recipeId, @PathVariable String ingredientId,
            Model model) {

        log.debug(">>>>>>> Getting the ingredient of the recipe: "
                + ingredientId + "/" + recipeId);

        IngredientCommand ic = this.ingredientService
                .findByRecipeIdAndIngredientId(recipeId, ingredientId).block();

        model.addAttribute("ingredient", ic);

//        Use the defined @ModelAttribute method instead
//        model.addAttribute("unitOfMeasures",
//                this.unitOfMeasureService.findAllUnitOfMeasureCommands());

//        Flux<UnitOfMeasureCommand> unitOfMeasures =
//                getAllUnitOfMeasureCommands();
//        model.addAttribute("unitOfMeasures", unitOfMeasures);

        return "/recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdateIngredient(@PathVariable String recipeId,
            @ModelAttribute("ingredient") IngredientCommand ingredientCommand,
                                         Model model) {

        ingredientCommand.setRecipeId(recipeId);

        this.webDataBinder.validate();
        BindingResult bindingResult = this.webDataBinder.getBindingResult();

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(
                    err -> log.debug(">>>>>>> " + err.toString()));

//            Use the defined @ModelAttribute method instead
//            Flux<UnitOfMeasureCommand> unitOfMeasures =
//                    getAllUnitOfMeasureCommands();
//            model.addAttribute("unitOfMeasures", unitOfMeasures);

            /*
             * Should return full template name here other than an UPDATE
             * REQUEST URL like:
             *   return "/recipe/" + recipeId + "/ingredient/" +
             *           ingredientCommand.getId() + "/update"
             */
            return "/recipe/ingredient/ingredientform";
        }

        IngredientCommand savedCommand = this.ingredientService
                .saveIngredientCommand(ingredientCommand).block();

//        String recipeId = savedCommand.getRecipeId();
        String ingredientId = savedCommand.getId();

        //"redirect:/recipe/" + savedCommand.getId() + "/show"

        String url = new StringBuilder()
                .append("redirect:/recipe/")
                .append(recipeId)
                .append("/ingredient/")
                .append(ingredientId)
                .append("/show")
                .toString();

        return url;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredientFromRecipe(@PathVariable String recipeId,
                                             @PathVariable String ingredientId) {

        this.ingredientService.deleteIngredientCommand(recipeId, ingredientId)
                .block();

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }


    /*
     * This method will be run with every request so that every time we get a
     * model being returned to the view layer, we are going to populate the
     * property called "unitOfMeasures"
     */
    @ModelAttribute("unitOfMeasures")
    public Flux<UnitOfMeasureCommand> getAllUnitOfMeasureCommands() {
        return this.unitOfMeasureService.findAllUnitOfMeasureCommands();
    }

}///~