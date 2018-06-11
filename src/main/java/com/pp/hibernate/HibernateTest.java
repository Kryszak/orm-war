package com.pp.hibernate;

import java.io.IOException;

import com.pp.ORMStats;
import com.pp.hibernate.migration.HibernateDataMigrationTool;

public class HibernateTest {

    private HibernateDataMigrationTool dataMigrationTool = new HibernateDataMigrationTool();

    private HibernateDAO hibernateDAO = new HibernateDAO();

    public ORMStats testHibernate(int count) throws IOException {		
    	ORMStats stats = new ORMStats();
        stats.setOrmName("Hibernate");
        
		for(int i=0; i<count; i++) {
	        testInsert(stats);
	        
	        testSelectSingle(stats);

	        testSelectJoin(stats);

	        testUpdate(stats);

	        System.out.println("Hibernate evaluation: " + stats);
		}
        

        return stats;
    }

    private void testUpdate(ORMStats stats) {
        long elapsed = hibernateDAO.update();
        stats.addUpdateTime(elapsed);
    }

    private void testSelectJoin(ORMStats stats) {
        long elapsed = hibernateDAO.selectJoin();
        stats.addSelectJoinTime(elapsed);
    }

    private void testSelectSingle(ORMStats stats) {
        long elapsed = hibernateDAO.selectSingle();
        stats.addSelectSingleTime(elapsed);

    }

    private void testInsert(ORMStats stats) throws IOException {
        long insertTime = dataMigrationTool.migrateData();
        stats.addInsertTime(insertTime);
    }
}
