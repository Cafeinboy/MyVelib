package Bike;

import Exception.BikeFactoryException;

public abstract class BikeFactory {
	
	public static Bike createABike(String kindBike) throws BikeFactoryException {
		
		if (kindBike == null) {
			throw new BikeFactoryException();
		}		
		else if(kindBike.equalsIgnoreCase("mechanical")) {
			return new MechanicalBike();
		} 
		else if(kindBike.equalsIgnoreCase("electrical")) {
			return new ElectricalBike();
		} 
		else {
			throw new BikeFactoryException();
		}
		
	}

}
