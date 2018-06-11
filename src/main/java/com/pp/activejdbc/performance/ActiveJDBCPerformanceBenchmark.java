package com.pp.activejdbc.performance;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.javalite.activejdbc.Base;

import com.google.common.base.Stopwatch;
import com.pp.activejdbc.model.Actor;
import com.pp.activejdbc.model.Director;
import com.pp.activejdbc.model.Movie;
import com.pp.activejdbc.model.U2Base;
import com.pp.activejdbc.model.User;

public class ActiveJDBCPerformanceBenchmark {

	public long selectOnSimpleTable(int count) {	
		connect();
		
		Stopwatch stopwatch = Stopwatch.createStarted();
		
		List<Actor> actors = Actor.where("a_gender = 'F'");
		
		long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
		
		return time;
	}
	
	public long selectOnJoinTable(int count) {
		connect();
		
		Stopwatch stopwatch = Stopwatch.createStarted();
		
		List<U2Base> u2Bases = U2Base.where("rating = '4'").include(User.class, Movie.class);
		
		long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
		System.out.println("SIZE " + u2Bases.size());
		
		return time;
	}
	
	public long updateOnSimpleTable(int count) {	
		connect();
		
		Stopwatch stopwatch = Stopwatch.createStarted();
		
		Director.update("avg_revenue = ?", "d_quality > ?", 10000, 4);
		Base.close();
		
		return stopwatch.elapsed(TimeUnit.MILLISECONDS);
	}
	
	@Override
	protected void finalize() {
		Base.close();
	}
	
	private void connect() {
		if (!Base.hasConnection()) {
			Base.open("org.postgresql.Driver", "jdbc:postgresql://127.0.0.1:5432/activejdbc", "postgres", "postgres");
		}
	}
}
