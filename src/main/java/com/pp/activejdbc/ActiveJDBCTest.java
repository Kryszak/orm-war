package com.pp.activejdbc;

import java.io.IOException;

import org.javalite.activejdbc.Base;

import com.pp.ORMStats;
import com.pp.activejdbc.migration.ActiveJDBCDataMigrationTool;
import com.pp.activejdbc.performance.ActiveJDBCPerformanceBenchmark;

public class ActiveJDBCTest {

	private ActiveJDBCDataMigrationTool activeJDBCDataMigrationTool = new ActiveJDBCDataMigrationTool();
	
	private ActiveJDBCPerformanceBenchmark activeJDBCPerformanceBenchmark = new ActiveJDBCPerformanceBenchmark();
	
	public ORMStats testActiveJDBC(int count) throws IOException {
		ORMStats stats = new ORMStats();
		stats.setOrmName("ActiveJDBC");
		
		for(int i=0; i<count; i++) {	        
	        testInsert(stats);

	        testSelectSingle(stats);

	        testSelectJoin(stats);

	        testUpdate(stats);
	        
	        Base.close();

	        System.out.println("ActiveJDBC evaluation: " + stats);
		}		

        return stats;
	}
	
    private void testInsert(ORMStats stats) throws IOException {
        long elapsed = activeJDBCDataMigrationTool.migrateData();
        stats.addInsertTime(elapsed);
    }
    
    private void testSelectSingle(ORMStats stats) {
        long elapsed = activeJDBCPerformanceBenchmark.selectOnSimpleTable(1);
        stats.addSelectSingleTime(elapsed);
    }
    
    private void testSelectJoin(ORMStats stats) {
        long elapsed = activeJDBCPerformanceBenchmark.selectOnJoinTable(1);
        stats.addSelectJoinTime(elapsed);
    }
	
	private void testUpdate(ORMStats stats) {
        long elapsed = activeJDBCPerformanceBenchmark.updateOnSimpleTable(1);
        stats.addUpdateTime(elapsed);
    }
}
