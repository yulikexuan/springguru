//: guru.springframework.spring5didemo.config.GreetingServiceConfig.java

package guru.springframework.spring5didemo.config;


import guru.springframework.spring5didemo.repositories.IGreetingRepository;
import guru.springframework.spring5didemo.services.GreetingServiceFactory;
import guru.springframework.spring5didemo.services.IGreetingService;
import guru.springframework.spring5didemo.services.IGreetingServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;


@Configuration
public class GreetingServiceConfig {

    @Bean
    @Primary
    @Profile({"default","en"})
    IGreetingService primaryGreeingService(
            IGreetingServiceFactory greetingServiceFactory) {

        return greetingServiceFactory.createGreetingService("en");
    }

    @Bean
    @Primary
    @Profile("fr")
    IGreetingService primaryFrenchService(
            IGreetingServiceFactory greetingServiceFactory) {

        return greetingServiceFactory.createGreetingService("fr");
    }

    @Bean
    @Primary
    @Profile("de")
    IGreetingService primaryGermanGreetingService(
            IGreetingServiceFactory greetingServiceFactory) {

        return greetingServiceFactory.createGreetingService("de");
    }

    @Bean
    @Primary
    @Profile("zh")
    IGreetingService primaryChineseGreetingService(
            IGreetingServiceFactory greetingServiceFactory) {

        return greetingServiceFactory.createGreetingService("zh");
    }

}///~