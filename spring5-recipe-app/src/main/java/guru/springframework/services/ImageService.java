//: guru.springframework.services.ImageService.java


package guru.springframework.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Service
public class ImageService implements IImageService {

    @Override
    public void saveImage(Long recipeId, MultipartFile multipartFile) {
        log.info(">>>>>>> Recived a file for new recipe image."
                + multipartFile.getOriginalFilename());
    }

}///~