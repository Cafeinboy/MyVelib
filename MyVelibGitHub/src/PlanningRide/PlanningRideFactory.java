package PlanningRide;

import Exception.PlanningRideFactoryException;

public class PlanningRideFactory {
	
	public static PlanningRideStrategy createAStrategy(String strategy) throws PlanningRideFactoryException {
		
		if (strategy == null) {
			throw new PlanningRideFactoryException();
		}		
		else if(strategy.equalsIgnoreCase("avoidplusstation")) {
			return new AvoidPlusStationStrategy();
		} 
		else if(strategy.equalsIgnoreCase("avoid plus station")) {
			return new AvoidPlusStationStrategy();
		} 
		else if(strategy.equalsIgnoreCase("fastestpath")) {
			return new FastestPath();
		} 
		else if(strategy.equalsIgnoreCase("fastest path")) {
			return new FastestPath();
		}
		else if(strategy.equalsIgnoreCase("preferplusstation")) {
			return new PreferPlusStationStrategy();
		} 
		else if(strategy.equalsIgnoreCase("prefer plus station")) {
			return new PreferPlusStationStrategy();
		} 
		else if(strategy.equalsIgnoreCase("preservationofuniformity")) {
			return new PreservationOfUniformityStrategy();
		} 
		else if(strategy.equalsIgnoreCase("preservation of uniformity")) {
			return new PreservationOfUniformityStrategy();
		} 
		else if(strategy.equalsIgnoreCase("shortestpath")) {
			return new ShortestPath();
		} 
		else if(strategy.equalsIgnoreCase("shortest path")) {
			return new ShortestPath();
		} 
		else {
			throw new PlanningRideFactoryException();
		}
		
	}

}