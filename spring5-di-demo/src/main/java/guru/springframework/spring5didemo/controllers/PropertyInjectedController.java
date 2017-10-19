//: guru.springframework.spring5didemo.controllers.PropertyInjectedController.java

package guru.springframework.spring5didemo.controllers;


import guru.springframework.spring5didemo.services.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class PropertyInjectedController {

    @Autowired
    public IGreetingService greetingService;

    public String sayHello() {
        return this.greetingService.sayGreeting();
    }

}///~