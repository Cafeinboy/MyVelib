package Ride;

import Bike.Bike;
import Network.Network;

public interface RideStrategyInterface {
	
	/**
	 * This method computes the beginning and finishing stations of
	 * the ride, given the type of bike the user asks for, and the
	 * strategy
	 * However you have to precise before the beginning station and the ending station
	 * It sets the beginning and finishing station of the ride accordingly
	 * @param bike
	 */
	
	public void haveARide(Bike bike);

}
