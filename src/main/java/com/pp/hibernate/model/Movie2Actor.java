package com.pp.hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "movies2actors", indexes = {
        @Index(columnList = "cast_num", name = "cast_num"),
        @Index(columnList = "movieid,actorid", name = "fakepk_m2a")
})
public class Movie2Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "movieid")
    private Movie movieId;

    @OneToOne
    @JoinColumn(name = "actorid")
    private Actor actorId;

    @Column(name = "cast_num")
    private int castNum;

    public Movie2Actor() {
    }

    public Movie2Actor(Movie movieId, Actor actorId, int castNum, int id) {
        this.movieId = movieId;
        this.actorId = actorId;
        this.castNum = castNum;
        this.id = id;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    public Actor getActorId() {
        return actorId;
    }

    public void setActorId(Actor actorId) {
        this.actorId = actorId;
    }

    public int getCastNum() {
        return castNum;
    }

    public void setCastNum(int castNum) {
        this.castNum = castNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
