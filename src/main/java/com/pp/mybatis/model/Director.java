package com.pp.mybatis.model;

public class Director {

    private long directorId;

    private int directorQuality;

    private int averageRevenue;

    public Director() {
    }

    public Director(long directorId, int directorQuality, int averageRevenue) {
        this.directorId = directorId;
        this.directorQuality = directorQuality;
        this.averageRevenue = averageRevenue;
    }

    public long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(long directorId) {
        this.directorId = directorId;
    }

    public int getDirectorQuality() {
        return directorQuality;
    }

    public void setDirectorQuality(int dirrectorQuality) {
        this.directorQuality = dirrectorQuality;
    }

    public int getAverageRevenue() {
        return averageRevenue;
    }

    public void setAverageRevenue(int averageRevenue) {
        this.averageRevenue = averageRevenue;
    }

    @Override
    public String toString() {
        return "Director{" +
                "directorId=" + directorId +
                ", directorQuality=" + directorQuality +
                ", averageRevenue=" + averageRevenue +
                '}';
    }
}
