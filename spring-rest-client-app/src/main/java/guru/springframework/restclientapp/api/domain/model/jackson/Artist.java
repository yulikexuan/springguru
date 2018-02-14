//: guru.springframework.restclientapp.api.domain.model.jackson.Artist.java


package guru.springframework.restclientapp.api.domain.model.jackson;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class Artist {

	private String name;
	private Date birthDate;

	private int age;
	private String homeTown;
	private List<String> awardsWon = new ArrayList<>();

}///~