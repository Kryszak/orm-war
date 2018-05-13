package com.pp.mybatis.model;

public class Actor {

    private long actorId;

    private String actorGender;

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
