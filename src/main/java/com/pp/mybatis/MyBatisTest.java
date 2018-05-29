package com.pp.mybatis;

import com.pp.ORMStats;
import com.pp.mybatis.migration.MybatisDataMigrationTool;

import java.io.IOException;

public class MyBatisTest {

    private MybatisDataMigrationTool dataMigrationTool = new MybatisDataMigrationTool();

    private MyBatisDAO myBatisDAO = new MyBatisDAO();

    public ORMStats testMyBatis() throws IOException {
        ORMStats stats = new ORMStats();

        stats.setOrmName("MyBatis");

        testInsert(stats);

        testSelectSingle(stats);

        testSelectJoin(stats);

        testUpdate(stats);

        System.out.println("MyBatis evaluation: " + stats);

        return stats;
    }

    private void testUpdate(ORMStats stats) {
        long elapsed = myBatisDAO.update();
        stats.setUpdateTime(elapsed);
    }

    private void testSelectJoin(ORMStats stats) {
        long elapsed = myBatisDAO.selectJoin();
        stats.setSelectJoinTime(elapsed);
    }

    private void testSelectSingle(ORMStats stats) {
        long elapsed = myBatisDAO.selectSingle();
        stats.setSelectSingleTime(elapsed);
    }

    private void testInsert(ORMStats stats) throws IOException {
        long elapsed = dataMigrationTool.migrateData();
        stats.setInsertTime(elapsed);
    }
}
