//: guru.springframework.restclientapp.api.domain.model.jackson.AlbumJacksonTest.java


package guru.springframework.restclientapp.api.domain.model.jackson;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AllArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;


public class AlbumJacksonTest {

	private ObjectMapper objectMapper;
	private Album album;
	private Artist artist;

	private SimpleDateFormat format;

	@Before
	public void setUp() throws Exception {

		// Set up the model
		this.album = new Album("Kind of Blue");

		this.album.setLinks(new String[] { "link1", "link2" });

		this.album.addSong("So What");
		this.album.addSong("Flamenco Sketches");
		this.album.addSong("Freddie Freeloader");

		this.artist = new Artist();
		this.artist.setName("Miles Davis");
		this.format = new SimpleDateFormat("dd-MM-yyyy");
		this.artist.setBirthDate(this.format.parse("26-05-1926"));
		this.album.setArtist(this.artist);

		this.album.addMusician("Miles Davis", "Trumpet, Band leader");
		this.album.addMusician("Julian Adderley", "Alto Saxophone");
		this.album.addMusician("Paul Chambers", "double bass");

		// Config the mapping
		this.objectMapper = new ObjectMapper();
	}// End of setUp()

	@Test
	public void test_Default_Behavior_Of_Object_Mapping() throws Exception {

		// When
		String jsonArtist = this.objectMapper.writeValueAsString(this.artist);

		// Then
		System.out.println(jsonArtist);
		assertThat(jsonArtist, Matchers.containsString("\"age\":0"));
		assertThat(jsonArtist, Matchers.containsString("\"homeTown\":null"));
		assertThat(jsonArtist, Matchers.containsString("\"awardsWon\":[]"));
		assertThat(jsonArtist, Matchers.not(Matchers.containsString(
				"1926")));
	}

	@Test
	public void test_Indent_Output_Behavior_Of_Object_Mapping()
			throws JsonProcessingException {

		// Given
		this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT,
				true);

		// When
		String jsonArtist = this.objectMapper.writeValueAsString(this.artist);
		int lineCount = jsonArtist.split(System.lineSeparator()).length;

		// Then
		System.out.println(jsonArtist);
		System.out.println("json lines: " + lineCount);
		assertThat(lineCount, Matchers.greaterThan(5));
	}

	@Test
	public void test_Date_Format_Behavior_Of_Object_Mapping()
			throws JsonProcessingException {

		// Given
		this.objectMapper.setDateFormat(new SimpleDateFormat(
				"dd MMM yyyy"));

		// When
		String jsonArtist = this.objectMapper.writeValueAsString(this.artist);

		// Then
		System.out.println(jsonArtist);
		assertThat(jsonArtist, Matchers.containsString("26 May 1926"));
	}

	@Test
	public void test_Key_Order_Of_Map() throws Exception {

		// Given
		this.objectMapper.configure(
				SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,
				true);

		this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT,
				true);

		// When
		String jsonAlbum = this.objectMapper.writeValueAsString(this.album);
		int indexJulion = jsonAlbum.indexOf("Julion");
		int indexMiles = jsonAlbum.indexOf("Miles");
		int indexPaul = jsonAlbum.indexOf("Paul");

		// Then
		System.out.println(jsonAlbum);
		assertThat(indexJulion, Matchers.lessThan(indexMiles));
		assertThat(indexMiles, Matchers.lessThan(indexPaul));
	}

	@Test
	public void test_Not_Include_Empty_Fields() throws Exception {

		// Given
		this.objectMapper.setSerializationInclusion(
				JsonInclude.Include.NON_EMPTY);

		// When
		String jsonArtist = this.objectMapper.writeValueAsString(this.artist);

		// Then
		System.out.println(jsonArtist);
		assertThat(jsonArtist, Matchers.containsString("\"age\":0"));
		assertThat(jsonArtist, Matchers.not(Matchers.containsString(
				"homeTown")));
		assertThat(jsonArtist, Matchers.not(Matchers.containsString(
				"awardsWon")));
	}

}///:~