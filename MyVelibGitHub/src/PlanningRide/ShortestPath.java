package PlanningRide;

import java.util.ArrayList;
import java.util.Arrays;

import Bike.*;
import GPSCoordinate.GPSCoordinate;
import Network.*;

/**
 * This class provide a method implemented by {@link PlanningRideStrategy}, to find a ShortestPath
 * @author Kévin HIVET
 *
 */
public class ShortestPath implements PlanningRideStrategy{

	public ShortestPath() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * The aim of this method is to find the shortest way to go between the location and the destination by taking at least a station, 
	 * else the shortest way between two GPSCoordinate is a direct line between both.
	 * @param beginingPoint 
	 * Where the user is starting from.
	 * @param finishingPoint
	 * Where the user wants to go.
	 * @param bike
	 * The kind of bike the user wants ({@link ElectricalBike}, {@link MechanicalBike})
	 * @return ArrayList
	 * Return the list of two stations, in the first place the begin station, in the second place the end station.
	 */
	@Override
	public ArrayList<Station> planARide(GPSCoordinate beginingPoint, GPSCoordinate finishingPoint, Bike bike) {
		
		ArrayList<Station> availableStation = Network.getAvailableStations();
		
		double distanceMin = GPSCoordinate.distance(availableStation.get(0).getCoordinates(),beginingPoint) + GPSCoordinate.distance(availableStation.get(1).getCoordinates(),finishingPoint);
		Station stationBeg = availableStation.get(0);
		Station stationFin = availableStation.get(1);
		
		for (Station station : availableStation) {
			for (Station stationBis : availableStation) {
				if (station.numberOfKindBike(bike)>0 && stationBis.numberOfFreeSpots()>0 && !station.equals(stationBis)) {
					double distanceTemp = GPSCoordinate.distance(beginingPoint, station.getCoordinates()) + GPSCoordinate.distance(station.getCoordinates(),stationBis.getCoordinates()) + GPSCoordinate.distance(stationBis.getCoordinates(), finishingPoint);
					if (distanceTemp<distanceMin) {
						distanceMin = distanceTemp;
						stationBeg = station;
						stationFin = stationBis;
					}
				}
			}
		}
		
		return new ArrayList<Station>(Arrays.asList(stationBeg, stationFin));
	}
	
	

}
