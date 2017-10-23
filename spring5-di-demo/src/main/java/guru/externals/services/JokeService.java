//: guru.springframework.joke.services.IJokeService.java

package guru.externals.services;


import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.stereotype.Service;


@Service
public class JokeService {

    private final ChuckNorrisQuotes chuckNorrisQuotes;

    public JokeService() {
        this.chuckNorrisQuotes = new ChuckNorrisQuotes();
    }

    public String getJoke() {
        return this.chuckNorrisQuotes.getRandomQuote();
    }

}///~