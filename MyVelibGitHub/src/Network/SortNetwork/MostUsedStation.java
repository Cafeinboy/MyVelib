package Network.SortNetwork;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

import Network.*;

public class MostUsedStation implements StrategySort{
	
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
			double time1 = result1.get(0) + result1.get(1);
			ArrayList<Double> result2 = s2.getAllRecord().balanceStation(this.minTime, this.maxTime, s1.getNumberOfSpots());
			double time2 = result2.get(0) + result2.get(1);
			return (int) (time1-time2);
		}
		
	}
	
	
	
	public static ArrayList<Station> sort(int minTime, int maxTime, Network net) {
		SortMethod method = new MostUsedStation().new SortMethod(minTime, maxTime);
		ArrayList<Station> network = new ArrayList<Station>(net.getStations());
		Collections.sort(network,method);
		return network;
	}

}
