package com.pp.hibernate.migration;

import com.google.common.base.Stopwatch;
import com.pp.TableContentRemover;
import com.pp.TableCsvReader;
import com.pp.hibernate.HibernateConnection;
import com.pp.hibernate.model.Actor;
import com.pp.hibernate.model.Director;
import com.pp.hibernate.model.Movie;
import com.pp.hibernate.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static com.pp.TableCsvReader.*;

public class HibernateDataMigrationTool {

    private TableCsvReader reader = new TableCsvReader();

    private static final int BATCH_SIZE = 10000;

    public long migrateData() throws IOException {
    	TableContentRemover tableContentRemover = new TableContentRemover("jdbc:postgresql://127.0.0.1:5432/hibernate", "postgres", "postgres");
		tableContentRemover.flush();
		
        System.out.println("Migrating data using Hibernate ORM...");
        Stopwatch stopwatch = Stopwatch.createStarted();

        System.out.println("Actor file migration");
        insertActors();
        System.out.println("Director file migration");
        insertDirectors();
        System.out.println("Movie file migration");
        insertMovies();
        System.out.println("User file migration");
        insertUsers();
        System.out.println("Movies2Actors file migration");
        insertMovies2Actors();
        System.out.println("Movies2Directors file migration");
        insertMovies2Directors();
        System.out.println("U2Base file migration");
        insertU2Base();

        stopwatch.stop();

        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("Data migrated using Hibernate in " + elapsed + " ms");
        return elapsed;
    }

    private void insertActors() throws IOException {
        insertData(ACTORS_TABLE_FILE, this::saveActor);
    }

    private void insertDirectors() throws IOException {
        insertData(DIRECTORS_TABLE_FILE, this::saveDirector);
    }

    private void insertMovies() throws IOException {
        insertData(MOVIES_TABLE_FILE, this::saveMovie);
    }

    private void insertUsers() throws IOException {
        insertData(USERS_TABLE_FILE, this::saveUser);
    }

    private void insertMovies2Actors() throws IOException {
        insertData(MOVIES_2_ACTORS_TABLE_FILE, this::saveMovie2Actor);
    }

    private void insertMovies2Directors() throws IOException {
        insertData(MOVIES_2_DIRECTORS_TABLE_FILE, this::saveMovie2Director);
    }

    private void insertU2Base() throws IOException {
        insertData(U2BASE_TABLE_FILE, this::saveU2Base);
    }

    private void insertData(String file, BiConsumer<Session, String[]> consumer) throws IOException {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            AtomicInteger counter = new AtomicInteger();
            Stream<String> dataStream = reader.streamTableFile(file);
            dataStream.forEach(s -> {
                String[] split = s.split(SEPARATOR);
                consumer.accept(session, split);
                counter.getAndIncrement();
                if (counter.get() >= BATCH_SIZE) {
                    flushBatch(session);
                    counter.getAndSet(0);
                }
            });
            // ostatnia niepełna paczka
            flushBatch(session);
            tx.commit();
        }

    }

    private void saveActor(Session session, String[] split) {
        Actor actor = new Actor(Long.parseLong(split[0]), split[1], Integer.parseInt(split[2]));
        session.save(actor);
    }

    private void saveDirector(Session session, String[] split) {
        Director director = new Director(Long.parseLong(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        session.save(director);
    }

    private void saveMovie(Session session, String[] split) {
        boolean isEnglish = split[2].equals("T");
        Movie movie = new Movie(Long.parseLong(split[0]),
                Integer.parseInt(split[1]), isEnglish, split[3], Integer.parseInt(split[4]));
        session.save(movie);
    }

    private void saveUser(Session session, String[] split) {
        User user = new User(Long.parseLong(split[0]), split[1], split[2], split[3]);
        session.save(user);
    }

    private void saveMovie2Actor(Session session, String[] split) {
        NativeQuery insert = session.createNativeQuery(
                "INSERT INTO movies2actors(movieid,actorid,cast_num) VALUES (?, ?, ?)");
        insert.setParameter(1, Long.parseLong(split[0]));
        insert.setParameter(2, Long.parseLong(split[1]));
        insert.setParameter(3, Integer.parseInt(split[2]));
        insert.executeUpdate();
    }

    private void saveMovie2Director(Session session, String[] split) {
        NativeQuery insert = session.createNativeQuery(
                "INSERT INTO movies2directors(movieid,directorid,genre) VALUES (?,?,?)");
        insert.setParameter(1, Long.parseLong(split[0]));
        insert.setParameter(2, Long.parseLong(split[1]));
        insert.setParameter(3, split[2]);
        insert.executeUpdate();
    }

    private void saveU2Base(Session session, String[] split) {
        NativeQuery insert = session.createNativeQuery(
                "INSERT INTO u2base(userid,movieid,rating) VALUES (?,?,?)");
        insert.setParameter(1, Long.parseLong(split[0]));
        insert.setParameter(2, Long.parseLong(split[1]));
        insert.setParameter(3, split[2]);
        insert.executeUpdate();
    }

    private void flushBatch(Session session) {
        session.flush();
        session.clear();
    }
}
