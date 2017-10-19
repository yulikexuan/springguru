//: guru.springframework.spring5didemo.controllers.SetterInjectedController.java

package guru.springframework.spring5didemo.controllers;

import guru.springframework.spring5didemo.services.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class SetterInjectedController {

    private IGreetingService greetingService;

    @Autowired
    public void setGreetingService(IGreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello() {
        return this.greetingService.sayGreeting();
    }

}///~