package com.pp.hibernate;

import com.pp.hibernate.model.Actor;
import com.pp.hibernate.model.Movie;
import com.pp.hibernate.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateDAO {



    public void test() {
        Session session = HibernateConnection.getSessionFactory().openSession();

        List<User> actor = (List<User>) session.createQuery("FROM User").setMaxResults(1).list();

        System.out.println("Retrieved from DB: " + actor);

        session.close();

        HibernateConnection.shutdown();
    }

    public void insert() {
        Session session = HibernateConnection.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(null);

        transaction.commit();

        session.close();
    }
}
