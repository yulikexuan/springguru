//: guru.springframework.services.ImageService.java


package guru.springframework.services;


import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IRecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@Service
public class ImageService implements IImageService {

    private final IRecipeRepository recipeRepository;

    @Autowired
    public ImageService(IRecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImage(Long recipeId, MultipartFile multipartFile) {

        log.info(">>>>>>> Recived a file for new recipe image."
                + multipartFile.getOriginalFilename());

        Recipe recipe = this.recipeRepository.findById(recipeId).get();

        try {
            byte[] byteArr = multipartFile.getBytes();
            Byte[] byteObjects = new Byte[byteArr.length];
            for (int i = 0; i < byteArr.length; i++) {
                byteObjects[i] = byteArr[i];
            }
            recipe.setImage(byteObjects);
            this.recipeRepository.save(recipe);
            log.debug(">>>>>>> Recipe Image has been saved.");
        } catch (IOException e) {
            log.error("Error occurred when saving recipe-image. Recipe ID is "
                    + recipeId);
            e.printStackTrace();
        }
    }

}///~