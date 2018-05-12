package com.pp.hibernate.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Movie2Actor.Movie2ActorPK.class)
@Table(name = "movies2actors", indexes = {
        @Index(columnList = "cast_num", name = "cast_num")
})
public class Movie2Actor {

    @Id
    @OneToOne
    @JoinColumn(name = "movieid")
    private Movie movieId;

    @Id
    @OneToOne
    @JoinColumn(name = "actorid")
    private Actor actorId;

    @Column(name = "cast_num")
    private int castNum;

    public Movie2Actor() {
    }

    public Movie2Actor(Movie movieId, Actor actorId, int castNum) {
        this.movieId = movieId;
        this.actorId = actorId;
        this.castNum = castNum;
    }

    @Embeddable
    public class Movie2ActorPK implements Serializable {

        private long movieId;

        private long actorId;

        public Movie2ActorPK() {
        }

        public Movie2ActorPK(long movieId, long actorId) {
            this.movieId = movieId;
            this.actorId = actorId;
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

        @Override
        public String toString() {
            return "Movie2ActorPK{" +
                    "movieId=" + movieId +
                    ", actorId=" + actorId +
                    '}';
        }
    }
}
