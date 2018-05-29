package com.pp.ebean.migration;

import static com.pp.TableCsvReader.ACTORS_TABLE_FILE;
import static com.pp.TableCsvReader.DIRECTORS_TABLE_FILE;
import static com.pp.TableCsvReader.MOVIES_2_ACTORS_TABLE_FILE;
import static com.pp.TableCsvReader.MOVIES_2_DIRECTORS_TABLE_FILE;
import static com.pp.TableCsvReader.MOVIES_TABLE_FILE;
import static com.pp.TableCsvReader.SEPARATOR;
import static com.pp.TableCsvReader.U2BASE_TABLE_FILE;
import static com.pp.TableCsvReader.USERS_TABLE_FILE;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import com.pp.TableContentRemover;
import com.pp.TableCsvReader;
import com.pp.ebean.model.Actor;
import com.pp.ebean.model.Director;
import com.pp.ebean.model.Movie;
import com.pp.ebean.model.User;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.SqlUpdate;
import io.ebean.Transaction;
import io.ebean.config.ServerConfig;

public class EbeanDataMigrationTool {

    private static final int BATCH_SIZE = 10000;
    
    private static final Logger log = LoggerFactory.getLogger(EbeanServerFactory.class);
    private ServerConfig config = new ServerConfig(); 
	private EbeanServer ebeanServer;
	
	private TableCsvReader tableCsvReader = new TableCsvReader();
	
	public void migrateData() throws IOException {
	    connectToEbeanServer();	    
	    
		System.out.println("Migrating data using Ebean ORM...");
        Stopwatch stopwatch = Stopwatch.createStarted();
        
        insertDataToTables();

        stopwatch.stop();
        System.out.println("Data migrated using Ebean in " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
	}
	
	public void migrateDataMultipleTimes(int count) throws IOException {
		connectToEbeanServer();
		
		TableContentRemover tableContentRemover = new TableContentRemover("jdbc:postgresql://127.0.0.1:5432/ebean", "postgres", "postgres");
		tableContentRemover.flush();
		
		Stopwatch stopwatch = Stopwatch.createUnstarted();
		long time = 0L;
		for(int i=0; i<count; i++) {
			stopwatch.start();
			insertDataToTables();
			stopwatch.stop();
			
			System.out.println("Data migrated using Ebean in " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
			
			tableContentRemover.flush();
			time += stopwatch.elapsed(TimeUnit.MILLISECONDS);
			stopwatch.reset();
		}
		
		System.out.println("Data migrated " + count + " times using Ebean in " + time + " ms");
		System.out.println("Avarage time is " + time/count + " ms");
	}
	
	private void connectToEbeanServer() {
		config.setDefaultServer(true);
	    config.loadFromProperties();
	    
	    ebeanServer = EbeanServerFactory.create(config);
	}
	
	private void insertDataToTables() throws IOException {
		insertActors();
        insertDirectors();
        insertMovies();
        insertUsers();
        insertMovies2Actors();
        insertMovies2Directors();
        insertU2Base();
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
    
	private void insertData(String file, BiConsumer<EbeanServer, String[]> consumer) {
		Transaction transaction = Ebean.beginTransaction();
		try {
			transaction.setBatchMode(true);
			transaction.setBatchSize(BATCH_SIZE);
			transaction.setBatchGetGeneratedKeys(false);
			
            Stream<String> dataStream = tableCsvReader.streamTableFile(file);
            dataStream.forEach(s -> {
                String[] split = s.split(SEPARATOR);
                consumer.accept(ebeanServer, split);
            });
			
			transaction.commit();
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			transaction.end();
		}
	}
	
	private void saveActor(EbeanServer ebeanServer, String[] split) {
		Actor actor = new Actor(Long.parseLong(split[0]), split[1], Integer.parseInt(split[2]));
		ebeanServer.save(actor);
	}
	
	private void saveDirector(EbeanServer ebeanServer, String[] split) {
        Director director = new Director(Long.parseLong(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        ebeanServer.save(director);
    }

    private void saveMovie(EbeanServer ebeanServer, String[] split) {
        boolean isEnglish = split[2].equals("T");
        Movie movie = new Movie(Long.parseLong(split[0]),
                Integer.parseInt(split[1]), isEnglish, split[3], Integer.parseInt(split[4]));
        ebeanServer.save(movie);
    }
    
    private void saveUser(EbeanServer ebeanServer, String[] split) {
        User user = new User(Long.parseLong(split[0]), split[1], split[2], split[3]);
        ebeanServer.save(user);
    }
    
    private void saveMovie2Actor(EbeanServer ebeanServer, String[] split) {
        String insert = "INSERT INTO movies2actors(movieid,actorid,cast_num) VALUES (:movieid, :actorid, :cast_num)";
        SqlUpdate sqlInsert = ebeanServer.createSqlUpdate(insert)
        		.setParameter("movieid", Long.parseLong(split[0]))
        		.setParameter("actorid", Long.parseLong(split[1]))
        		.setParameter("cast_num", Integer.parseInt(split[2]));
        sqlInsert.execute();
    }

    private void saveMovie2Director(EbeanServer ebeanServer, String[] split) {
    	String insert = "INSERT INTO movies2directors(movieid,directorid,genre) VALUES (:movieid, :directorid, :genre)";
        SqlUpdate sqlInsert = ebeanServer.createSqlUpdate(insert)
        		.setParameter("movieid", Long.parseLong(split[0]))
        		.setParameter("directorid", Long.parseLong(split[1]))
        		.setParameter("genre", split[2]);
        sqlInsert.execute();
    }

    private void saveU2Base(EbeanServer ebeanServer, String[] split) {
    	String insert = "INSERT INTO u2base(userid,movieid,rating) VALUES (:userid, :movieid, :rating)";
        SqlUpdate sqlInsert = ebeanServer.createSqlUpdate(insert)
        		.setParameter("userid", Long.parseLong(split[0]))
        		.setParameter("movieid", Long.parseLong(split[1]))
        		.setParameter("rating", split[2]);
        sqlInsert.execute();
    }
}
