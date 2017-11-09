//: guru.springframework.spring5didemo.services.GreetingService.java

package guru.springframework.spring5didemo.services;


import org.springframework.stereotype.Service;

@Service
public class GreetingService implements IGreetingService {

    @Override
    public String sayGreeting() {
        return HELLO_GURUS;
    }

}///~