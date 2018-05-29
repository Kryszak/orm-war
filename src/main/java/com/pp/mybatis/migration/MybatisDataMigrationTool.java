package com.pp.mybatis.migration;

import com.google.common.base.Stopwatch;
import com.pp.TableCsvReader;
import com.pp.mybatis.MyBatisConnection;
import com.pp.mybatis.model.*;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static com.pp.TableCsvReader.*;

public class MybatisDataMigrationTool {

    private TableCsvReader reader = new TableCsvReader();

    private static final int BATCH_SIZE = 10000;

    public long migrateData() throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("Creating tables.");
        createTables();

        fillTables();

        stopwatch.stop();

        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("MyBatis data migration finished in " + elapsed + " ms.");
        return elapsed;
    }

    private void createTables() {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        CreateTablesMapper mapper = sqlSession.getMapper(CreateTablesMapper.class);

        mapper.createActorsTable();
        mapper.createDirectorsTable();
        mapper.createMoviesTable();
        mapper.createUsersTable();
        mapper.createMovies2ActorsTable();
        mapper.createMovies2Directors();
        mapper.createU2BaseTable();

        sqlSession.commit();
        sqlSession.close();
    }

    private void fillTables() throws IOException {
        System.out.println("Migrating actors file.");
        migrateActors();
        System.out.println("Migrating directors file.");
        migrateDirectors();
        System.out.println("Migrating movies file.");
        migrateMovies();
        System.out.println("Migrating users file.");
        migrateUsers();
        System.out.println("Migrating movies 2 actors file.");
        migrateMovies2Actors();
        System.out.println("Migrate movies 2 directors file.");
        migrateMovies2Directors();
        System.out.println("Migrate U2Base file.");
        migrateU2Base();
    }

    private void migrateActors() throws IOException {
        migrateFile(ACTORS_TABLE_FILE, this::saveActor);
    }

    private void migrateDirectors() throws IOException {
        migrateFile(DIRECTORS_TABLE_FILE, this::saveDirector);
    }

    private void migrateMovies() throws IOException {
        migrateFile(MOVIES_TABLE_FILE, this::saveMovie);
    }

    private void migrateUsers() throws IOException {
        migrateFile(USERS_TABLE_FILE, this::saveUser);
    }

    private void migrateMovies2Actors() throws IOException {
        migrateFile(MOVIES_2_ACTORS_TABLE_FILE, this::saveMovie2Actor);
    }

    private void migrateMovies2Directors() throws IOException {
        migrateFile(MOVIES_2_DIRECTORS_TABLE_FILE, this::saveMovie2Director);
    }

    private void migrateU2Base() throws IOException {
        migrateFile(U2BASE_TABLE_FILE, this::saveU2Base);
    }

    private void migrateFile(String file, BiConsumer<InsertDataMapper, String[]> consumer) throws IOException {
        Stream<String> dataStream = reader.streamTableFile(file);
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        InsertDataMapper mapper = sqlSession.getMapper(InsertDataMapper.class);
        AtomicInteger counter = new AtomicInteger();

        dataStream.forEach(s -> {
            String[] split = s.split(SEPARATOR);
            consumer.accept(mapper, split);
            counter.getAndIncrement();
            if (counter.get() >= BATCH_SIZE) {
                sqlSession.flushStatements();
                counter.getAndSet(0);
            }
        });

        sqlSession.flushStatements();

        sqlSession.commit();
        sqlSession.close();
    }

    private void saveActor(InsertDataMapper mapper, String[] split) {
        Actor actor = new Actor(Long.parseLong(split[0]), split[1], Integer.parseInt(split[2]));
        mapper.insertActor(actor);
    }

    private void saveDirector(InsertDataMapper mapper, String[] split) {
        Director director = new Director(Long.parseLong(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        mapper.insertDirector(director);
    }

    private void saveMovie(InsertDataMapper mapper, String[] split) {
        boolean isEnglish = split[2].equals("T");
        Movie movie = new Movie(Long.parseLong(split[0]), Integer.parseInt(split[1]), isEnglish, split[3], Integer.parseInt(split[4]));
        mapper.insertMovie(movie);
    }

    private void saveUser(InsertDataMapper mapper, String[] split) {
        User user = new User(Long.parseLong(split[0]), split[1], split[2], split[3]);
        mapper.insertUser(user);
    }

    private void saveMovie2Actor(InsertDataMapper mapper, String[] split) {
        Movie2Actor movie2Actor = new Movie2Actor(Long.parseLong(split[0]), Long.parseLong(split[1]), Integer.parseInt(split[2]));
        mapper.insertMovie2Actor(movie2Actor);
    }

    private void saveMovie2Director(InsertDataMapper mapper, String[] split) {
        Movie2Director movie2Director = new Movie2Director(Long.parseLong(split[0]), Long.parseLong(split[1]), split[2]);
        mapper.insertMovie2Director(movie2Director);
    }

    private void saveU2Base(InsertDataMapper mapper, String[] split) {
        U2Base u2Base = new U2Base(Long.parseLong(split[0]), Long.parseLong(split[1]), split[2]);
        mapper.insertU2Base(u2Base);
    }
}
