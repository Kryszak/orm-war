package com.pp.mybatis.model;

import javax.persistence.*;

public class Movie {

    private long movieId;

    private int year;

    private boolean isEnglish;

    private String country;

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
