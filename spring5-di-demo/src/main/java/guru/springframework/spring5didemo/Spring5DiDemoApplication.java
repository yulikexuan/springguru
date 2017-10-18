package guru.springframework.spring5didemo;


import guru.springframework.spring5didemo.controllers.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Spring5DiDemoApplication {

	public static void main(String[] args) {
		ApplicationContext actx = SpringApplication.run(
				Spring5DiDemoApplication.class, args);
        MyController controller = (MyController) actx.getBean(
                "myController");
        controller.greeting();
	}
}
