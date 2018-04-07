package PlanningRide;

import java.util.ArrayList;
import java.util.Arrays;

import Bike.*;
import GPSCoordinate.GPSCoordinate;
import Network.*;

/**
 * This class provide a method implemented by {@link PlanningRideStrategy}, to try to preserve the uniformity of distribution of bikes.
 * @author K�vin HIVET
 *
 */
public class PreservationOfUniformityStrategy implements PlanningRideStrategy{
	
	public PreservationOfUniformityStrategy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * The aim of this method is to find a way to go between the location and the destination by attempting to preserve the uniformity of the distribution of bikes, from the requirements.
	 * If there is not possible to preserve the uniformity the shortest walking way is returned.
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
		
		double distancePreserveBeg = GPSCoordinate.distance(availableStation.get(0).getCoordinates(),beginingPoint);
		Station stationPreserveBeg = null;
		
		double distancePreserveFin = GPSCoordinate.distance(availableStation.get(0).getCoordinates(),finishingPoint);
		Station stationPreserveFin = null;
		
		double caracteristicDistanceBeg = GPSCoordinate.distance(stationBeg.getCoordinates(),finishingPoint);
		double caracteristicDistanceFin = GPSCoordinate.distance(stationFin.getCoordinates(),finishingPoint);
		
		for (Station station : availableStation) {
			double distancePreserveBegTemp = GPSCoordinate.distance(station.getCoordinates(),beginingPoint);
			double distancePreserveFinTemp = GPSCoordinate.distance(station.getCoordinates(),finishingPoint);
			if (distancePreserveBegTemp <= 1.05*caracteristicDistanceBeg && distancePreserveBegTemp <= distancePreserveBeg && station.numberOfKindBike(bike)>stationBeg.numberOfKindBike(bike)) {
				distancePreserveBeg = distancePreserveBegTemp;
				stationPreserveBeg = station;				
			}
			
			if (distancePreserveFinTemp <= 1.05*caracteristicDistanceFin && distancePreserveFinTemp <= distancePreserveFin && station.getNumberOfSpots()>stationBeg.numberOfFreeSpots()) {
				distancePreserveFin = distancePreserveFinTemp;
				stationPreserveFin = station;				
			}
		}
		
		if (stationPreserveBeg == null && stationPreserveFin == null) {
			return new ArrayList<Station>(Arrays.asList(stationBeg,stationFin));
		}
		else if (stationPreserveBeg == null && stationPreserveFin != null) {
			return new ArrayList<Station>(Arrays.asList(stationBeg,stationPreserveFin));
		}
		else if (stationPreserveBeg != null && stationPreserveFin == null) {
			return new ArrayList<Station>(Arrays.asList(stationPreserveBeg,stationFin));
		}
		else {
			return new ArrayList<Station>(Arrays.asList(stationPreserveBeg,stationPreserveFin));
		}

	}

}
