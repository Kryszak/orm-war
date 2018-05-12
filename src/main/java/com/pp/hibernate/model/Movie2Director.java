package com.pp.hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "movies2directors", indexes = {
        @Index(columnList = "genre", name = "genre"),
        @Index(columnList = "movieid,directorid", name = "fakepk_m2d")
})
public class Movie2Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "movieid")
    private Movie movieId;

    @OneToOne
    @JoinColumn(name = "directorid")
    private Director directorId;

    @Column(name = "genre")
    private String genre;

    public Movie2Director() {
    }

    public Movie2Director(Movie movieId, Director directorId, String genre, int id) {
        this.movieId = movieId;
        this.directorId = directorId;
        this.genre = genre;
        this.id = id;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    public Director getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Director directorId) {
        this.directorId = directorId;
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
                "movieId=" + movieId +
                ", directorId=" + directorId +
                ", genre='" + genre + '\'' +
                '}';
    }
}
