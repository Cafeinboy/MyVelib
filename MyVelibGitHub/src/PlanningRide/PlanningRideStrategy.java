package PlanningRide;

import java.util.ArrayList;

import Bike.*;
import GPSCoordinate.GPSCoordinate;
import Network.*;

/**
 * 
 * @author Kévin HIVET
 * This class has been implemented to provide the good function for a new kind strategy to ride. Without that a strategy ride has no sense to exist.
 */
public interface PlanningRideStrategy {
	
	/**
	 * A function that can give us a ride, depending of the strategy.
	 * @param beginingPoint 
	 * Where the user is starting from.
	 * @param finishingPoint
	 * Where the user wants to go.
	 * @param bike
	 * The kind of bike the user wants ({@link ElectricalBike}, {@link MechanicalBike})
	 * @return ArrayList
	 * Return the list of two stations, in the first place the begin station, in the second place the end station.
	 */
	public ArrayList<Station> planARide(GPSCoordinate beginingPoint,GPSCoordinate finishingPoint, Bike bike);

}
