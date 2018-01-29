//: guru.springframework.controllers.IngredientController.java


package guru.springframework.controllers;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


@Slf4j
@Controller
public class IngredientController {

    private final IRecipeService recipeService;
    private final IIngredientReactiveService ingredientService;
    private final IUnitOfMeasureService unitOfMeasureService;

    @Autowired
    public IngredientController(RecipeService recipeService,
                                IIngredientReactiveService ingredientService,
                                IUnitOfMeasureService unitOfMeasureService) {

        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String getIngredients(@PathVariable String id, Model model) {

        log.debug(">>>>>>> Getting ingredient list for recipoe id: " + id);

        model.addAttribute("recipe",
                this.recipeService.findCommandById(id));

        return "/recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showIngredientOfRecipe(@PathVariable String recipeId,
                                         @PathVariable String id, Model model) {

        log.debug(">>>>>>> Getting the ingredient of the recipe: " + id + "/"
                + recipeId);

        Mono<IngredientCommand> ingredientCommandMono =
                this.ingredientService.findByRecipeIdAndIngredientId(recipeId,
                        id);

        IngredientCommand ic = ingredientCommandMono.block();

        model.addAttribute("ingredient", ic);

        return "/recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String showIngredientFormForCreatingNew(
            @PathVariable String recipeId, Model model) {

        String viewName = "/";
        if (this.recipeService.existById(recipeId)) {
            IngredientCommand ingredient = new IngredientCommand.Builder()
                    .setRecipeId(recipeId).createIngredientCommand();
            model.addAttribute("ingredient", ingredient);

            List<UnitOfMeasureCommand> unitOfMeasures =
                    this.unitOfMeasureService.findAllUnitOfMeasureCommands()
                            .collectList()
                            .block();
            model.addAttribute("unitOfMeasures", unitOfMeasures);

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

        model.addAttribute("ingredient",
                this.ingredientService.findByRecipeIdAndIngredientId(
                        recipeId, ingredientId).block());

//        model.addAttribute("unitOfMeasures",
//                this.unitOfMeasureService.findAllUnitOfMeasureCommands());

        List<UnitOfMeasureCommand> unitOfMeasures =
                this.unitOfMeasureService.findAllUnitOfMeasureCommands()
                        .collectList()
                        .block();
        model.addAttribute("unitOfMeasures", unitOfMeasures);

        return "/recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdateIngredient(
            @ModelAttribute IngredientCommand ingredientCommand) {

        IngredientCommand savedCommand = this.ingredientService
                .saveIngredientCommand(ingredientCommand).block();

        String recipeId = savedCommand.getRecipeId();
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

}///~