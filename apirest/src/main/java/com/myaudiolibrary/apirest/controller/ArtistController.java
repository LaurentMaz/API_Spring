package com.myaudiolibrary.apirest.controller;
import com.myaudiolibrary.apirest.modele.Artist;
import com.myaudiolibrary.apirest.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value ="/artists" )
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    //  ---------------- 1 - Afficher un artiste ----------  //

    @RequestMapping(method = RequestMethod.GET,value = "/{id}", produces = "application/json")
    public Artist getArtById(@PathVariable Integer id)
    {
        Artist a = artistRepository.findOne(id);
        if (a == null)
        {
            throw new EntityNotFoundException("L'artiste d'id "+id+" n'existe pas");
        }
        return a;
    }

    //  ---------------- 2 - Recherche par nom ----------  //

    @RequestMapping(params = {"name", "page", "size", "sortProperty", "sortDirection"})
    public Page<Artist> getArt(@RequestParam("name") String name,
                               @RequestParam("page") Integer page,
                               @RequestParam("size") Integer size,
                               @RequestParam("sortProperty") String sortProperty,
                               @RequestParam("sortDirection") Sort.Direction sortDirection)
    {
        PageRequest pageRequest = new PageRequest(page, size, sortDirection, sortProperty);

        return artistRepository.findByNameContainingIgnoreCase(name, pageRequest);
    }

    //  ---------------- 3 - Liste des artistes ----------  //


    @RequestMapping(params = {"page", "size", "sortProperty", "sortDirection"})
    public Page<Artist> findAllPaging(@RequestParam("page") Integer page,
                                      @RequestParam("size") Integer size,
                                      @RequestParam("sortProperty") String sortProperty,
                                      @RequestParam("sortDirection") String sortDirection)
    {
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        return artistRepository.findAll(pageRequest);
    }
    //  ---------------- 4 - Création d'un artiste ----------  //


    @RequestMapping(value = "", method = RequestMethod.POST)
    public Artist createArtist(@RequestBody Artist artist){
        Artist artistTmp = artistRepository.findByName(artist.getName());
        if(artistTmp == null)
        {
            return artistRepository.save(artist);
        }
        else
        {
            throw new EntityExistsException("L'artiste existe déjà");
        }

    }

    //  ---------------- 5 - Modification d'un artiste ----------  //

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Artist updateArtist(@PathVariable Integer id, @RequestBody Artist artist){
        return artistRepository.save(artist);
    }

    //  ---------------- 6 - Suppression d'un artiste ----------  //

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable Integer id){
        if(artistRepository.findOne(id)==null)
        {
            throw new EntityNotFoundException("L'artist d'identifiant "+id+" n'existe pas");
        }
        artistRepository.delete(id);
    }



}
