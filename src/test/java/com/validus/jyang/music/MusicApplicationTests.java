package com.validus.jyang.music;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.validus.jyang.music.model.Song;
import com.validus.jyang.music.repository.SongRepository;

/**
 * Integration Testing suite, just one as example at this point
 * @author jack
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes ={MusicApplication.class}, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MusicApplicationTests {

	@LocalServerPort
	private int port;
	
	@Autowired
	private SongRepository songRepository;

	TestRestTemplate restTemplate = new TestRestTemplate();
	private ObjectMapper objectMapper = new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	HttpHeaders headers = new HttpHeaders();
	
	private static Song song;
	
	@BeforeClass
	public static void setup() throws Exception {
		song = TestUtil.createSongOne();
	}
  	
	@Test
	public void testAddSong() throws Exception {


		HttpEntity<Song> entity = new HttpEntity<Song>(song, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/Songs"),
				HttpMethod.POST, entity, String.class);

		String expected = objectMapper.writeValueAsString(song);

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
