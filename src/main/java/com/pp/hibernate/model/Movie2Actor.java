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
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "actorid")
    private Actor actor;

    @Column(name = "cast_num")
    private int castNum;

    public Movie2Actor() {
    }

    public Movie2Actor(Movie movie, Actor actor, int castNum, int id) {
        this.movie = movie;
        this.actor = actor;
        this.castNum = castNum;
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movieId) {
        this.movie = movieId;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actorId) {
        this.actor = actorId;
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
                "movie=" + movie +
                ", actor=" + actor +
                ", castNum=" + castNum +
                '}';
    }
}
