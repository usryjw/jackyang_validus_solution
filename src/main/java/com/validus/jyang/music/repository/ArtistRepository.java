package com.validus.jyang.music.repository;

import org.springframework.data.repository.CrudRepository;

import com.validus.jyang.music.model.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Long> {

}
