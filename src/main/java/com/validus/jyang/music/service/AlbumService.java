package com.validus.jyang.music.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.validus.jyang.music.model.Album;
import com.validus.jyang.music.repository.AlbumRepository;

@Service
public class AlbumService{

	@Autowired
	private AlbumRepository albumRepository;
	
	public List<Album> getAlbums() {
		List<Album> albums = new ArrayList<Album>();
		albumRepository.findAll()
		.forEach(albums::add);
		return albums;
	}

	public Album addAlbum(Album theAlbum) {
		theAlbum.setCreated(new Date());
		return albumRepository.save(theAlbum);
	}

	public Album updateAlbum(Album theAlbum) {
		theAlbum.setLastModified(new Date());
		return albumRepository.save(theAlbum);
	}

	public Album getAlbum(Long theId) {
		
		return albumRepository.findById(theId).orElse(null);
	}

	public void deleteAlbum(Long theId) {
		
		albumRepository.deleteById(theId);
	}
}





