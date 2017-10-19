//: guru.springframework.spring5didemo.services.PrimaryGermanGreetingService.java

package guru.springframework.spring5didemo.services;


import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Primary
@Profile("de")
public class PrimaryGermanGreetingService implements IGreetingService {

    @Override
    public String sayGreeting() {
        return "Hallo - Primärer Grußdienst";
    }

}///~