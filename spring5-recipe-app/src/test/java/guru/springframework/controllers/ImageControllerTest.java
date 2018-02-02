//: guru.springframework.controllers.ImageControllerTest.java


package guru.springframework.controllers;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.IImageService;
import guru.springframework.services.IRecipeReactiveService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import reactor.core.publisher.Mono;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Ignore
public class ImageControllerTest extends AbstractControllerTest {

    @Mock
    private IImageService imageService;

    @Mock
    private IRecipeReactiveService recipeReactiveService;

    private ImageController controller;

    private String recipeId;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.recipeId = this.random.nextLong() + "";
    }

    @Override
    Object initController() {
        this.controller = new ImageController(this.imageService,
                this.recipeReactiveService);
        return this.controller;
    }

    @Override
    String getInvalidUrl() {
        return "/recipe/123abc/image";
    }

    @Test
    public void able_To_Show_Image_Form() throws Exception {

        // Given
        String imageFormUrl = "/recipe/" + this.recipeId + "/image";
        String formUrl = "/recipe/imageuploadform";

        RecipeCommand recipe = new RecipeCommand.Builder()
                .setId(this.recipeId)
                .createRecipeCommand();

        when(this.recipeReactiveService.findCommandById(this.recipeId))
                .thenReturn(Mono.just(recipe));

        // When
        this.mockMvc.perform(get(imageFormUrl))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name(formUrl));
    }

    @Test
    public void able_To_Upload_Image_For_Recipe() throws Exception {

        // Given
        String uploadUrl = "/recipe/" + this.recipeId + "/image";

        String locationHeader = "/recipe/" + this.recipeId + "/update";

        MockMultipartFile multipartFile = new MockMultipartFile(
                "imagefile",
                "testing.txt",
                "text/plain",
                "Spring Framework Guru".getBytes());

        when(this.imageService.saveImage(anyString(), any()))
                .thenReturn(Mono.empty());

        // When
        this.mockMvc.perform(multipart(uploadUrl).file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", locationHeader));

        // Then
        verify(this.imageService, times(1))
                .saveImage(this.recipeId, multipartFile);
    }

    @Ignore
    @Test
    public void able_To_Load_Image_From_Database() throws Exception {

        // Given
        String imageUrl = "/recipe/" + this.recipeId + "/recipeimage";

        RecipeCommand recipeCommand = mock(RecipeCommand.class);
        when(this.recipeReactiveService.findCommandById(this.recipeId))
                .thenReturn(Mono.just(recipeCommand));

        String str = "Fake image bytes";
        byte[] bytes = str.getBytes();
        Byte[] image = new Byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            image[i] = bytes[i];
        }

        when(recipeCommand.getImage()).thenReturn(image);

        // When
        MockHttpServletResponse response = this.mockMvc
                .perform(get(imageUrl))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        // Then
//        assertThat(response.getContentType(), is("image/jpeg"));
//        assertThat(response.getContentAsByteArray(), is(bytes));
    }

}///:~

