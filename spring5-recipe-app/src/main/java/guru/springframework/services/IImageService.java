//: guru.springframework.services.IImageService.java


package guru.springframework.services;


import org.springframework.web.multipart.MultipartFile;


public interface IImageService {

    void saveImage(String recipeId, MultipartFile multipartFile);

}///~