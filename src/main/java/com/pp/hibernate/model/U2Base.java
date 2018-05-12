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
    private User user;

    @OneToOne
    @JoinColumn(name = "movieid")
    private Movie movie;

    @Column(name = "rating")
    private String rating;

    public U2Base() {
    }

    public U2Base(User user, Movie movieId, String rating, int id) {
        this.user = user;
        this.movie = movieId;
        this.rating = rating;
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "U2Base{" +
                "user=" + user +
                ", movie=" + movie +
                ", rating='" + rating + '\'' +
                '}';
    }
}
