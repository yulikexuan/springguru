//: guru.springframework.spring5didemo.controllers.ConstructorInjectedController.java

package guru.springframework.spring5didemo.controllers;

import guru.springframework.spring5didemo.services.IGreetingService;

public class ConstructorInjectedController {

    private final IGreetingService greetingService;

    public ConstructorInjectedController(IGreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello() {
        return this.greetingService.sayGreeting();
    }

}///~