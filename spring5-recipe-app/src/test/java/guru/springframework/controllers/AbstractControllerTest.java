//: guru.springframework.controllers.AbstractControllerTest.java


package guru.springframework.controllers;


import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Random;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public abstract class AbstractControllerTest {

    MockMvc mockMvc;
    Random random;

    abstract Object initController();
    abstract String getInvalidUrl();

    public void setUp() throws Exception {
        this.random = new Random(System.currentTimeMillis());
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(initController())
                .setControllerAdvice(new ControllerExceptionHandlers())
                .build();
    }

//    @Test
//    public void able_To_Handle_NumberFormatException() throws Exception {
//        // When
//        this.mockMvc.perform(get(getInvalidUrl()))
//                .andExpect(status().isBadRequest())
//                .andExpect(model().attributeExists("exception"))
//                .andExpect(view().name("400error"));
//    }



}///~