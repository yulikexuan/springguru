//: guru.springframework.spring5didemo.controllers.SetterInjectedControllerTest.java

package guru.springframework.spring5didemo.controllers;

import guru.springframework.spring5didemo.services.GreetingService;
import guru.springframework.spring5didemo.services.IGreetingService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SetterInjectedControllerTest {

    private SetterInjectedController controller;

    @Before
    public void setUp() throws Exception {
        this.controller = new SetterInjectedController();
        this.controller.setGreetingService(new GreetingService());
    }

    @Test
    public void able_To_Say_Hello() {
        assertEquals(IGreetingService.HELLO_GURUS, this.controller.sayHello());
    }

}///~