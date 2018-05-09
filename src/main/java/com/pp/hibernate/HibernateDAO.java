package com.pp.hibernate;

import org.hibernate.Session;

import java.util.List;

public class HibernateDAO {

    public void test() {
        Session session = HibernateConnection.getSessionFactory().openSession();

        Test test = (Test) session.createQuery("FROM Test").setMaxResults(1).list().get(0);

        System.out.println("Retrieved from DB: " + test);

        session.close();

        HibernateConnection.shutdown();
    }
}
