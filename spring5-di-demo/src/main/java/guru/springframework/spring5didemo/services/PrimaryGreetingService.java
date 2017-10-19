//: guru.springframework.spring5didemo.services.PrimaryGreetingService.java

package guru.springframework.spring5didemo.services;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service
@Primary
public class PrimaryGreetingService implements IGreetingService {

    @Override
    public String sayGreeting() {
        return "Hello - Primary Greeting Service";
    }

}///~