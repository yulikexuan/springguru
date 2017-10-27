package guru.springframework.spring5didemo;


import guru.springframework.spring5didemo.examplebeans.FakeDataSource;
import guru.springframework.spring5didemo.examplebeans.FakeJmsBroker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class Spring5DiDemoApplication {

    public static void main(String[] args) {

        ApplicationContext actx = SpringApplication.run(
                Spring5DiDemoApplication.class, args);

        FakeDataSource fakeDataSource = actx.getBean(FakeDataSource.class);
        System.out.println(fakeDataSource.getUser());
        System.out.println(fakeDataSource.getPassword());

        FakeJmsBroker fakeJmsBroker = actx.getBean(FakeJmsBroker.class);
        System.out.println(fakeJmsBroker);

    }

}///:~