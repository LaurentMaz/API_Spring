package com.myaudiolibrary.apirest.repository;

import com.myaudiolibrary.apirest.modele.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    //@Query("Select a from Artist a where lower(a.name) =lower(:Name)")
    //List<Artist> findByName(@Param("Name")String name);
    Page<Artist> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Artist findByName(String name);

}
