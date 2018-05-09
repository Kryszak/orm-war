package com.pp.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

class MyBatisConnection {

    private static SqlSessionFactory sqlSessionFactory;

    private static final String CONFIG_FILE = "mybatis-config.xml";

    static {
        try (InputStream inputStream = Resources.getResourceAsStream(CONFIG_FILE)) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}
