package com.pp;

import com.pp.mybatis.MyBatisDAO;
import com.pp.mybatis.migration.MybatisDataMigrationTool;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        MybatisDataMigrationTool migrationTool = new MybatisDataMigrationTool();
        MyBatisDAO myBatisDAO = new MyBatisDAO();

        migrationTool.migrateData();
        myBatisDAO.test();

    }

}
