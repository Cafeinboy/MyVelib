package Network;

import GPSCoordinate.GPSCoordinate;

public class StandardStation extends Station {

	//Constructor
	
	public StandardStation() {
		super();
	}
	
	public StandardStation(boolean status, GPSCoordinate coordinates,
			String name) {
		super(status, coordinates, name);
	}

	public StandardStation(boolean status, GPSCoordinate coordinates) {
		super(status, coordinates);
		// TODO Auto-generated constructor stub
	}
	
	

}
