//: guru.springframework.controllers.ImageControllerTest.java


package guru.springframework.controllers;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.IImageService;
import guru.springframework.services.IRecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ImageControllerTest {

    @Mock
    private IImageService imageService;

    @Mock
    private IRecipeService recipeService;

    private MockMvc mockMvc;
    private ImageController controller;

    private Random random;
    private Long recipeId;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.random = new Random(System.currentTimeMillis());
        this.recipeId = this.random.nextLong();
        this.controller = new ImageController(this.imageService,
                this.recipeService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
    }

    @Test
    public void able_To_Show_Image_Form() throws Exception {

        // Given
        String imageFormUrl = "/recipe/" + this.recipeId + "/image";
        String formUrl = "/recipe/imageuploadform";

        RecipeCommand recipe = new RecipeCommand.Builder()
                .setId(this.recipeId)
                .createRecipeCommand();

        when(this.recipeService.findCommandById(this.recipeId)).thenReturn(
                recipe);

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
                "imageFile",
                "testing.txt",
                "text/plain",
                "Spring Framework Guru".getBytes());

        // When
        this.mockMvc.perform(multipart(uploadUrl).file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", locationHeader));

        // Then
        verify(this.imageService, times(1))
                .saveImage(this.recipeId, multipartFile);
    }

}///:~