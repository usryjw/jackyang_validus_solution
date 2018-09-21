package com.validus.jyang.music.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.validus.jyang.music.model.Artist;
import com.validus.jyang.music.service.ArtistService;

@RestController
public class ArtistRestController {

	@Autowired
	private ArtistService artistService;
	
	@RequestMapping("/api/Artists")
	public List<Artist> getArtists() {
		
		return artistService.getArtists();
		
	}
	
	@RequestMapping("/api/Artists/{ArtistId}")
	public Artist getArtist(@PathVariable Long ArtistId) {
		
		Artist theArtist = artistService.getArtist(ArtistId);
		
		if (theArtist == null) {
			throw new MusicNotFoundException("Artist id not found - " + ArtistId);
		}
		
		return theArtist;
	}
	
	@RequestMapping(value="/api/Artists", method=RequestMethod.POST)
	public Artist addArtist(@RequestBody Artist theArtist) {
		
		artistService.addArtist(theArtist);
		
		return theArtist;
	}
	
	@RequestMapping(value="/api/Artists", method=RequestMethod.PUT)
	public Artist updateArtist(@RequestBody Artist theArtist) {
		
		artistService.updateArtist(theArtist);
		
		return theArtist;
		
	}
	
	@RequestMapping(value="/api/Artists/{ArtistId}", method=RequestMethod.DELETE)
	public String deleteArtist(@PathVariable Long ArtistId) {
		
		Artist tempArtist = artistService.getArtist(ArtistId);
		
		// throw exception if null
		
		if (tempArtist == null) {
			throw new MusicNotFoundException("Artist id not found - " + ArtistId);
		}
				
		artistService.deleteArtist(ArtistId);
		
		return "Deleted Artist id - " + ArtistId;
	}
	
}


















