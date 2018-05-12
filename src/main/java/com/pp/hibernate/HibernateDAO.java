package com.pp.hibernate;

import com.pp.hibernate.model.Movie2Director;
import com.pp.hibernate.model.U2Base;
import org.hibernate.Session;

import java.util.List;

public class HibernateDAO {


    public void test() {
        Session session = HibernateConnection.getSessionFactory().openSession();

        List<U2Base> actor = (List<U2Base>) session.createQuery("FROM U2Base").setMaxResults(1).list();

        System.out.println("Retrieved from DB: " + actor);

        session.close();

        HibernateConnection.shutdown();
    }
}
