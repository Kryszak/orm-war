package com.pp.ebean.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import io.ebean.Model;

@Entity
@Table(name = "movies", indexes = {
        @Index(columnList = "year", name = "year"),
        @Index(columnList = "isEnglish", name = "isEnglish"),
        @Index(columnList = "country", name = "country"),
        @Index(columnList = "runningtime", name = "runningtime")
})
public class Movie extends Model {

    @Id
    @Column(name = "movieid")
    private long movieId;

    @Column(name = "year")
    private int year;

    @Column(name = "isEnglish")
    private boolean isEnglish;

    @Column(name = "country")
    private String country;

    @Column(name = "runningtime")
    private int runningTime;

    public Movie() {
    }

    public Movie(long movieId, int year, boolean isEnglish, String country, int runningTime) {
        this.movieId = movieId;
        this.year = year;
        this.isEnglish = isEnglish;
        this.country = country;
        this.runningTime = runningTime;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isEnglish() {
        return isEnglish;
    }

    public void setEnglish(boolean english) {
        isEnglish = english;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", year=" + year +
                ", isEnglish=" + isEnglish +
                ", country='" + country + '\'' +
                ", runningTime=" + runningTime +
                '}';
    }
}
