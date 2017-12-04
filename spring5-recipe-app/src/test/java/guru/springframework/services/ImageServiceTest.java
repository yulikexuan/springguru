//: guru.springframework.services.ImageServiceTest.java


package guru.springframework.services;


import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IRecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

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
    private IRecipeRepository recipeRepository;

    private ImageService imageService;

    private Random random;
    private Long recipeId;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.random = new Random(System.currentTimeMillis());
        this.recipeId = this.random.nextLong();
        this.imageService = new ImageService(this.recipeRepository);
    }

    @Test
    public void able_Tp_Save_Recipe_Image() throws Exception {

        // Given
        MultipartFile multipartFile = new MockMultipartFile("imagefile",
                "testing.txt", "text/plain",
                "Spring Framework Guru".getBytes());

        Optional<Recipe> recipeOpt = Optional.of(this.recipe);

        when(this.recipeRepository.findById(this.recipeId))
                .thenReturn(recipeOpt);

        ArgumentCaptor<Byte[]> argumentCaptor = ArgumentCaptor.forClass(
                Byte[].class);

        // When
        this.imageService.saveImage(this.recipeId, multipartFile);
        verify(this.recipeRepository, times(1))
                .save(this.recipe);
        verify(this.recipe, times(1))
                .setImage(argumentCaptor.capture());
        assertThat(multipartFile.getBytes().length,
                is(argumentCaptor.getValue().length));
    }

}///:~