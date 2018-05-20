package com.pp.activejdbc.migration;

import static com.pp.TableCsvReader.ACTORS_TABLE_FILE;
import static com.pp.TableCsvReader.DIRECTORS_TABLE_FILE;
import static com.pp.TableCsvReader.MOVIES_2_ACTORS_TABLE_FILE;
import static com.pp.TableCsvReader.MOVIES_2_DIRECTORS_TABLE_FILE;
import static com.pp.TableCsvReader.MOVIES_TABLE_FILE;
import static com.pp.TableCsvReader.SEPARATOR;
import static com.pp.TableCsvReader.U2BASE_TABLE_FILE;
import static com.pp.TableCsvReader.USERS_TABLE_FILE;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import org.javalite.activejdbc.Base;

import com.google.common.base.Stopwatch;
import com.pp.TableCsvReader;

public class ActiveJDBCDataMigrationTool {
	
	private static final int BATCH_SIZE = 10000;
	
	private TableCsvReader tableCsvReader = new TableCsvReader();

	public void migrateData() throws IOException {
		Base.open("org.postgresql.Driver", "jdbc:postgresql://127.0.0.1:5432/activejdbc", "postgres", "postgres");
		
		System.out.println("Migrating data using ActiveJDBC ORM...");
		Stopwatch stopwatch = Stopwatch.createStarted();
		
		insertActors();
		insertDirectors();
        insertMovies();
        insertUsers();
        insertMovies2Actors();
        insertMovies2Directors();
        insertU2Base();
		
		stopwatch.stop();
		System.out.println("Data migrated using ActiveJDBC in " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
		
		Base.close();
	}
	
	private void insertActors() throws IOException {
		insertData(ACTORS_TABLE_FILE, "INSERT INTO actors(actorid,a_gender,a_quality) VALUES(?,?,?)", this::saveActor);
	}
	
	private void insertDirectors() throws IOException {
        insertData(DIRECTORS_TABLE_FILE, "INSERT INTO directors(directorid,d_quality,avg_revenue) VALUES(?,?,?)", this::saveDirector);
    }

    private void insertMovies() throws IOException {
        insertData(MOVIES_TABLE_FILE, "INSERT INTO movies(movieid,year,isenglish,country,runningtime) VALUES (?,?,?,?,?)", this::saveMovie);
    }

    private void insertUsers() throws IOException {
        insertData(USERS_TABLE_FILE, "INSERT INTO users(userid, age,u_gender,occupation) VALUES(?,?,?,?)", this::saveUser);
    }
	
    private void insertMovies2Actors() throws IOException {
        insertData(MOVIES_2_ACTORS_TABLE_FILE, "INSERT INTO movies2actors(movieid,actorid,cast_num) VALUES(?,?,?)", this::saveMovie2Actor);
    }

    private void insertMovies2Directors() throws IOException {
        insertData(MOVIES_2_DIRECTORS_TABLE_FILE, "INSERT INTO movies2directors(movieid,directorid,genre) VALUES(?,?,?)", this::saveMovie2Director);
    }

    private void insertU2Base() throws IOException {
        insertData(U2BASE_TABLE_FILE, "INSERT INTO u2base(userid,movieid,rating) VALUES(?,?,?)", this::saveU2Base);
    }
	
	private void insertData(String file, String insertQuery, BiConsumer<PreparedStatement, String[]> consumer) {
		try {
			PreparedStatement preparedStatement = Base.startBatch(insertQuery);
			AtomicInteger counter = new AtomicInteger();
			
            Stream<String> dataStream = tableCsvReader.streamTableFile(file);
            dataStream.forEach(s -> {
                String[] split = s.split(SEPARATOR);
                consumer.accept(preparedStatement, split);
                counter.getAndIncrement();
                if (counter.get() >= BATCH_SIZE) {
                	Base.executeBatch(preparedStatement);
                    counter.getAndSet(0);
                }
            });
            
            Base.executeBatch(preparedStatement);	
			preparedStatement.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private void saveActor(PreparedStatement preparedStatement, String[] split) {
		Base.addBatch(preparedStatement, Long.parseLong(split[0]), split[1], Integer.parseInt(split[2]));
	}
	
	private void saveDirector(PreparedStatement preparedStatement, String[] split) {
		Base.addBatch(preparedStatement, Long.parseLong(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }

    private void saveMovie(PreparedStatement preparedStatement, String[] split) {
        boolean isEnglish = split[2].equals("T");
    	Base.addBatch(preparedStatement, Long.parseLong(split[0]), Integer.parseInt(split[1]), isEnglish, split[3], Integer.parseInt(split[4]));
    }
    
    private void saveUser(PreparedStatement preparedStatement, String[] split) {
    	Base.addBatch(preparedStatement, Long.parseLong(split[0]), split[1], split[2], split[3]);
    }
    
    private void saveMovie2Actor(PreparedStatement preparedStatement, String[] split) {
    	Base.addBatch(preparedStatement, Long.parseLong(split[0]), Long.parseLong(split[1]), Integer.parseInt(split[2]));
    }

    private void saveMovie2Director(PreparedStatement preparedStatement, String[] split) {
    	Base.addBatch(preparedStatement, Long.parseLong(split[0]), Long.parseLong(split[1]), split[2]);
    }

    private void saveU2Base(PreparedStatement preparedStatement, String[] split) {
    	Base.addBatch(preparedStatement, Long.parseLong(split[0]), Long.parseLong(split[1]), split[2]);
    }
}
