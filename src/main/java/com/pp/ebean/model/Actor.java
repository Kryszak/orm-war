package com.pp.ebean.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import io.ebean.Model;

@Entity
@Table(name = "actors", indexes = {
        @Index(columnList = "a_gender", name = "a_gender"),
        @Index(columnList = "a_quality", name = "a_quality")
})
public class Actor extends Model {

    @Id
    @Column(name = "actorid")
    private long actorId;

    @Column(name = "a_gender")
    private String actorGender;

    @Column(name = "a_quality")
    private int actorQuality;

    public Actor() {
    }

    public Actor(long actorId, String actorGender, int actorQuality) {
        this.actorId = actorId;
        this.actorGender = actorGender;
        this.actorQuality = actorQuality;
    }

    public long getActorId() {
        return actorId;
    }

    public void setActorId(long actorId) {
        this.actorId = actorId;
    }

    public String getActorGender() {
        return actorGender;
    }

    public void setActorGender(String actorGender) {
        this.actorGender = actorGender;
    }

    public int getActorQuality() {
        return actorQuality;
    }

    public void setActorQuality(int actorQuality) {
        this.actorQuality = actorQuality;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "actorId=" + actorId +
                ", actorGender='" + actorGender + '\'' +
                ", actorQuality=" + actorQuality +
                '}';
    }
}
