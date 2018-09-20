//: guru.springframework.spring5didemo.services.ChineseGreetingService.java

package guru.springframework.spring5didemo.services;


import guru.springframework.spring5didemo.repositories.IGreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PrimaryChineseGreetingService implements IGreetingService {

    private IGreetingRepository greetingRepository;

    @Autowired
    public PrimaryChineseGreetingService(IGreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public String sayGreeting() {
        return this.greetingRepository.getChineseGreeting();
    }

}///~