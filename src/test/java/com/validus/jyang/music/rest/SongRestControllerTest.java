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
import com.validus.jyang.music.model.Song;
import com.validus.jyang.music.service.SongService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=SongRestController.class, secure=false)
public class SongRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SongService songService;
	
	private ObjectMapper objectMapper = new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	private static Song mockSong;
	
	@BeforeClass
	public static void setup() throws Exception {
		mockSong = TestUtil.createSongOne();
	}

	@Test
	public void testAddSong() throws Exception {
		
		String inputJson = objectMapper.writeValueAsString(mockSong);
		
		String URI = "/api/Songs";
		Mockito.when(songService.updateSong(Mockito.any(Song.class))).thenReturn(mockSong);
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
	public void testGetSongs() throws Exception {

		List<Song> songs = TestUtil.createSongList();

		String inputJson = objectMapper.writeValueAsString(songs);
		
		String URI = "/api/Songs";
		Mockito.when(songService.getSongs()).thenReturn(songs);
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
	public void testGetSong() throws Exception {
		
		String inputJson = objectMapper.writeValueAsString(mockSong);
		
		String URI = "/api/Songs/1";
		Mockito.when(songService.getSong(Mockito.anyLong())).thenReturn(mockSong);
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
	public void testGetSongNotFound() throws Exception {
		
		String URI = "/api/Songs/1";
		Mockito.when(songService.getSong(Mockito.anyLong())).thenReturn(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		
	}

	@Test
	public void testGetSongBadRequest() throws Exception {
		
		String URI = "/api/Songs/abc";
		Mockito.when(songService.getSong(Mockito.anyLong())).thenReturn(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		
	}

	
	@Test
	public void testUpdateSong() throws Exception {
		
		String inputJson = objectMapper.writeValueAsString(mockSong);
		
		String URI = "/api/Songs";
		Mockito.when(songService.updateSong(Mockito.any(Song.class))).thenReturn(mockSong);
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
	public void testDeleteSong() throws Exception {
		
		String inputJson = objectMapper.writeValueAsString(mockSong);
	
		String URI = "/api/Songs/1";
		
		Mockito.when(songService.getSong(Mockito.anyLong())).thenReturn(mockSong);
		
		songService = Mockito.mock(SongService.class);
		Mockito.doNothing().when(songService).deleteSong(Mockito.anyLong());
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(URI)
				.accept(MediaType.APPLICATION_JSON)
				.content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputJson = response.getContentAsString();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("Deleted Song id - 1", outputJson);
		
	}
	

}
