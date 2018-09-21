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
import com.validus.jyang.music.model.Artist;
import com.validus.jyang.music.service.ArtistService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=ArtistRestController.class, secure=false)
public class ArtistRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ArtistService artistService;
	
	private ObjectMapper objectMapper = new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	private static Artist mockArtist;
	
	@BeforeClass
	public static void setup() throws Exception {
		mockArtist = TestUtil.createArtistOne();
	}

	@Test
	public void testAddArtist() throws Exception {
		
		String inputJson = objectMapper.writeValueAsString(mockArtist);
		
		String URI = "/api/Artists";
		Mockito.when(artistService.updateArtist(Mockito.any(Artist.class))).thenReturn(mockArtist);
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
	public void testGetArtists() throws Exception {

		List<Artist> artists = TestUtil.createArtistList();

		String inputJson = objectMapper.writeValueAsString(artists);
		
		String URI = "/api/Artists";
		Mockito.when(artistService.getArtists()).thenReturn(artists);
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
	public void testGetArtist() throws Exception {
		
		String inputJson = objectMapper.writeValueAsString(mockArtist);
		
		String URI = "/api/Artists/1";
		Mockito.when(artistService.getArtist(Mockito.anyLong())).thenReturn(mockArtist);
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
	public void testUpdateArtist() throws Exception {
		
		String inputJson = objectMapper.writeValueAsString(mockArtist);
		
		String URI = "/api/Artists";
		Mockito.when(artistService.updateArtist(Mockito.any(Artist.class))).thenReturn(mockArtist);
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
	public void testDeleteArtist() throws Exception {
		
		String inputJson = objectMapper.writeValueAsString(mockArtist);
	
		String URI = "/api/Artists/1";
		
		Mockito.when(artistService.getArtist(Mockito.anyLong())).thenReturn(mockArtist);
		
		artistService = Mockito.mock(ArtistService.class);
		Mockito.doNothing().when(artistService).deleteArtist(Mockito.anyLong());
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(URI)
				.accept(MediaType.APPLICATION_JSON)
				.content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputJson = response.getContentAsString();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("Deleted Artist id - 1", outputJson);
		
	}
	

}
