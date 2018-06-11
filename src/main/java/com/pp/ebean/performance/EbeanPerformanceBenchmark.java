package com.pp.ebean.performance;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import com.pp.ebean.model.Actor;
import com.pp.ebean.model.Director;
import com.pp.ebean.model.U2Base;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;

public class EbeanPerformanceBenchmark {

	private static final Logger log = LoggerFactory.getLogger(EbeanServerFactory.class);
	private ServerConfig config = new ServerConfig();
	private EbeanServer ebeanServer;
	
	public EbeanPerformanceBenchmark() {
		config.setDefaultServer(true);
		config.loadFromProperties();

		ebeanServer = EbeanServerFactory.create(config);
	}

	public long selectOnSimpleTable(int count) {		
		Stopwatch stopwatch = Stopwatch.createStarted();
		
		List<Actor> actors = Ebean.find(Actor.class)
			.where().ieq("a_gender", "F")
			.findList();
		
		return stopwatch.elapsed(TimeUnit.MILLISECONDS);
	}
	
	public long selectOnJoinTable(int count) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		
		List<U2Base> u2Bases = Ebean.find(U2Base.class)
			.where().ieq("rating", "4")
			.findList();
		
		return stopwatch.elapsed(TimeUnit.MILLISECONDS);
	}
	
	public long updateOnSimpleTable(int count) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		
		Ebean.update(Director.class)
			.set("avg_revenue", 10000)
			.where().gt("d_quality", 4);
		
		return stopwatch.elapsed(TimeUnit.MILLISECONDS);
	}
}
