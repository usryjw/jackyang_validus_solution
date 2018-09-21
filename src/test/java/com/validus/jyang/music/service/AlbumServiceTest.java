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
import com.validus.jyang.music.model.Album;
import com.validus.jyang.music.repository.AlbumRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumServiceTest {

	@Autowired
	private AlbumService albumService;

	@MockBean
	private AlbumRepository albumRepository;
	
	private static Album mockAlbum;
	
	@BeforeClass
	public static void setup() throws Exception {
		mockAlbum = TestUtil.getAlbumOne();
	}

	@Test
	public void testGetAlbums() throws Exception {

		List<Album> inAlbums = TestUtil.createAlbumList();

		Mockito.when(albumRepository.findAll()).thenReturn(inAlbums);
		List<Album> outAlbums =albumService.getAlbums();
		assertNotNull(outAlbums);
		assertEquals(2, outAlbums.size());
		assertEquals(inAlbums, outAlbums);
	}

	@Test
	public void testAddAlbum() throws Exception {

		Mockito.when(albumRepository.save(mockAlbum)).thenReturn(mockAlbum);
		Album actualAlbum =albumService.addAlbum(mockAlbum);
		assertNotNull(actualAlbum);
		assertEquals(mockAlbum, actualAlbum);
	}

	@Test
	public void testGetAlbum() throws Exception {

		Mockito.when(albumRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockAlbum));
		Album actualAlbum =albumService.getAlbum(1L);
		assertNotNull(actualAlbum);
		assertEquals(mockAlbum, actualAlbum);
	}

	@Test
	public void testSaveAlbum() throws Exception {

		Mockito.when(albumRepository.save(mockAlbum)).thenReturn(mockAlbum);
		Album actualAlbum =albumService.updateAlbum(mockAlbum);
		assertNotNull(actualAlbum);
		assertEquals(mockAlbum, actualAlbum);
	}

	@Test
	public void testDeleteAlbum() throws Exception {

		Mockito.doNothing().when(albumRepository).deleteById(Mockito.anyLong());
		albumService.deleteAlbum(1L);
	}
}
