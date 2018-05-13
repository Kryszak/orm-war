package com.pp.mybatis;

import com.pp.mybatis.model.Actor;
import org.apache.ibatis.session.SqlSession;

public class MyBatisDAO {

    public void test() {

        // sesja + utworzenie instancji mappera
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        MyBatisMapper mapper = sqlSession.getMapper(MyBatisMapper.class);

        // wywołanie
        Actor actor = mapper.testActorSelect();
        System.out.println("Got response from DB: " + actor);

        // commit i zamknięcie sesji
        sqlSession.close();
    }

}
