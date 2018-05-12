package com.pp.mybatis;

import com.pp.mybatis.model.Test;
import org.apache.ibatis.session.SqlSession;

public class MyBatisDAO {

    public void test() {

        // sesja + utworzenie instancji mappera
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        MyBatisMapper mapper = sqlSession.getMapper(MyBatisMapper.class);

        Test test = new Test("inserted from java into mybatis");

        // wywołanie
        Test fromDB = mapper.testSelect();
        mapper.createTable();
        mapper.insertTest(test);
        
        System.out.println("Got response from DB: " + fromDB);

        // commit i zamknięcie sesji
        sqlSession.commit();
        sqlSession.close();
    }

}
