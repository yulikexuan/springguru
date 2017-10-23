package guru.springframework.spring5didemo;


import guru.externals.services.JokeService;
import guru.springframework.spring5didemo.controllers.ConstructorInjectedController;
import guru.springframework.spring5didemo.controllers.MyController;
import guru.springframework.spring5didemo.controllers.PropertyInjectedController;
import guru.springframework.spring5didemo.controllers.SetterInjectedController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"guru.externals.services",
        "guru.springframework.spring5didemo"})
public class Spring5DiDemoApplication {

    public static void main(String[] args) {

        ApplicationContext actx = SpringApplication.run(
                Spring5DiDemoApplication.class, args);

        MyController controller = (MyController) actx.getBean(
                "myController");

        // Test DI
        System.out.println(controller.greeting());
        System.out.println(actx.getBean(PropertyInjectedController.class)
                .sayHello());
        System.out.println(actx.getBean(SetterInjectedController.class)
                .sayHello());
        System.out.println(actx.getBean(ConstructorInjectedController.class)
                .sayHello());

        JokeService jokeService = (JokeService) actx.getBean("jokeService");
        System.out.println(jokeService.getJoke());
    }

}///:~