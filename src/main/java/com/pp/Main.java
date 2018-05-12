package com.pp;

import com.pp.hibernate.HibernateDAO;
import com.pp.hibernate.migration.HibernateDataMigrationTool;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        HibernateDataMigrationTool migrationTool = new HibernateDataMigrationTool();
        HibernateDAO hibernateDAO = new HibernateDAO();

        migrationTool.migrateData();
        hibernateDAO.test();

    }

}
