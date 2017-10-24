//: guru.springframework.spring5didemo.services.PrimaryFrenchGreetingService.java

package guru.springframework.spring5didemo.services;


import guru.springframework.spring5didemo.repositories.IGreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PrimaryFrenchGreetingService implements IGreetingService {

    private IGreetingRepository greetingRepository;

    @Autowired
    public PrimaryFrenchGreetingService(IGreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public String sayGreeting() {
        return this.greetingRepository.getFrenchGreeting();
    }

}///~