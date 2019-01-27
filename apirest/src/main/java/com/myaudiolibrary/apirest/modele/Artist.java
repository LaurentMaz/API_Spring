package com.myaudiolibrary.apirest.modele;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "artist")
public class Artist implements Serializable {

    // ------------------ ATTRIBUTS ------------------ //
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    // ------------------ RELATION ONETOMANY ------------------ //

    @OneToMany( mappedBy = "artist" )
    @JsonIgnoreProperties("artist")
    private List<Album> albums;

    // ------------------ CONSTRUCTORS ------------------ //
    public Artist(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Artist (){

    }

    // ------------------ GETTER/SETTERS ------------------ //
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
