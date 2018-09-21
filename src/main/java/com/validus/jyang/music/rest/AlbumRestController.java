package com.validus.jyang.music.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.validus.jyang.music.model.Album;
import com.validus.jyang.music.service.AlbumService;

@RestController
public class AlbumRestController {

	@Autowired
	private AlbumService albumService;
	
	@RequestMapping("/api/Albums")
	public List<Album> getAlbums() {
		
		return albumService.getAlbums();
		
	}
	
	@RequestMapping("/api/Albums/{AlbumId}")
	public Album getAlbum(@PathVariable Long AlbumId) {
		
		Album theAlbum = albumService.getAlbum(AlbumId);
		
		if (theAlbum == null) {
			throw new MusicNotFoundException("Album id not found - " + AlbumId);
		}
		
		return theAlbum;
	}
	
	@RequestMapping(value="/api/Albums", method=RequestMethod.POST)
	public Album addAlbum(@RequestBody Album theAlbum) {
		
		albumService.addAlbum(theAlbum);
		
		return theAlbum;
	}
	
	@RequestMapping(value="/api/Albums", method=RequestMethod.PUT)
	public Album updateAlbum(@RequestBody Album theAlbum) {
		
		albumService.updateAlbum(theAlbum);
		
		return theAlbum;
		
	}
	
	@RequestMapping(value="/api/Albums/{AlbumId}", method=RequestMethod.DELETE)
	public String deleteAlbum(@PathVariable Long AlbumId) {
		
		Album tempAlbum = albumService.getAlbum(AlbumId);
		
		// throw exception if null
		
		if (tempAlbum == null) {
			throw new MusicNotFoundException("Album id not found - " + AlbumId);
		}
				
		albumService.deleteAlbum(AlbumId);
		
		return "Deleted Album id - " + AlbumId;
	}
	
}


















