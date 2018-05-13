package com.pp.mybatis.model;

public class U2Base {

    private long userId;

    private long movieId;

    private String rating;

    public U2Base() {
    }

    public U2Base(long user, long movieId, String rating) {
        this.userId = user;
        this.movieId = movieId;
        this.rating = rating;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "U2Base{" +
                "userId=" + userId +
                ", movieId=" + movieId +
                ", rating='" + rating + '\'' +
                '}';
    }
}
