//: guru.springframework.controllers.ImageController.java


package guru.springframework.controllers;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.IImageService;
import guru.springframework.services.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ImageController {

    private final IImageService imageService;
    private final IRecipeService recipeService;

    @Autowired
    public ImageController(IImageService imageService,
                           IRecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{recipeId}/image")
    public String getImageForm(@PathVariable String recipeId, Model model) {

        RecipeCommand recipe = this.recipeService.findCommandById(
                Long.valueOf(recipeId));

        model.addAttribute("recipe", recipe);

        return "/recipe/imageuploadform";
    }

    @PostMapping("/recipe/{recipeId}/image")
    public String saveImageFileForRecipe(
            @PathVariable String recipeId,
            @RequestParam("imagefile") MultipartFile file) {

        this.imageService.saveImage(Long.valueOf(recipeId), file);

        return "redirect:/recipe/" + recipeId + "/update";
    }

}///~