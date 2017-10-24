//: guru.springframework.spring5didemo.services.PrimaryGreetingService.java

package guru.springframework.spring5didemo.services;


import guru.springframework.spring5didemo.repositories.IGreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PrimaryGreetingService implements IGreetingService {

    private IGreetingRepository greetingRepository;

    @Autowired
    public PrimaryGreetingService(IGreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public String sayGreeting() {
        return this.greetingRepository.getEnglishGreeting();
    }

}///~