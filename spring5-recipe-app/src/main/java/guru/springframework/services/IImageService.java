//: guru.springframework.services.IImageService.java


package guru.springframework.services;


import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;


public interface IImageService {

    Mono<Void> saveImage(String recipeId, MultipartFile multipartFile);

}///~