package com.validus.jyang.music.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.validus.jyang.music.model.Song;
import com.validus.jyang.music.repository.SongRepository;

@Service
public class SongService{

	@Autowired
	private SongRepository songRepository;
	
	public List<Song> getSongs() {
		List<Song> songs = new ArrayList<Song>();
		songRepository.findAll()
		.forEach(songs::add);
		return songs;
	}

	public Song addSong(Song theSong) {
		theSong.setCreated(new Date());
		return songRepository.save(theSong);
	}

	public Song updateSong(Song theSong) {

		theSong.setLastModified(new Date());
		return songRepository.save(theSong);
	}

	public Song getSong(Long theId) {
		
		return songRepository.findById(theId).orElse(null);
	}

	public void deleteSong(Long theId) {
		
		songRepository.deleteById(theId);
	}
}





