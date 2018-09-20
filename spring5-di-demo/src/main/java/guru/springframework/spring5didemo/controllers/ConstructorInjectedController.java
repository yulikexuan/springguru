//: guru.springframework.spring5didemo.controllers.ConstructorInjectedController.java

package guru.springframework.spring5didemo.controllers;


import guru.springframework.spring5didemo.services.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;


@Controller
public class ConstructorInjectedController {

    private final IGreetingService greetingService;

    @Autowired
    public ConstructorInjectedController(@Qualifier("constructorGreetingService") IGreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello() {
        return this.greetingService.sayGreeting();
    }

}///~