//: guru.springframework.joke.services.IJokeService.java

package guru.springframework.joke.services;


import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.stereotype.Service;


@Service
public class JokeService implements IJokeService {

    private final ChuckNorrisQuotes chuckNorrisQuotes;

    public JokeService() {
        this.chuckNorrisQuotes = new ChuckNorrisQuotes();
    }

    @Override
    public String getJoke() {
        return this.chuckNorrisQuotes.getRandomQuote();
    }

}///~