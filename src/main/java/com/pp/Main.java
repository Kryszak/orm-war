package com.pp;

import com.pp.hibernate.HibernateDAO;
import com.pp.mybatis.MyBatisDAO;

public class Main {

    public static void main(String[] args) {

        MyBatisDAO myBatisDAO = new MyBatisDAO();
        HibernateDAO hibernateDAO = new HibernateDAO();

        System.out.println("MyBatis test call:");
        myBatisDAO.test();

        System.out.println("Hibernate test call:");
        hibernateDAO.test();

    }
    
}
