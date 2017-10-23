//: guru.springframework.joke.controllers.JokeController.java

package guru.springframework.joke.controllers;


import guru.springframework.joke.services.IJokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class JokeController {

    private final IJokeService jokeService;

    @Autowired
    public JokeController(IJokeService jokeService) {
        this.jokeService = jokeService;
    }

    @RequestMapping({"/", ""})
    public String showJoke(Model model) {

        String joke = this.jokeService.getJoke();
        model.addAttribute("joke", joke);

        return "chucknorris";
    }

}///~