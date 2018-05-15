package com.pp.ebean.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.ebean.Model;

@Entity
@Table(name = "movies2directors", indexes = {
        @Index(columnList = "genre", name = "genre"),
        @Index(columnList = "movieid,directorid", name = "fakepk_m2d")
})
public class Movie2Director extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "movieid")
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "directorid")
    private Director director;

    @Column(name = "genre")
    private String genre;

    public Movie2Director() {
    }

    public Movie2Director(Movie movie, Director directorId, String genre, int id) {
        this.movie = movie;
        this.director = directorId;
        this.genre = genre;
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movieId) {
        this.movie = movieId;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director directorId) {
        this.director = directorId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Movie2Director{" +
                "movie=" + movie +
                ", director=" + director +
                ", genre='" + genre + '\'' +
                '}';
    }
}
