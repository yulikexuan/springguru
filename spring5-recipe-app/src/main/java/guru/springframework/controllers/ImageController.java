//: guru.springframework.controllers.ImageController.java


package guru.springframework.controllers;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.IImageService;
import guru.springframework.services.IRecipeReactiveService;
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
    private final IRecipeReactiveService recipeReactiveService;

    @Autowired
    public ImageController(IImageService imageService,
                           IRecipeReactiveService recipeReactiveService) {
        this.imageService = imageService;
        this.recipeReactiveService = recipeReactiveService;
    }

    @GetMapping("/recipe/{recipeId}/image")
    public String getImageForm(@PathVariable String recipeId, Model model) {

    	if (!recipeId.equals("null")) {
		    RecipeCommand recipe = this.recipeReactiveService
				    .findCommandById(recipeId).block();

		    model.addAttribute("recipe", recipe);
	    }

        return "/recipe/imageuploadform";
    }

    @PostMapping("/recipe/{recipeId}/image")
    public String saveImageFileForRecipe(
            @PathVariable String recipeId,
            @RequestParam("imagefile") MultipartFile file) {

        this.imageService.saveImage(recipeId, file).block();

        return "redirect:/recipe/" + recipeId + "/update";
    }

//    @GetMapping("/recipe/{recipeId}/recipeimage")
//    public void renderImageFromDB(@PathVariable String recipeId,
//            HttpServletResponse response) throws IOException {
//
//        if (recipeId.equals("null")) {
//            return;
//        }
//
//        RecipeCommand recipeCommand = this.recipeReactiveService
//                .findCommandById(recipeId).block();
//
//        Byte[] image = recipeCommand.getImage();
//        if (image != null) {
//            byte[] byteArray = new byte[image.length];
//            for (int i = 0; i < image.length; i++) {
//                byteArray[i] = image[i];
//            }
//            response.setContentType("image/jpeg");
//            InputStream is = new ByteArrayInputStream(byteArray);
//            IOUtils.copy(is, response.getOutputStream());
//        }
//    }

}///~