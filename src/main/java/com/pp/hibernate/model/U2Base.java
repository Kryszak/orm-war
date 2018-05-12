package com.pp.hibernate.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(U2Base.U2BasePK.class)
@Table(name = "u2base", indexes = {
        @Index(columnList = "rating", name = "rating")
})
public class U2Base {

    @Id
    @OneToOne
    @JoinColumn(name = "userid")
    private User userId;

    @Id
    @OneToOne
    @JoinColumn(name = "movieid")
    private Movie movieId;

    @Column(name = "rating")
    private String rating;

    public U2Base() {
    }

    public U2Base(User userId, Movie movieId, String rating) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movie) {
        this.movieId = movie;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Embeddable
    public class U2BasePK implements Serializable {

        private long userId;

        private long movieId;

        public U2BasePK() {
        }

        public U2BasePK(long userId, long movieId) {
            this.userId = userId;
            this.movieId = movieId;
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
