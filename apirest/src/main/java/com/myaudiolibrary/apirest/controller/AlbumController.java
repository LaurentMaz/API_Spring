package com.myaudiolibrary.apirest.controller;


import com.myaudiolibrary.apirest.modele.Album;
import com.myaudiolibrary.apirest.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/albums" )

public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    //  ---------------- 7 - Ajout d'un album ----------  //

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Album createAlbum(@RequestBody Album album){
        return albumRepository.save(album);
    }

    //  ---------------- 8 - Suppression d'un album ----------  //

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Integer id){
        albumRepository.delete(id);
    }

}
