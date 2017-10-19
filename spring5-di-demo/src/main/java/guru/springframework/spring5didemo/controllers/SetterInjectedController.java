//: guru.springframework.spring5didemo.controllers.SetterInjectedController.java

package guru.springframework.spring5didemo.controllers;

import guru.springframework.spring5didemo.services.IGreetingService;

public class SetterInjectedController {

    private IGreetingService greetingService;

    public void setGreetingService(IGreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello() {
        return this.greetingService.sayGreeting();
    }

}///~