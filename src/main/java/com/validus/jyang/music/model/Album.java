package com.validus.jyang.music.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="album")
public class Album extends BaseModel {

	@Column(name="name")
	private String name;
	
	@Column(name="year_released")
	private int yearReleased;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="album")
	@Fetch(value=FetchMode.SUBSELECT)
	@JsonIgnore
	private List<Song> songs = new ArrayList<Song>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(
			name="artist_albums", 
			joinColumns=@JoinColumn(name="albums_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="artist_id", referencedColumnName="id"))
	private Artist artist;
	
	public Album() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYearReleased() {
		return yearReleased;
	}
	public void setYearReleased(int yearReleased) {
		this.yearReleased = yearReleased;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
}
