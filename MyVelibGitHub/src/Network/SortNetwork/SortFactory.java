package Network.SortNetwork;

import java.util.ArrayList;

import Exception.SortFactoryException;
import Network.Network;
import Network.Station;

public abstract class SortFactory {
	
	public static ArrayList<Station> sortAList(String kindSort, int minTime, int maxTime, Network net) throws SortFactoryException {
		
		if (kindSort == null) {
			throw new SortFactoryException();
		}		
		else if(kindSort.equalsIgnoreCase("most used station")) {
			return MostUsedStation.sort(minTime, maxTime, net);
		} 
		else if(kindSort.equalsIgnoreCase("mostusedstation")) {
			return MostUsedStation.sort(minTime, maxTime, net);
		} 
		else if(kindSort.equalsIgnoreCase("most used")) {
			return MostUsedStation.sort(minTime, maxTime, net);
		} 
		else if(kindSort.equalsIgnoreCase("mostused")) {
			return MostUsedStation.sort(minTime, maxTime, net);
		} 
		else if(kindSort.equalsIgnoreCase("leastoccupiedstation")) {
			return LeastOccupiedStation.sort(minTime, maxTime, net);
		} 
		else if(kindSort.equalsIgnoreCase("least occupied station")) {
			return LeastOccupiedStation.sort(minTime, maxTime, net);
		} 
		else if(kindSort.equalsIgnoreCase("least occupied")) {
			return LeastOccupiedStation.sort(minTime, maxTime, net);
		} 
		else if(kindSort.equalsIgnoreCase("leastoccupied")) {
			return LeastOccupiedStation.sort(minTime, maxTime, net);
		} 
		else {
			throw new SortFactoryException();
		}
		
	}

}