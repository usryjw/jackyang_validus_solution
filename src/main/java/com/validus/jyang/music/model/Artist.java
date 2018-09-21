package com.validus.jyang.music.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="artist")
public class Artist extends BaseModel {

	private String name;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="artist_albums", 
			joinColumns={@JoinColumn(name="artist_id")},
			inverseJoinColumns={@JoinColumn(name="albums_id")})
	@JsonIgnore
	private List<Album> albums= new ArrayList<Album>();

	public Artist() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
	
}
