package com.validus.jyang.music.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.validus.jyang.music.model.Song;
import com.validus.jyang.music.service.SongService;

@RestController

public class SongRestController {

	@Autowired
	private SongService songService;
	
	// add mapping for GET /Songs
	@RequestMapping("/api/Songs")
	public List<Song> getSongs() {
		
		return songService.getSongs();
		
	}
	
	// add mapping for GET /Songs/{SongId}
	
	@RequestMapping("/api/Songs/{SongId}")
	public Song getSong(@PathVariable Long SongId) {
		
		Song theSong = songService.getSong(SongId);
		
		if (theSong == null) {
			throw new MusicNotFoundException("Song id not found - " + SongId);
		}
		
		return theSong;
	}
	
	@RequestMapping(value="/api/Songs", method=RequestMethod.POST)
	public Song addSong(@RequestBody Song theSong) {
		
		songService.addSong(theSong);
		
		return theSong;
	}
	
	@RequestMapping(value="/api/Songs", method=RequestMethod.PUT)
	public Song updateSong(@RequestBody Song theSong) {
		
		songService.updateSong(theSong);
		
		return theSong;
		
	}
	
	@RequestMapping(value="/api/Songs/{SongId}", method=RequestMethod.DELETE)
	public String deleteSong(@PathVariable Long SongId) {
		
		Song tempSong = songService.getSong(SongId);
		
		// throw exception if null
		
		if (tempSong == null) {
			throw new MusicNotFoundException("Song id not found - " + SongId);
		}
				
		songService.deleteSong(SongId);
		
		return "Deleted Song id - " + SongId;
	}
	
}


















