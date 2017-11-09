//: guru.springframework.spring5didemo.repositories.GreetingRepository.java

package guru.springframework.spring5didemo.repositories;


import org.springframework.stereotype.Repository;


@Repository
public class GreetingRepository implements IGreetingRepository {

    @Override
    public String getEnglishGreeting() {
        return "Hello - Primary Greeting service";
    }

    @Override
    public String getFrenchGreeting() {
        return "Bonjour - Service d'accueil principal";
    }

    @Override
    public String getGermanGreeting() {
        return "Primärer Grußdienst";
    }

    @Override
    public String getChineseGreeting() {
        return "您好 - 主問候服務";
    }

}///~