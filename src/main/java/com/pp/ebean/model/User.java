package com.pp.ebean.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import io.ebean.Model;

@Entity
@Table(name = "users", indexes = {
        @Index(columnList = "age", name = "age"),
        @Index(columnList = "u_gender", name = "u_gender"),
        @Index(columnList = "occupation", name = "occupation")
})
public class User extends Model {

    @Id
    @Column(name = "userid")
    private long userId;

    @Column(name = "age")
    private String age;

    @Column(name = "u_gender")
    private String userGender;

    @Column(name = "occupation")
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
