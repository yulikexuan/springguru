package guru.springframework.spring5didemo.services;

public interface IGreetingServiceFactory {
    IGreetingService createGreetingService(String lang);
}
