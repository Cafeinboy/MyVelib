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
import PlanningRide.PreferPlusStationStrategy;
import User.User;
import User.UserFactory;

public class KindBikeTest {

	@Test
	public void test() throws NetworkFactoryException, UserFactoryException {
		
Network net1 = new Network("Paris");
		
		PlusStation opera = new PlusStation(true, new GPSCoordinate(5,5), "Op�ra");
		for(int k = 0; k < 10; k++) {
			opera.addParkingSlot(new ParkingSlot(opera));
		}
		for(int k = 0; k < 4; k++) {
			opera.addBike(new MechanicalBike());

		}
		
		net1.addStation(opera);
		
		PlusStation chatelet = new PlusStation(true, new GPSCoordinate(11,17), "Ch�telet");
		for(int k = 0; k < 10; k++) {
			chatelet.addParkingSlot(new ParkingSlot(chatelet));
		}
		for(int k = 0; k < 4; k++) {
			chatelet.addBike(new MechanicalBike());
		}
		net1.addStation(chatelet);
		
		PlusStation luxembourg = new PlusStation(true, new GPSCoordinate(20,11), "Luxembourg");
		for(int k = 0; k < 10; k++) {
			luxembourg.addParkingSlot(new ParkingSlot(luxembourg));
		}
		for(int k = 0; k < 4; k++) {
			luxembourg.addBike(new MechanicalBike());
		}
		net1.addStation(luxembourg);
		
		PlusStation odeon = new PlusStation(true, new GPSCoordinate(25,5), "Od�on");
		for(int k = 0; k < 10; k++) {
			odeon.addParkingSlot(new ParkingSlot(odeon));
		}
		for(int k = 0; k < 4; k++) {
			odeon.addBike(new MechanicalBike());
		}
		net1.addStation(odeon);
		
		StandardStation montparnasse = new StandardStation(true, new GPSCoordinate(8,18), "Montparnasse");
		for(int k = 0; k < 10; k++) {
			montparnasse.addParkingSlot(new ParkingSlot(montparnasse));
		}
		for(int k = 0; k < 4; k++) {
			montparnasse.addBike(new MechanicalBike());
		}
		net1.addStation(montparnasse);
		
		StandardStation invalides = new StandardStation(true, new GPSCoordinate(10,15), "Invalides");
		for(int k = 0; k < 10; k++) {
			invalides.addParkingSlot(new ParkingSlot(invalides));
		}
		for(int k = 0; k < 4; k++) {
			invalides.addBike(new MechanicalBike());
		}
		net1.addStation(invalides);
		
		StandardStation defense = new StandardStation(true, new GPSCoordinate(24,4), "D�fense");
		for(int k = 0; k < 10; k++) {
			defense.addParkingSlot(new ParkingSlot(defense));
		}
		for(int k = 0; k < 4; k++) {
			defense.addBike(new MechanicalBike());
		}
		net1.addStation(defense);
				
		System.out.println(net1.isAKindBike(new ElectricalBike()));
		
//		net1.getStations().get(3).goOffline();
		
		User user = UserFactory.createUser("BroulQ", "nocard", "paris");
		
		user.takeARide(new GPSCoordinate(3,3), new GPSCoordinate(25,3.9), new PreferPlusStationStrategy(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(3));
		
		assertTrue(!net1.isAKindBike(new ElectricalBike()));
	}

}
