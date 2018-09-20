//: guru.springframework.restclientapp.api.domain.model.jackson.Album.java


package guru.springframework.restclientapp.api.domain.model.jackson;


import lombok.Data;

import java.util.*;


@Data
public class Album {

    private String title;
    private Artist artist;

    private String[] links;
    private List<String> songs = new ArrayList<>();
    private Map<String, String> musicians = new HashMap<>();

    public Album(String title) {
        this.title = title;
    }

    public List<String> getSongs() {
        return Collections.unmodifiableList(this.songs);
    }

    public void addSong(String song) {
        this.songs.add(song);
    }

    public Map<String, String> getMusicians() {
        return Collections.unmodifiableMap(this.musicians);
    }

    public void addMusician(String key, String value) {
        this.musicians.put(key, value);
    }

}///~