package Network.SortNetwork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Network.*;

public class LeastOccupiedStation {

	public class SortMethod implements Comparator<Station> {
		
		private int minTime;
		private int maxTime;
		
		public SortMethod(int minTime, int maxTime) {
			super();
			this.minTime = minTime;
			this.maxTime = maxTime;
		}

		@Override
		public int compare(Station s1, Station s2) {
			ArrayList<Double> result1 = s1.getAllRecord().balanceStation(this.minTime, this.maxTime, s1.getNumberOfSpots());
			double average1 = result1.get(2);
			ArrayList<Double> result2 = s2.getAllRecord().balanceStation(this.minTime, this.maxTime, s1.getNumberOfSpots());
			double average2 = result2.get(2);
			return (int) (average2-average1);
		}
		
	}
	
	
	
	public static ArrayList<Station> sort(int minTime, int maxTime) {
		SortMethod method = new LeastOccupiedStation().new SortMethod(minTime, maxTime);
		ArrayList<Station> network = new ArrayList<Station>(Network.getStations());
		Collections.sort(network,method);
		return network;
	}

}