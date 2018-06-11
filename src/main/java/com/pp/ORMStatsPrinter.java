package com.pp;

import java.util.Arrays;
import java.util.List;

public class ORMStatsPrinter {

	public void print(ORMStats ormStats) {
		System.out.println("ORM name: " + ormStats.getOrmName());
		
		printAllTimes(ormStats);
		
		printAllMeans(ormStats);
		
		printAllMedians(ormStats);
	}

	private void printAllTimes(ORMStats ormStats) {
		printTimes(ormStats.getInsertTime(), "Insert");
		
		printTimes(ormStats.getSelectSingleTime(), "Select Single");
		
		printTimes(ormStats.getSelectJoinTime(), "Select Join");
		
		printTimes(ormStats.getUpdateTime(), "Update");
	}

	private void printAllMeans(ORMStats ormStats) {
		printMeanValue(ormStats.getInsertTime(), "Insert");
		
		printMeanValue(ormStats.getSelectSingleTime(), "Select Single");
		
		printMeanValue(ormStats.getSelectJoinTime(), "Select Join");
		
		printMeanValue(ormStats.getUpdateTime(), "Update");
	}

	private void printAllMedians(ORMStats ormStats) {
		printMedianValue(ormStats.getInsertTime(), "Insert");
		
		printMedianValue(ormStats.getSelectSingleTime(), "Select Single");
		
		printMedianValue(ormStats.getSelectJoinTime(), "Select Join");
		
		printMedianValue(ormStats.getUpdateTime(), "Update");
	}

	private void printTimes(List<Double> times, String measure) {
		System.out.print(measure + " times: ");
		for(double time : times) {
			System.out.print(time + ", ");
		}
		System.out.print("\n");
	}
	
	private void printMeanValue(List<Double> times, String measure) {
		System.out.print(measure + " avarage time: ");
		double sum = 0.0;
		for(double time : times) {
			sum += time;
		}
		System.out.println(sum / times.size());
	}
	
	private void printMedianValue(List<Double> times, String measure) {
		double[] numArray = times.stream()
				.mapToDouble(Double::doubleValue)
				.toArray();
		Arrays.sort(numArray);
		double median;
		if (numArray.length % 2 == 0)
		    median = ((double)numArray[numArray.length/2] + (double)numArray[numArray.length/2 - 1])/2;
		else
		    median = (double) numArray[numArray.length/2];
		System.out.println(measure + " median time: " + median);
	}
}
