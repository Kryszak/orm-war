package com.pp.mybatis.model;

public class Movie2Director {

    private long movieId;

    private long directorId;

    private String genre;

    public Movie2Director() {
    }

    public Movie2Director(long movieId, long directorId, String genre) {
        this.movieId = movieId;
        this.directorId = directorId;
        this.genre = genre;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
