package com.validus.jyang.music.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="song")
public class Song extends BaseModel {

	@Column(name = "track")
	private int track;
	
	@Column(name = "name")
	private String name;

	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name="album_id")
	private Album album;
	
	public int getTrack() {
		return track;
	}
	public void setTrack(int track) {
		this.track = track;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public Song()
	{
		super();
	}
	
}
