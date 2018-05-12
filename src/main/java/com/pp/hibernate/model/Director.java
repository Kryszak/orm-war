package com.pp.hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "directors", indexes = {
        @Index(columnList = "d_quality", name = "d_quality"),
        @Index(columnList = "avg_revenue", name = "avg_revenue")
})
public class Director {

    @Id
    @Column(name = "directorid")
    private long directorId;

    @Column(name = "d_quality")
    private int directorQuality;

    @Column(name = "avg_revenue")
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
                ", dirrectorQuality=" + directorQuality +
                ", averageRevenue=" + averageRevenue +
                '}';
    }
}
