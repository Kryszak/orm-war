package com.pp;

import java.io.IOException;

import com.pp.activejdbc.ActiveJDBCTest;
import com.pp.ebean.EbeanTest;
import com.pp.hibernate.HibernateTest;
import com.pp.mybatis.MyBatisTest;

public class Main {

public static void main(String[] args) throws IOException {
	
//        HibernateTest hibernateTest = new HibernateTest();
//        ORMStats hibernateStats = hibernateTest.testHibernate(30);
//        
//        MyBatisTest myBatisTest = new MyBatisTest();
//        ORMStats myBatisStats = myBatisTest.testMyBatis(30);
//        
//        EbeanTest ebeanTest = new EbeanTest();
//        ORMStats ebeanStats = ebeanTest.testEbean(30);       

        ActiveJDBCTest activeJDBCTest = new ActiveJDBCTest();
        ORMStats aciveJDBCStats = activeJDBCTest.testActiveJDBC(2);
     
        ORMStatsPrinter ormStatsPrinter = new ORMStatsPrinter();
        
//        ormStatsPrinter.print(hibernateStats);
//        
//        ormStatsPrinter.print(myBatisStats);
//        
//        ormStatsPrinter.print(ebeanStats);
        
        ormStatsPrinter.print(aciveJDBCStats);
    }
}
