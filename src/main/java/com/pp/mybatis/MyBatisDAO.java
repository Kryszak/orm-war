package com.pp.mybatis;

import org.apache.ibatis.session.SqlSession;

public class MyBatisDAO {

    public void test() {

        // sesja + utworzenie instancji mappera
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        MyBatisMapper mapper = sqlSession.getMapper(MyBatisMapper.class);

        // wywołanie
        Test fromDB = mapper.testSelect();
        System.out.println("Got response from DB: " + fromDB);

        // commit i zamknięcie sesji
        sqlSession.commit();
        sqlSession.close();
    }

}
