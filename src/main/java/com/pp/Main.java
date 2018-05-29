package com.pp;

import com.pp.mybatis.MyBatisTest;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

//        HibernateTest hibernateTest = new HibernateTest();

//        hibernateTest.testHibernate();

        MyBatisTest myBatisTest = new MyBatisTest();

        myBatisTest.testMyBatis();

    }

}
