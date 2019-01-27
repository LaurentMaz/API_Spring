package com.myaudiolibrary.apirest.repository;

import com.myaudiolibrary.apirest.modele.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Integer> {



}
