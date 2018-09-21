package com.validus.jyang.music.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.validus.jyang.music.TestUtil;
import com.validus.jyang.music.model.Artist;
import com.validus.jyang.music.repository.ArtistRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtistServiceTest {

	@Autowired
	private ArtistService artistService;

	@MockBean
	private ArtistRepository artistRepository;
	
	private static Artist mockArtist;
	
	@BeforeClass
	public static void setup() throws Exception {
		mockArtist = TestUtil.createArtistOne();
	}

	@Test
	public void testGetArtists() throws Exception {

		List<Artist> inArtists = TestUtil.createArtistList();

		Mockito.when(artistRepository.findAll()).thenReturn(inArtists);
		List<Artist> outArtists =artistService.getArtists();
		assertNotNull(outArtists);
		assertEquals(2, outArtists.size());
		assertEquals(inArtists, outArtists);
	}

	@Test
	public void testAddArtist() throws Exception {

		Mockito.when(artistRepository.save(mockArtist)).thenReturn(mockArtist);
		Artist actualArtist =artistService.addArtist(mockArtist);
		assertNotNull(actualArtist);
		assertEquals(mockArtist, actualArtist);
	}

	@Test
	public void testGetArtist() throws Exception {

		Mockito.when(artistRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockArtist));
		Artist actualArtist =artistService.getArtist(1L);
		assertNotNull(actualArtist);
		assertEquals(mockArtist, actualArtist);
	}

	@Test
	public void testSaveArtist() throws Exception {

		Mockito.when(artistRepository.save(mockArtist)).thenReturn(mockArtist);
		Artist actualArtist =artistService.updateArtist(mockArtist);
		assertNotNull(actualArtist);
		assertEquals(mockArtist, actualArtist);
	}

	@Test
	public void testDeleteArtist() throws Exception {

		Mockito.doNothing().when(artistRepository).deleteById(Mockito.anyLong());
		artistService.deleteArtist(1L);
	}
}
