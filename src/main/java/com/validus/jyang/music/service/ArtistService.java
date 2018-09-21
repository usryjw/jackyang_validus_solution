package com.validus.jyang.music.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.validus.jyang.music.model.Artist;
import com.validus.jyang.music.repository.ArtistRepository;

@Service
public class ArtistService{

	@Autowired
	private ArtistRepository artistRepository;
	
	public List<Artist> getArtists() {
		List<Artist> artists = new ArrayList<Artist>();
		artistRepository.findAll()
		.forEach(artists::add);
		return artists;
	}

	public Artist addArtist(Artist theArtist) {
		theArtist.setCreated(new Date());
		return artistRepository.save(theArtist);
	}
	
	public Artist updateArtist(Artist theArtist) {
		theArtist.setLastModified(new Date());
		return artistRepository.save(theArtist);
	}

	public Artist getArtist(Long theId) {
		
		return artistRepository.findById(theId).orElse(null);
	}

	public void deleteArtist(Long theId) {
		
		artistRepository.deleteById(theId);
	}
}





