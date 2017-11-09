//: guru.springframework.spring5didemo.controllers.MyController.java

package guru.springframework.spring5didemo.controllers;


import guru.springframework.spring5didemo.services.IGreetingService;
import org.springframework.stereotype.Controller;


@Controller
public class MyController {

    private IGreetingService greetingService;

    public MyController(IGreetingService primaryGreetingService) {
        this.greetingService = primaryGreetingService;
    }

    public String greeting() {
        return this.greetingService.sayGreeting();
    }

}///~