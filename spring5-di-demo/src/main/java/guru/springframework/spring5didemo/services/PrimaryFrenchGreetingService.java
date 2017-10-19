//: guru.springframework.spring5didemo.services.PrimaryFrenchGreetingService.java

package guru.springframework.spring5didemo.services;


import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Service
@Primary
@Profile("fr")
public class PrimaryFrenchGreetingService implements IGreetingService {

    @Override
    public String sayGreeting() {
        return "Bonjour - Service d'accueil primaire";
    }

}///~