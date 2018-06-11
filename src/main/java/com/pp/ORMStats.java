package com.pp;

import java.util.ArrayList;
import java.util.List;

public class ORMStats {

    private String ormName;

    private List<Double> insertTime = new ArrayList<>();

    private List<Double> selectSingleTime = new ArrayList<>();

    private List<Double> selectJoinTime = new ArrayList<>();

    private List<Double> updateTime = new ArrayList<>();

    public ORMStats() {
    }

    public List<Double> getInsertTime() {
        return insertTime;
    }

    public void addInsertTime(double insertTime) {
        this.insertTime.add(insertTime);
    }

    public List<Double> getSelectSingleTime() {
        return selectSingleTime;
    }

    public void addSelectSingleTime(double selectSingleTime) {
        this.selectSingleTime.add(selectSingleTime);
    }

    public List<Double> getSelectJoinTime() {
        return selectJoinTime;
    }

    public void addSelectJoinTime(double selectJoinTime) {
        this.selectJoinTime.add(selectJoinTime);
    }

    public List<Double> getUpdateTime() {
        return updateTime;
    }

    public void addUpdateTime(double updateTime) {
        this.updateTime.add(updateTime);
    }

    public String getOrmName() {
        return ormName;
    }

    public void setOrmName(String ormName) {
        this.ormName = ormName;
    }

    @Override
    public String toString() {
        return "ORMStats{" +
                "ormName='" + ormName + '\'' +
                ", insertTime=" + insertTime +
                ", selectSingleTime=" + selectSingleTime +
                ", selectJoinTime=" + selectJoinTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
