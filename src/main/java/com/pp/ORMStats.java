package com.pp;

public class ORMStats {

    private String ormName;

    private double insertTime;

    private double selectSingleTime;

    private double selectJoinTime;

    private double updateTime;

    public ORMStats() {
    }

    public ORMStats(String ormName, double insertTime, double selectSingleTime, double selectJoinTime, double updateTime) {
        this.ormName = ormName;
        this.insertTime = insertTime;
        this.selectSingleTime = selectSingleTime;
        this.selectJoinTime = selectJoinTime;
        this.updateTime = updateTime;
    }

    public double getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(double insertTime) {
        this.insertTime = insertTime;
    }

    public double getSelectSingleTime() {
        return selectSingleTime;
    }

    public void setSelectSingleTime(double selectSingleTime) {
        this.selectSingleTime = selectSingleTime;
    }

    public double getSelectJoinTime() {
        return selectJoinTime;
    }

    public void setSelectJoinTime(double selectJoinTime) {
        this.selectJoinTime = selectJoinTime;
    }

    public double getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(double updateTime) {
        this.updateTime = updateTime;
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
