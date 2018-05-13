package com.pp.mybatis.model;

import javax.persistence.*;

public class User {

    private long userId;

    private String age;

    private String userGender;

    private String occupation;

    public User() {
    }

    public User(long userId, String age, String userGender, String occupation) {
        this.userId = userId;
        this.age = age;
        this.userGender = userGender;
        this.occupation = occupation;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", age='" + age + '\'' +
                ", userGender='" + userGender + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
