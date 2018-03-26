package PlanningRide;

import java.util.ArrayList;
import java.util.Arrays;

import Bike.Bike;
import Bike.ElectricalBike;
import Bike.MechanicalBike;
import GPSCoordinate.GPSCoordinate;
import Network.*;

/**
 * This class provide a method implemented by {@link PlanningRideStrategy}, to find a fastest path, by walking at 4km/k and bicycling at 20km/h for a { @link ElectricalBike} and at 15km/h for a 
 * { @link MechanicalBike}
 * @author Kévin HIVET
 *
 */
public class FastestPath implements PlanningRideStrategy{

	public FastestPath() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * The aim of this method is to find a way to go between the location and the destination by attempting to find a fastest path, by walking at 4km/k and bicycling at 20km/h for a { @link ElectricalBike} and at 15km/h for a 
	 * { @link MechanicalBike} 
	 * @param beginingPoint 
	 * Where the user is starting from.
	 * @param finishingPoint
	 * Where the user wants to go.
	 * @param bike
	 * The kind of bike the user wants ({@link ElectricalBike}, {@link MechanicalBike})
	 * @return ArrayList
	 * Return the list of two stations, in the first place the begin station, in the second place the end station.
	 */
	public ArrayList<Station> planARide(GPSCoordinate beginingPoint, GPSCoordinate finishingPoint, Bike bike) {

		ArrayList<Station> availableStation = Network.getAvailableStations();
		
		double timeMin = GPSCoordinate.distance(availableStation.get(0).getCoordinates(),beginingPoint)/4. + GPSCoordinate.distance(availableStation.get(0).getCoordinates(),finishingPoint)/4.;
		Station stationBeg = availableStation.get(0);
		Station stationFin = availableStation.get(0);
		
		for (Station station : availableStation) {
			for (Station stationBis : availableStation) {
				if (station.numberOfKindBike(bike)>0 && stationBis.numberOfFreeSpots()>0) {
					double timeTemp = GPSCoordinate.distance(beginingPoint, station.getCoordinates())/4. + GPSCoordinate.distance(station.getCoordinates(),stationBis.getCoordinates())/bike.getSpeed() + GPSCoordinate.distance(stationBis.getCoordinates(), finishingPoint)/4.;
					if (timeTemp<timeMin) {
						timeMin = timeTemp;
						stationBeg = station;
						stationFin = stationBis;
					}
				}
			}
		}
		
		return new ArrayList<Station>(Arrays.asList(stationBeg,stationFin));
	}
	
	

}
