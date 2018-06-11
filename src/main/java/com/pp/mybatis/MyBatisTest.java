package com.pp.mybatis;

import java.io.IOException;

import com.pp.ORMStats;
import com.pp.mybatis.migration.MybatisDataMigrationTool;

public class MyBatisTest {

    private MybatisDataMigrationTool dataMigrationTool = new MybatisDataMigrationTool();

    private MyBatisDAO myBatisDAO = new MyBatisDAO();

    public ORMStats testMyBatis(int count) throws IOException {
    	ORMStats stats = new ORMStats();
        stats.setOrmName("MyBatis");
        
		for(int i=0; i<count; i++) {
	        testInsert(stats);

	        testSelectSingle(stats);

	        testSelectJoin(stats);

	        testUpdate(stats);

	        System.out.println("MyBatis evaluation: " + stats);
		}
        

        return stats;
    }

    private void testUpdate(ORMStats stats) {
        long elapsed = myBatisDAO.update();
        stats.addUpdateTime(elapsed);
    }

    private void testSelectJoin(ORMStats stats) {
        long elapsed = myBatisDAO.selectJoin();
        stats.addSelectJoinTime(elapsed);
    }

    private void testSelectSingle(ORMStats stats) {
        long elapsed = myBatisDAO.selectSingle();
        stats.addSelectSingleTime(elapsed);
    }

    private void testInsert(ORMStats stats) throws IOException {
        long elapsed = dataMigrationTool.migrateData();
        stats.addInsertTime(elapsed);
    }
}
