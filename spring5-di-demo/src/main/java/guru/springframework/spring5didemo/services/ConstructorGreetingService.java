//: guru.springframework.spring5didemo.services.ConstructorGreetingService.java

package guru.springframework.spring5didemo.services;


import org.springframework.stereotype.Service;


@Service
public class ConstructorGreetingService implements IGreetingService {

    @Override
    public String sayGreeting() {
        return "Hello - I was injected via the constructor!!!";
    }

}///~