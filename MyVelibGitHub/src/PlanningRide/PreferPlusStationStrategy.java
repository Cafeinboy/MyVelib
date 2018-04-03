package PlanningRide;

import java.util.ArrayList;

import java.util.Arrays;

import Bike.*;
import GPSCoordinate.GPSCoordinate;
import Network.*;


/**
 * 
 * This class provide a method implemented by {@link PlanningRideStrategy}, to try to search a plus station not far away than the destination station.
 * @author Kevin HIVET
 *
 */
public class PreferPlusStationStrategy implements PlanningRideStrategy{
	
	public PreferPlusStationStrategy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * The aim of this method is to find a way to go between the location and the destination by attempting to have a plus station as destination. 
	 * If there is not possible to find a plus station as destination the shortest walking way is returned. 
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
	public ArrayList<Station> planARide(GPSCoordinate beginingPoint, GPSCoordinate finishingPoint, Bike bike, Network net) {
		ArrayList<Station> availableStation = net.getAvailableStations();
		double distanceBeg = GPSCoordinate.distance(availableStation.get(0).getCoordinates(),beginingPoint);
		Station stationBeg = availableStation.get(0);
		
		double distanceFin = GPSCoordinate.distance(availableStation.get(0).getCoordinates(),finishingPoint);
		Station stationFin = availableStation.get(0);
				
		for (Station station : availableStation) {
			double distanceTempBeg = GPSCoordinate.distance(station.getCoordinates(),beginingPoint);
			if (distanceTempBeg<distanceBeg && station.numberOfKindBike(bike)>0) {
				distanceBeg = distanceTempBeg;
				stationBeg = station;
			}		
			
			double distanceTempFin = GPSCoordinate.distance(station.getCoordinates(),finishingPoint);
			if (distanceTempFin<distanceFin && station.numberOfFreeSpots()>0) {
				distanceFin = distanceTempFin;
				stationFin = station;
			}
		}
		
		if (stationFin instanceof PlusStation) {
			return new ArrayList<Station>(Arrays.asList(stationBeg,stationFin));
		}
		else {
			ArrayList<Station> listPlus = Network.getPlusStations(availableStation);
			double distancePlus = GPSCoordinate.distance(listPlus.get(0).getCoordinates(),finishingPoint);
			double caracteristicDistance = GPSCoordinate.distance(stationFin.getCoordinates(),finishingPoint);
			for (Station station : listPlus) {
				double distanceTempPlus = GPSCoordinate.distance(station.getCoordinates(),finishingPoint);
				if (distanceTempPlus <= distancePlus && distanceTempPlus <= 1.1*caracteristicDistance && station.numberOfFreeSpots()>0) {
					distancePlus = distanceTempPlus ;
					stationFin = station;
				}
			}
		}
		return new ArrayList<Station>(Arrays.asList(stationBeg,stationFin));
	}

}
