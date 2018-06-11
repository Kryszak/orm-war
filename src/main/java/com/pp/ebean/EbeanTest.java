package com.pp.ebean;

import java.io.IOException;

import com.pp.ORMStats;
import com.pp.ebean.migration.EbeanDataMigrationTool;
import com.pp.ebean.performance.EbeanPerformanceBenchmark;

public class EbeanTest {

	private EbeanDataMigrationTool ebeanDataMigrationTool = new EbeanDataMigrationTool();
	
	private EbeanPerformanceBenchmark ebeanPerformanceBenchmark = new EbeanPerformanceBenchmark();
	
	public ORMStats testEbean(int count) throws IOException {	
		ORMStats stats = new ORMStats();
        stats.setOrmName("Ebean");
        
		for(int i=0; i<count; i++) {
	        testInsert(stats);

	        testSelectSingle(stats);

	        testSelectJoin(stats);

	        testUpdate(stats);

	        System.out.println("Ebean evaluation: " + stats);
		}
		
        return stats;
	}
	
    private void testInsert(ORMStats stats) throws IOException {
        long elapsed = ebeanDataMigrationTool.migrateData();
        stats.addInsertTime(elapsed);
    }
    
    private void testSelectSingle(ORMStats stats) {
        long elapsed = ebeanPerformanceBenchmark.selectOnSimpleTable(1);
        stats.addSelectSingleTime(elapsed);
    }
    
    private void testSelectJoin(ORMStats stats) {
        long elapsed = ebeanPerformanceBenchmark.selectOnJoinTable(1);
        stats.addSelectJoinTime(elapsed);
    }
	
	private void testUpdate(ORMStats stats) {
        long elapsed = ebeanPerformanceBenchmark.updateOnSimpleTable(1);
        stats.addUpdateTime(elapsed);
    }
}
