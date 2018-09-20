//: guru.springframework.services.ImageService.java


package guru.springframework.services;


import guru.springframework.domain.Recipe;
import guru.springframework.repositories.reactive.IRecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;


@Slf4j
@Service
public class ImageService implements IImageService {

    private final IRecipeReactiveRepository recipeReactiveRepository;

    @Autowired
    public ImageService(IRecipeReactiveRepository recipeReactiveRepository) {
        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    public Mono<Void> saveImage(String recipeId, MultipartFile multipartFile) {

        log.info(">>>>>>> Recived a file for new recipe image." + multipartFile.getOriginalFilename());

        Mono<Recipe> recipeMono = this.recipeReactiveRepository.findById(recipeId).map(r -> {
            try {
                byte[] byteArr = multipartFile.getBytes();
                Byte[] byteObjects = new Byte[byteArr.length];
                for (int i = 0; i < byteArr.length; i++) {
                    byteObjects[i] = byteArr[i];
                }
                r.setImage(byteObjects);
                return r;
            } catch (IOException e) {
                log.error("Error occurred when saving recipe-image. Recipe ID is " + recipeId);
                throw new RuntimeException(e);
            }

        });

        this.recipeReactiveRepository.save(recipeMono.block()).block();

        return Mono.empty();
    }

}///~