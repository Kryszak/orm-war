package com.pp.mybatis.model;

public class U2BaseFull {

    private User user;

    private Movie movie;

    private String rating;

    public U2BaseFull() {
    }

    public U2BaseFull(User user, Movie movie, String rating) {
        this.user = user;
        this.movie = movie;
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "U2BaseFull{" +
                "user=" + user +
                ", movie=" + movie +
                ", rating='" + rating + '\'' +
                '}';
    }
}
