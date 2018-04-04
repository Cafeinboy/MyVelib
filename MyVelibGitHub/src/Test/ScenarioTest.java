package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Bike.ElectricalBike;
import Bike.MechanicalBike;
import Exception.NetworkFactoryException;
import Exception.UserFactoryException;
import GPSCoordinate.GPSCoordinate;
import Network.Network;
import Network.ParkingSlot;
import Network.PlusStation;
import Network.StandardStation;
import User.User;
import User.UserFactory;

public class ScenarioTest {

	@Test
	public void scenarioTest() {
		//Building network
		Network paris = new Network("Paris", 30);
		
		PlusStation opera = new PlusStation(true, new GPSCoordinate(5,5), "Opéra");
		for(int k = 0; k < 10; k++) {
			opera.addParkingSlot(new ParkingSlot(opera));
		}
		for(int k = 0; k < 4; k++) {
			opera.addBike(new MechanicalBike());
			opera.addBike(new ElectricalBike());

		}
		
		paris.addStation(opera);
		
		PlusStation chatelet = new PlusStation(true, new GPSCoordinate(11,17), "Châtelet");
		for(int k = 0; k < 10; k++) {
			chatelet.addParkingSlot(new ParkingSlot(chatelet));
		}
		for(int k = 0; k < 4; k++) {
			chatelet.addBike(new MechanicalBike());
			chatelet.addBike(new ElectricalBike());
		}
		paris.addStation(chatelet);
		
		PlusStation luxembourg = new PlusStation(true, new GPSCoordinate(20,11), "Luxembourg");
		for(int k = 0; k < 10; k++) {
			luxembourg.addParkingSlot(new ParkingSlot(luxembourg));
		}
		for(int k = 0; k < 4; k++) {
			luxembourg.addBike(new MechanicalBike());
			luxembourg.addBike(new ElectricalBike());
		}
		paris.addStation(luxembourg);
		
		PlusStation odeon = new PlusStation(true, new GPSCoordinate(25,5), "Odï¿½on");
		for(int k = 0; k < 10; k++) {
			odeon.addParkingSlot(new ParkingSlot(odeon));
		}
		for(int k = 0; k < 4; k++) {
			odeon.addBike(new MechanicalBike());
			odeon.addBike(new ElectricalBike());
		}
		paris.addStation(odeon);
		
		StandardStation montparnasse = new StandardStation(true, new GPSCoordinate(8,18), "Montparnasse");
		for(int k = 0; k < 10; k++) {
			montparnasse.addParkingSlot(new ParkingSlot(montparnasse));
		}
		for(int k = 0; k < 4; k++) {
			montparnasse.addBike(new MechanicalBike());
			montparnasse.addBike(new ElectricalBike());
		}
		paris.addStation(montparnasse);
		
		StandardStation invalides = new StandardStation(true, new GPSCoordinate(10,15), "Invalides");
		for(int k = 0; k < 10; k++) {
			invalides.addParkingSlot(new ParkingSlot(invalides));
		}
		for(int k = 0; k < 4; k++) {
			invalides.addBike(new MechanicalBike());
			invalides.addBike(new ElectricalBike());
		}
		paris.addStation(invalides);
		
		StandardStation defense = new StandardStation(true, new GPSCoordinate(24,4), "Dï¿½fense");
		for(int k = 0; k < 10; k++) {
			defense.addParkingSlot(new ParkingSlot(defense));
		}
		for(int k = 0; k < 4; k++) {
			defense.addBike(new MechanicalBike());
			defense.addBike(new ElectricalBike());
		}
		paris.addStation(defense);
		
		//Creating users
		
		UserFactory uf = new UserFactory();
		
		try {
			User pierre = uf.createUser("Pierre", "NoCard", "Paris");
		} catch (UserFactoryException | NetworkFactoryException e) {}
		
		try {
			User paul = uf.createUser("Paul", "Vlibre", "Paris");
		} catch (UserFactoryException | NetworkFactoryException e) {}
		
		try {
			User jacques = uf.createUser("Jacques", "Vmax", "Paris");
		} catch (UserFactoryException | NetworkFactoryException e) {}
	}

}
