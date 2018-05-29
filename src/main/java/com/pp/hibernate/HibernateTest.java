package com.pp.hibernate;

import com.pp.ORMStats;
import com.pp.hibernate.migration.HibernateDataMigrationTool;

import java.io.IOException;

public class HibernateTest {

    private HibernateDataMigrationTool dataMigrationTool = new HibernateDataMigrationTool();

    private HibernateDAO hibernateDAO = new HibernateDAO();

    public ORMStats testHibernate() throws IOException {

        ORMStats stats = new ORMStats();

        stats.setOrmName("Hibernate");

        testInsert(stats);
        
        testSelectSingle(stats);

        testSelectJoin(stats);

        testUpdate(stats);

        System.out.println("Hibernate evaluation: " + stats);

        return stats;
    }

    private void testUpdate(ORMStats stats) {
        long elapsed = hibernateDAO.update();
        stats.setUpdateTime(elapsed);
    }

    private void testSelectJoin(ORMStats stats) {
        long elapsed = hibernateDAO.selectJoin();
        stats.setSelectJoinTime(elapsed);
    }

    private void testSelectSingle(ORMStats stats) {
        long elapsed = hibernateDAO.selectSingle();
        stats.setSelectSingleTime(elapsed);

    }

    private void testInsert(ORMStats stats) throws IOException {
        long insertTime = dataMigrationTool.migrateData();
        stats.setInsertTime(insertTime);
    }
}
