//: guru.springframework.spring5didemo.controllers.PropertyInjectedController.java

package guru.springframework.spring5didemo.controllers;


import guru.springframework.spring5didemo.services.IGreetingService;


public class PropertyInjectedController {

    public IGreetingService greetingService;

    public String sayHello() {
        return this.greetingService.sayGreeting();
    }

}///~