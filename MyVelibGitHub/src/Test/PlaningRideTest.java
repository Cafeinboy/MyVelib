package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Bike.ElectricalBike;
import Bike.MechanicalBike;
import Exception.UserFactoryException;
import GPSCoordinate.GPSCoordinate;
import Network.Network;
import Network.ParkingSlot;
import Network.PlusStation;
import Network.StandardStation;
import PlanningRide.AvoidPlusStationStrategy;
import PlanningRide.FastestPath;
import PlanningRide.PreferPlusStationStrategy;
import PlanningRide.ShortestPath;
import User.User;
import User.UserFactory;

public class PlaningRideTest {
	
	//Creation of a network and a User
	
	@Test
	public void testNetwork() throws UserFactoryException {
		
		Network net1 = new Network("Paris");
		
		PlusStation opera = new PlusStation(true, new GPSCoordinate(5,5), "Op�ra");
		for(int k = 0; k < 10; k++) {
			opera.addParkingSlot(new ParkingSlot(opera));
		}
		for(int k = 0; k < 4; k++) {
			opera.addBike(new MechanicalBike());
			opera.addBike(new ElectricalBike());

		}
		
		net1.addStation(opera);
		
		PlusStation chatelet = new PlusStation(true, new GPSCoordinate(11,17), "Ch�telet");
		for(int k = 0; k < 10; k++) {
			chatelet.addParkingSlot(new ParkingSlot(chatelet));
		}
		for(int k = 0; k < 4; k++) {
			chatelet.addBike(new MechanicalBike());
			chatelet.addBike(new ElectricalBike());
		}
		net1.addStation(chatelet);
		
		PlusStation luxembourg = new PlusStation(true, new GPSCoordinate(20,11), "Luxembourg");
		for(int k = 0; k < 10; k++) {
			luxembourg.addParkingSlot(new ParkingSlot(luxembourg));
		}
		for(int k = 0; k < 4; k++) {
			luxembourg.addBike(new MechanicalBike());
			luxembourg.addBike(new ElectricalBike());
		}
		net1.addStation(luxembourg);
		
		PlusStation odeon = new PlusStation(true, new GPSCoordinate(25,5), "Od�on");
		for(int k = 0; k < 10; k++) {
			odeon.addParkingSlot(new ParkingSlot(odeon));
		}
		for(int k = 0; k < 4; k++) {
			odeon.addBike(new MechanicalBike());
			odeon.addBike(new ElectricalBike());
		}
		net1.addStation(odeon);
		
		StandardStation montparnasse = new StandardStation(true, new GPSCoordinate(8,18), "Montparnasse");
		for(int k = 0; k < 10; k++) {
			montparnasse.addParkingSlot(new ParkingSlot(montparnasse));
		}
		for(int k = 0; k < 4; k++) {
			montparnasse.addBike(new MechanicalBike());
			montparnasse.addBike(new ElectricalBike());
		}
		net1.addStation(montparnasse);
		
		StandardStation invalides = new StandardStation(true, new GPSCoordinate(10,15), "Invalides");
		for(int k = 0; k < 10; k++) {
			invalides.addParkingSlot(new ParkingSlot(invalides));
		}
		for(int k = 0; k < 4; k++) {
			invalides.addBike(new MechanicalBike());
			invalides.addBike(new ElectricalBike());
		}
		net1.addStation(invalides);
		
		StandardStation defense = new StandardStation(true, new GPSCoordinate(24,4), "D�fense");
		for(int k = 0; k < 10; k++) {
			defense.addParkingSlot(new ParkingSlot(defense));
		}
		for(int k = 0; k < 4; k++) {
			defense.addBike(new MechanicalBike());
			defense.addBike(new ElectricalBike());
		}
		net1.addStation(defense);
		
		assertEquals(net1.getAvailableStations().size(), 7);

		
		User user = UserFactory.createUser("BroulQ", "nocard");
		
		//PreferPlusStation strategy
		user.takeARide(new GPSCoordinate(3,3), new GPSCoordinate(25,4), new PreferPlusStationStrategy(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(3));
		
		user.takeARide(new GPSCoordinate(3,3), new GPSCoordinate(25,3.9), new PreferPlusStationStrategy(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(3));
		
		net1.getStations().get(3).goOffline();
		user.takeARide(new GPSCoordinate(3,3), new GPSCoordinate(25,4), new PreferPlusStationStrategy(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(6));
		
		net1.getStations().get(3).setStatus(true);
		user.takeARide(new GPSCoordinate(3,3), new GPSCoordinate(25,3.9), new PreferPlusStationStrategy(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(3));
		
		user.takeARide(new GPSCoordinate(5,20), new GPSCoordinate(20,8), new PreferPlusStationStrategy(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(4));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(2));
		
		net1.getStations().get(2).goOffline();
		user.takeARide(new GPSCoordinate(5,20), new GPSCoordinate(20,8), new PreferPlusStationStrategy(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(4));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(3));
		net1.getStations().get(2).setStatus(true);
		
		//AvoidPlusStation strategy
		
		user.takeARide(new GPSCoordinate(3,3), new GPSCoordinate(25,4), new AvoidPlusStationStrategy(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(6));
		
		net1.getStations().get(6).goOffline();
		user.takeARide(new GPSCoordinate(3,3), new GPSCoordinate(25,4), new AvoidPlusStationStrategy(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(5));
		net1.getStations().get(6).setStatus(true);
		
		user.takeARide(new GPSCoordinate(10,18), new GPSCoordinate(24,11), new AvoidPlusStationStrategy(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(1));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(6));
		
		net1.getStations().get(6).goOffline();
		net1.getStations().get(5).goOffline();
		user.takeARide(new GPSCoordinate(10,18), new GPSCoordinate(24,11), new AvoidPlusStationStrategy(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(1));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(4));
		net1.getStations().get(6).setStatus(true);
		net1.getStations().get(5).setStatus(true);
		
		//ShortestPath strategy
		
		//Ne marche pas dans cette version, la version correcte existe mais dans un autre projet,
		//elle arrive asap
		
		user.takeARide(new GPSCoordinate(5,4), new GPSCoordinate(10,16), new ShortestPath(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(1));
	
		//FastestPath strategy
		
		user.takeARide(new GPSCoordinate(8,18), new GPSCoordinate(25,5), new FastestPath(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(4));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(3));
		
		user.takeARide(new GPSCoordinate(8,18), new GPSCoordinate(25,5), new FastestPath(), new ElectricalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(4));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(3));
		
		user.takeARide(new GPSCoordinate(3,4), new GPSCoordinate(10,16), new FastestPath(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(5));
		
		user.takeARide(new GPSCoordinate(5,5), new GPSCoordinate(10,17), new FastestPath(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(1));
		//Ici on teste le cas o� la strategy donne des r�sultats diff�rents avec un v�lo �lectrique
		//et un v�lo classique. Le point d'arriv�e a pour cela �t� choisi au terme de longs et 
		//fastidieux calculs.
		user.takeARide(new GPSCoordinate(5,5), new GPSCoordinate(22,7.1), new FastestPath(), new MechanicalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(2));
		
		user.takeARide(new GPSCoordinate(5,5), new GPSCoordinate(22,7.1), new FastestPath(), new ElectricalBike(), net1);
		assertEquals(user.getRide().getListStation().get(0), net1.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), net1.getStations().get(6));
		
	}

}
