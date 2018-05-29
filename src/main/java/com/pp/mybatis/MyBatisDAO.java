package com.pp.mybatis;

import com.google.common.base.Stopwatch;
import com.pp.mybatis.model.Actor;
import com.pp.mybatis.model.U2BaseFull;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyBatisDAO {

    public long selectSingle() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try (SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession()) {
            MyBatisMapper mapper = sqlSession.getMapper(MyBatisMapper.class);

            List<Actor> actressess = mapper.selectActressess();
            stopwatch.stop();
            System.out.println("Actressess list size: " + actressess.size());
        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("MyBatis single select in " + elapsed + " ms.");
        return elapsed;
    }


    public long selectJoin() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try (SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession()) {
            MyBatisMapper mapper = sqlSession.getMapper(MyBatisMapper.class);

            List<U2BaseFull> rated4 = mapper.selectJoin();
            stopwatch.stop();
            System.out.println("Actressess list size: " + rated4.size());
        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("MyBatis single select in " + elapsed + " ms.");
        return elapsed;
    }

    public long update() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try (SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession()) {
            MyBatisMapper mapper = sqlSession.getMapper(MyBatisMapper.class);
            mapper.update();
            stopwatch.stop();
        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("MyBatis single select in " + elapsed + " ms.");
        return elapsed;
    }
}
