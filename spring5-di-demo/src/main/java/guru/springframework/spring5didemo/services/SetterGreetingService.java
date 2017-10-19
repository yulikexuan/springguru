//: guru.springframework.spring5didemo.services.SetterGreetingService.java

package guru.springframework.spring5didemo.services;


import org.springframework.stereotype.Service;


@Service
public class SetterGreetingService implements IGreetingService {

    @Override
    public String sayGreeting() {
        return "Hello - I was injected by the setter";
    }

}///~