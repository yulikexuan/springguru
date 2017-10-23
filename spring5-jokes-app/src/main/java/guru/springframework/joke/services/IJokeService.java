//: guru.springframework.joke.services.IJokeService.java

package guru.springframework.joke.services;


import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.stereotype.Service;


@Service
public class IJokeService implements JokeService {

    private final ChuckNorrisQuotes chuckNorrisQuotes;

    public IJokeService() {
        this.chuckNorrisQuotes = new ChuckNorrisQuotes();
    }

    @Override
    public String getJoke() {
        return this.chuckNorrisQuotes.getRandomQuote();
    }

}///~