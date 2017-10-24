//: guru.springframework.spring5didemo.services.PrimaryGermanGreetingService.java

package guru.springframework.spring5didemo.services;


import guru.springframework.spring5didemo.repositories.IGreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Service
public class PrimaryGermanGreetingService implements IGreetingService {

    private IGreetingRepository greetingRepository;

    @Autowired
    public PrimaryGermanGreetingService(IGreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public String sayGreeting() {
        return this.greetingRepository.getGermanGreeting();
    }

}///~