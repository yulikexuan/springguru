//: guru.springframework.spring5didemo.services.GreetingServiceFactory.java

package guru.springframework.spring5didemo.services;


import guru.springframework.spring5didemo.repositories.IGreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GreetingServiceFactory implements IGreetingServiceFactory {

    private IGreetingRepository greetingRepository;

    @Autowired
    public GreetingServiceFactory(IGreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public IGreetingService createGreetingService(String lang) {
        switch (lang){
            case "en":
                return new PrimaryGreetingService(this.greetingRepository);
            case "de":
                return new PrimaryGermanGreetingService(this.greetingRepository);
            case "fr":
                return new PrimaryFrenchGreetingService(this.greetingRepository);
            case "zh":
                return new PrimaryChineseGreetingService(this.greetingRepository);
            default:
                return new PrimaryGreetingService(this.greetingRepository);
        }
    }

}///~