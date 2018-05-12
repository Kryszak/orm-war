package com.pp.hibernate.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Movie2Director.Movie2DirectorPK.class)
@Table(name = "movies2directors", indexes = {
        @Index(columnList = "genre", name = "genre")
})
public class Movie2Director {

    @Id
    @OneToOne
    @JoinColumn(name = "movieid")
    private Movie movieId;

    @Id
    @OneToOne
    @JoinColumn(name = "directorid")
    private Director directorId;

    @Column(name = "genre")
    private String genre;

    public Movie2Director() {
    }

    public Movie2Director(Movie movieId, Director directorId, String genre) {
        this.movieId = movieId;
        this.directorId = directorId;
        this.genre = genre;
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

    @Embeddable
    public class Movie2DirectorPK implements Serializable {

        private long movieId;

        private long directorId;

        public Movie2DirectorPK() {
        }

        public Movie2DirectorPK(long movieId, long directorId) {
            this.movieId = movieId;
            this.directorId = directorId;
        }

        public long getMovieId() {
            return movieId;
        }

        public void setMovieId(long movieId) {
            this.movieId = movieId;
        }

        public long getDirectorId() {
            return directorId;
        }

        public void setDirectorId(long directorId) {
            this.directorId = directorId;
        }
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
