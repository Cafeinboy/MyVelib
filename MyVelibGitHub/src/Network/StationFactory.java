package Network;

import Exception.StationFactoryException;
import GPSCoordinate.GPSCoordinate;

public class StationFactory {
	
public static Station createAStation(boolean status, GPSCoordinate coordinates, String name, String kindStation) throws StationFactoryException {
		
		if (kindStation == null) {
			throw new StationFactoryException();
		}		
		else if(kindStation.equalsIgnoreCase("plus")) {
			return new PlusStation(status, coordinates, name);
		} 
		else if(kindStation.equalsIgnoreCase("plus station")) {
			return new PlusStation(status, coordinates, name);
		} 
		else if(kindStation.equalsIgnoreCase("plusstation")) {
			return new PlusStation(status, coordinates, name);
		} 
		else if(kindStation.equalsIgnoreCase("standard")) {
			return new StandardStation(status, coordinates, name);
		} 
		else if(kindStation.equalsIgnoreCase("standard station")) {
			return new StandardStation(status, coordinates, name);
		} 
		else if(kindStation.equalsIgnoreCase("standardstation")) {
			return new StandardStation(status, coordinates, name);
		} 
		else {
			throw new StationFactoryException();
		}
		
	}

}
