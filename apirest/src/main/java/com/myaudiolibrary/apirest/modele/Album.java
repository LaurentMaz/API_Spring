package com.myaudiolibrary.apirest.modele;

import javax.persistence.*;

@Entity
@Table(name = "album")
public class Album {

    // ------------------ ATTRIBUTS ------------------ //
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;

    // ------------------ RELATION MANYTOONE ------------------ //
    @ManyToOne
    @JoinColumn( name = "artistId" )
    private Artist artist;

    // ------------------ CONSTRUCTORS ------------------ //


    public Album(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Album() {
    }

    // ------------------ GETTER/SETTERS ------------------ //

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
