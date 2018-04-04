package Network;

import GPSCoordinate.GPSCoordinate;
import User.User;

public class PlusStation extends Station {
	
	//Constructor

	public PlusStation(boolean status, GPSCoordinate coordinates) {
		super(status, coordinates);
		// TODO Auto-generated constructor stub
	}

	public PlusStation(boolean status, GPSCoordinate coordinates,
			String name) {
		super(status, coordinates, name);
	}
	
	//Methods
	
	@Override
	public void giveTimeCredit(User u) {
		u.getCard().addTimecredit(5);
	}

}
