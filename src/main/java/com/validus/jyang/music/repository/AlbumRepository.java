package com.validus.jyang.music.repository;

import org.springframework.data.repository.CrudRepository;

import com.validus.jyang.music.model.Album;

public interface AlbumRepository extends CrudRepository<Album, Long> {

}
