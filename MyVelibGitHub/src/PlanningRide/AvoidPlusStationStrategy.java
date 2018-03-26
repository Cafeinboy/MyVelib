package PlanningRide;

import java.util.ArrayList;
import java.util.Arrays;

import Bike.*;
import GPSCoordinate.*;
import Network.*;

/**
 * 
 * This class provide a method implemented by {@link PlanningRideStrategy}, to avoid station.
 * @author Kevin HIVET
 *
 */
public class AvoidPlusStationStrategy implements PlanningRideStrategy{
	
	public AvoidPlusStationStrategy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * The aim of this method is to find a way to go between the location and the destination by avoiding plus station as destination.
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
		double distanceBeg = GPSCoordinate.distance(availableStation.get(0).getCoordinates(),beginingPoint);
		Station stationBeg = availableStation.get(0);
				
		for (Station station : availableStation) {
			double distanceTempBeg = GPSCoordinate.distance(station.getCoordinates(),beginingPoint);
			if (distanceTempBeg<distanceBeg && station.numberOfKindBike(bike)>0) {
				distanceBeg = distanceTempBeg;
				stationBeg = station;
			}			
		}
		
		ArrayList<Station> listNonPlus = Network.getNonPlusStations(Network.getAvailableStations());
		double distanceFin = GPSCoordinate.distance(listNonPlus.get(0).getCoordinates(),finishingPoint);
		Station stationFin = listNonPlus.get(0);		
		
		for (Station station : listNonPlus) {
			double distanceTempFin = GPSCoordinate.distance(station.getCoordinates(),finishingPoint);
			if (distanceTempFin<distanceFin && station.numberOfFreeSpots()>0) {
				distanceFin = distanceTempFin;
				stationFin = station;
			}
		}
		
		return new ArrayList<Station>(Arrays.asList(stationBeg,stationFin));
		
	}

}
