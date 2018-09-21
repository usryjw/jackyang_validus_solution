package com.validus.jyang.music.rest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.validus.jyang.music.TestUtil;
import com.validus.jyang.music.model.Album;
import com.validus.jyang.music.service.AlbumService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=AlbumRestController.class, secure=false)
public class AlbumRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AlbumService albumService;
	
	private ObjectMapper objectMapper = new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	private static Album mockAlbum;
	
	@BeforeClass
	public static void setup() throws Exception {
		mockAlbum = TestUtil.getAlbumOne();
	}

	@Test
	public void testAddAlbum() throws Exception {
		
		String inputJson = objectMapper.writeValueAsString(mockAlbum);
		
		String URI = "/api/Albums";
		Mockito.when(albumService.updateAlbum(Mockito.any(Album.class))).thenReturn(mockAlbum);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON)
				.content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputJson = response.getContentAsString();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(inputJson, outputJson);
		
	}

	@Test
	public void testGetAlbums() throws Exception {

		List<Album> albums = TestUtil.createAlbumList();

		String inputJson = objectMapper.writeValueAsString(albums);
		
		String URI = "/api/Albums";
		Mockito.when(albumService.getAlbums()).thenReturn(albums);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputJson = response.getContentAsString();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(inputJson, outputJson);
		
	}
	

	@Test
	public void testGetAlbum() throws Exception {
		
		String inputJson = objectMapper.writeValueAsString(mockAlbum);
		
		String URI = "/api/Albums/1";
		Mockito.when(albumService.getAlbum(Mockito.anyLong())).thenReturn(mockAlbum);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputJson = response.getContentAsString();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(inputJson, outputJson);
		
	}
	
	@Test
	public void testUpdateAlbum() throws Exception {
		
		String inputJson = objectMapper.writeValueAsString(mockAlbum);
		
		String URI = "/api/Albums";
		Mockito.when(albumService.updateAlbum(Mockito.any(Album.class))).thenReturn(mockAlbum);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(URI)
				.accept(MediaType.APPLICATION_JSON)
				.content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputJson = response.getContentAsString();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(inputJson, outputJson);
		
	}
	
	@Test
	public void testDeleteAlbum() throws Exception {
		
		String inputJson = objectMapper.writeValueAsString(mockAlbum);
	
		String URI = "/api/Albums/1";
		
		Mockito.when(albumService.getAlbum(Mockito.anyLong())).thenReturn(mockAlbum);
		
		albumService = Mockito.mock(AlbumService.class);
		Mockito.doNothing().when(albumService).deleteAlbum(Mockito.anyLong());
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(URI)
				.accept(MediaType.APPLICATION_JSON)
				.content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputJson = response.getContentAsString();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("Deleted Album id - 1", outputJson);
		
	}
	

}
