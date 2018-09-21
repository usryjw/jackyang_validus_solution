package com.validus.jyang.music;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.validus.jyang.music.model.Album;
import com.validus.jyang.music.model.Artist;
import com.validus.jyang.music.model.Song;

public class TestUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static Album albumOne;
	public static Song createSongOne() throws Exception{
		Song song = new Song();
		song.setId(1L);
		song.setCreated(sdf.parse("2018-09-14"));
		song.setLastModified(sdf.parse("2018-09-15"));
		song.setName("First Song");
		song.setTrack(101);
		albumOne = createAlbumOne();
		song.setAlbum(albumOne);
		albumOne.getSongs().add(song);
		return song;
	}

	public static Song createSongTwo() throws Exception{
		Song song2 = new Song();
		song2.setId(2L);
		song2.setCreated(sdf.parse("2018-08-14"));
		song2.setLastModified(sdf.parse("2018-08-15"));
		song2.setName("Second Song");
		song2.setTrack(102);
		return song2;
	}

	public static List<Song> createSongList() throws Exception{
		Song mockSong1 = createSongOne();
		
		Song mockSong2 = createSongTwo();

		List<Song> songs = new ArrayList<Song>();
		songs.add(mockSong1);
		songs.add(mockSong2);

		return songs;
	}
	
	private static Album createAlbumOne() throws Exception{
		Album album = new Album();
		album.setId(1L);
		album.setName("AlbumOne");
		album.setCreated(sdf.parse("2017-01-01"));
		album.setLastModified(sdf.parse("2017-05-25"));
		album.setYearReleased(2017);
		return album;
	}

	private static Album createAlbumTwo() throws Exception{
		Album album2 = new Album();
		album2.setId(2L);
		album2.setName("AlbumTwo");
		album2.setYearReleased(2018);
		album2.setCreated(sdf.parse("2018-02-01"));
		album2.setLastModified(sdf.parse("2018-06-25"));
		return album2;
	}
	
	public static Album getAlbumOne() throws Exception{
		if(albumOne==null){
			createSongOne();
		};
		return albumOne;
	}

	public static List<Album> createAlbumList() throws Exception{
		Album album1 = createAlbumOne();
		
		Album album2 = createAlbumTwo();

		List<Album> albums = new ArrayList<Album>();
		albums.add(album1);
		albums.add(album2);

		return albums;
	}

	public static Artist createArtistOne() throws Exception{
		Artist artist = new Artist();
		artist.setId(1L);
		artist.setCreated(sdf.parse("1999-01-31"));
		artist.setLastModified(sdf.parse("2018-02-01"));
		artist.setName("ArtistOne");
		artist.setAlbums(createAlbumList());
		return artist;
	}
	
	public static List<Artist> createArtistList() throws Exception{
		Artist artist1 = createArtistOne();
		
		Artist artist2 = new Artist();
		artist2.setCreated(sdf.parse("2001-05-31"));
		artist2.setLastModified(sdf.parse("2018-09-01"));
		artist2.setName("ArtistTwo");
		artist2.setAlbums(createAlbumList());

		List<Artist> artists = new ArrayList<Artist>();
		artists.add(artist1);
		artists.add(artist2);

		return artists;
	}

}
