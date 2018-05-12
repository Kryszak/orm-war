package com.pp.hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "u2base", indexes = {
        @Index(columnList = "rating", name = "rating"),
        @Index(columnList = "userid,movieid", name = "fakepk_u2b")
})
public class U2Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "userid")
    private User userId;

    @OneToOne
    @JoinColumn(name = "movieid")
    private Movie movieId;

    @Column(name = "rating")
    private String rating;

    public U2Base() {
    }

    public U2Base(User userId, Movie movieId, String rating, int id) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
