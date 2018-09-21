package com.validus.jyang.music.repository;

import org.springframework.data.repository.CrudRepository;

import com.validus.jyang.music.model.Song;

public interface SongRepository extends CrudRepository<Song, Long> {

}
