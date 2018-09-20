//: guru.springframework.services.ImageServiceTest.java


package guru.springframework.services;


import guru.springframework.domain.Recipe;
import guru.springframework.repositories.reactive.IRecipeReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ImageServiceTest {

    @Mock
    private Recipe recipe;

    @Mock
    private IRecipeReactiveRepository recipeReactiveRepository;

    private ImageService imageService;

    private Random random;
    private String recipeId;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.random = new Random(System.currentTimeMillis());
        this.recipeId = this.random.nextLong() + "";
        this.imageService = new ImageService(this.recipeReactiveRepository);
    }

    @Test
    public void able_Tp_Save_Recipe_Image() throws Exception {

        // Given
        MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain", "Spring Framework Guru".getBytes());

        this.recipe = new Recipe();
        this.recipe.setId(this.recipeId);

        when(this.recipeReactiveRepository.findById(this.recipeId)).thenReturn(Mono.just(this.recipe));

        when(this.recipeReactiveRepository.save(this.recipe)).thenReturn(Mono.just(recipe));

        // When
        this.imageService.saveImage(this.recipeId, multipartFile);
        verify(this.recipeReactiveRepository, times(1)).save(this.recipe);
    }

}///:~