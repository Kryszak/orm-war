package com.pp;

import com.pp.mybatis.MyBatisDAO;
import com.pp.mybatis.migration.MybatisDataMigrationTool;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        MybatisDataMigrationTool migrationTool = new MybatisDataMigrationTool();
        MyBatisDAO hibernateDAO = new MyBatisDAO();

        migrationTool.migrateData();
        hibernateDAO.test();

    }

}
