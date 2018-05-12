package com.pp.hibernate;

import com.pp.hibernate.model.Test;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateDAO {

    public void test() {
        Session session = HibernateConnection.getSessionFactory().openSession();

        insert();

        Test test = (Test) session.createQuery("FROM Test").setMaxResults(1).list().get(0);

        System.out.println("Retrieved from DB: " + test);

        session.close();

        HibernateConnection.shutdown();
    }

    public void insert() {
        Session session = HibernateConnection.getSessionFactory().openSession();

        Test test = new Test("hibernate from code message");

        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(test);

        transaction.commit();

        session.close();
    }
}
