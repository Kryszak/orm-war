package com.pp.mybatis.model;

public class Movie2Actor {

    private long movieId;

    private long actorId;

    private int castNum;

    public Movie2Actor() {
    }

    public Movie2Actor(long movieId, long actorId, int castNum) {
        this.movieId = movieId;
        this.actorId = actorId;
        this.castNum = castNum;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getActorId() {
        return actorId;
    }

    public void setActorId(long actorId) {
        this.actorId = actorId;
    }

    public int getCastNum() {
        return castNum;
    }

    public void setCastNum(int castNum) {
        this.castNum = castNum;
    }


    @Override
    public String toString() {
        return "Movie2Actor{" +
                "movieId=" + movieId +
                ", actorId=" + actorId +
                ", castNum=" + castNum +
                '}';
    }
}
