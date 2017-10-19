//: guru.springframework.spring5didemo.services.ChineseGreetingService.java

package guru.springframework.spring5didemo.services;


import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Primary
@Profile("zh")
public class PrimaryChineseGreetingService implements IGreetingService {

    @Override
    public String sayGreeting() {
        return "您好 - 主問候服務";
    }

}///~