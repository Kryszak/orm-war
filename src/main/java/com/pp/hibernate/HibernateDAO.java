package com.pp.hibernate;

import com.google.common.base.Stopwatch;
import com.pp.hibernate.model.Actor;
import com.pp.hibernate.model.U2Base;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HibernateDAO {

    public long selectSingle() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            List<Actor> actressess = session.createQuery("FROM Actor WHERE actorGender = 'F'").list();
            stopwatch.stop();
            System.out.println("Actressess list size: " + actressess.size());
        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);

        System.out.println("Hibernate single select in " + elapsed + " ms.");
        return elapsed;
    }

    public long selectJoin() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            List<U2Base> rated4 = session.createQuery("FROM U2Base WHERE rating = '4'").list();
            stopwatch.stop();
            System.out.println("rated4 list size: " + rated4.size());
        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);

        System.out.println("Hibernate join select in " + elapsed + " ms.");
        return elapsed;
    }

    public long update() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("UPDATE Director SET averageRevenue = 10000 WHERE directorQuality > 4");
            int i = query.executeUpdate();
            tx.commit();
            System.out.println("Hibernate update result: " + i);
            stopwatch.stop();
        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);

        System.out.println("Hibernate update in " + elapsed + " ms.");
        return elapsed;
    }
}
