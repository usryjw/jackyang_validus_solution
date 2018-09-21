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
import com.validus.jyang.music.model.Song;
import com.validus.jyang.music.repository.SongRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SongServiceTest {

	@Autowired
	private SongService songService;

	@MockBean
	private SongRepository songRepository;
	
	private static Song mockSong;
	
	@BeforeClass
	public static void setup() throws Exception {
		mockSong = TestUtil.createSongOne();
	}

	@Test
	public void testGetSongs() throws Exception {

		List<Song> inSongs = TestUtil.createSongList();

		Mockito.when(songRepository.findAll()).thenReturn(inSongs);
		List<Song> outSongs =songService.getSongs();
		assertNotNull(outSongs);
		assertEquals(2, outSongs.size());
		assertEquals(inSongs, outSongs);
	}

	@Test
	public void testAddSong() throws Exception {

		Mockito.when(songRepository.save(mockSong)).thenReturn(mockSong);
		Song actualSong =songService.addSong(mockSong);
		assertNotNull(actualSong);
		assertEquals(mockSong, actualSong);
	}

	@Test
	public void testGetSong() throws Exception {

		Mockito.when(songRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockSong));
		Song actualSong =songService.getSong(1L);
		assertNotNull(actualSong);
		assertEquals(mockSong, actualSong);
	}

	@Test
	public void testSaveSong() throws Exception {

		Mockito.when(songRepository.save(mockSong)).thenReturn(mockSong);
		Song actualSong =songService.updateSong(mockSong);
		assertNotNull(actualSong);
		assertEquals(mockSong, actualSong);
	}

	@Test
	public void testDeleteSong() throws Exception {

		Mockito.doNothing().when(songRepository).deleteById(Mockito.anyLong());
		songService.deleteSong(1L);
	}
}
