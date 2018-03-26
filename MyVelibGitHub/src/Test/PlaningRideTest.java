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
		
		PlusStation opera = new PlusStation(true, new GPSCoordinate(5,5), "Opéra");
		for(int k = 0; k < 10; k++) {
			opera.addParkingSlot(new ParkingSlot(opera));
		}
		for(int k = 0; k < 4; k++) {
			opera.addBike(new MechanicalBike());
			opera.addBike(new ElectricalBike());

		}
		
		Network.addStation(opera);
		
		PlusStation chatelet = new PlusStation(true, new GPSCoordinate(11,17), "Châtelet");
		for(int k = 0; k < 10; k++) {
			chatelet.addParkingSlot(new ParkingSlot(chatelet));
		}
		for(int k = 0; k < 4; k++) {
			chatelet.addBike(new MechanicalBike());
			chatelet.addBike(new ElectricalBike());
		}
		Network.addStation(chatelet);
		
		PlusStation luxembourg = new PlusStation(true, new GPSCoordinate(20,11), "Luxembourg");
		for(int k = 0; k < 10; k++) {
			luxembourg.addParkingSlot(new ParkingSlot(luxembourg));
		}
		for(int k = 0; k < 4; k++) {
			luxembourg.addBike(new MechanicalBike());
			luxembourg.addBike(new ElectricalBike());
		}
		Network.addStation(luxembourg);
		
		PlusStation odeon = new PlusStation(true, new GPSCoordinate(25,5), "Odéon");
		for(int k = 0; k < 10; k++) {
			odeon.addParkingSlot(new ParkingSlot(odeon));
		}
		for(int k = 0; k < 4; k++) {
			odeon.addBike(new MechanicalBike());
			odeon.addBike(new ElectricalBike());
		}
		Network.addStation(odeon);
		
		StandardStation montparnasse = new StandardStation(true, new GPSCoordinate(8,18), "Montparnasse");
		for(int k = 0; k < 10; k++) {
			montparnasse.addParkingSlot(new ParkingSlot(montparnasse));
		}
		for(int k = 0; k < 4; k++) {
			montparnasse.addBike(new MechanicalBike());
			montparnasse.addBike(new ElectricalBike());
		}
		Network.addStation(montparnasse);
		
		StandardStation invalides = new StandardStation(true, new GPSCoordinate(10,15), "Invalides");
		for(int k = 0; k < 10; k++) {
			invalides.addParkingSlot(new ParkingSlot(invalides));
		}
		for(int k = 0; k < 4; k++) {
			invalides.addBike(new MechanicalBike());
			invalides.addBike(new ElectricalBike());
		}
		Network.addStation(invalides);
		
		StandardStation defense = new StandardStation(true, new GPSCoordinate(24,4), "Défense");
		for(int k = 0; k < 10; k++) {
			defense.addParkingSlot(new ParkingSlot(defense));
		}
		for(int k = 0; k < 4; k++) {
			defense.addBike(new MechanicalBike());
			defense.addBike(new ElectricalBike());
		}
		Network.addStation(defense);
		
		assertEquals(Network.getAvailableStations().size(), 7);

		
		User user = UserFactory.createUser("BroulQ", "nocard");
		
		//PreferPlusStation strategy
		user.takeARide(new GPSCoordinate(3,3), new GPSCoordinate(25,4), new PreferPlusStationStrategy(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(3));
		
		user.takeARide(new GPSCoordinate(3,3), new GPSCoordinate(25,3.9), new PreferPlusStationStrategy(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(3));
		
		Network.getStations().get(3).goOffline();
		user.takeARide(new GPSCoordinate(3,3), new GPSCoordinate(25,4), new PreferPlusStationStrategy(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(6));
		
		Network.getStations().get(3).setStatus(true);
		user.takeARide(new GPSCoordinate(3,3), new GPSCoordinate(25,3.9), new PreferPlusStationStrategy(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(3));
		
		user.takeARide(new GPSCoordinate(5,20), new GPSCoordinate(20,8), new PreferPlusStationStrategy(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(4));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(2));
		
		Network.getStations().get(2).goOffline();
		user.takeARide(new GPSCoordinate(5,20), new GPSCoordinate(20,8), new PreferPlusStationStrategy(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(4));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(3));
		Network.getStations().get(2).setStatus(true);
		
		//AvoidPlusStation strategy
		
		user.takeARide(new GPSCoordinate(3,3), new GPSCoordinate(25,4), new AvoidPlusStationStrategy(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(6));
		
		Network.getStations().get(6).goOffline();
		user.takeARide(new GPSCoordinate(3,3), new GPSCoordinate(25,4), new AvoidPlusStationStrategy(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(5));
		Network.getStations().get(6).setStatus(true);
		
		user.takeARide(new GPSCoordinate(10,18), new GPSCoordinate(24,11), new AvoidPlusStationStrategy(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(1));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(6));
		
		Network.getStations().get(6).goOffline();
		Network.getStations().get(5).goOffline();
		user.takeARide(new GPSCoordinate(10,18), new GPSCoordinate(24,11), new AvoidPlusStationStrategy(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(1));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(4));
		Network.getStations().get(6).setStatus(true);
		Network.getStations().get(5).setStatus(true);
		
		//ShortestPath strategy
		
		//Ne marche pas dans cette version, la version correcte existe mais dans un autre projet,
		//elle arrive asap
		
		user.takeARide(new GPSCoordinate(5,4), new GPSCoordinate(10,16), new ShortestPath(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(1));
	
		//FastestPath strategy
		
		user.takeARide(new GPSCoordinate(8,18), new GPSCoordinate(25,5), new FastestPath(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(4));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(3));
		
		user.takeARide(new GPSCoordinate(8,18), new GPSCoordinate(25,5), new FastestPath(), new ElectricalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(4));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(3));
		
		user.takeARide(new GPSCoordinate(3,4), new GPSCoordinate(10,16), new FastestPath(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(5));
		
		user.takeARide(new GPSCoordinate(5,5), new GPSCoordinate(10,17), new FastestPath(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(1));
		//Ici on teste le cas où la strategy donne des résultats différents avec un vélo électrique
		//et un vélo classique. Le point d'arrivée a pour cela été choisi au terme de longs et 
		//fastidieux calculs.
		user.takeARide(new GPSCoordinate(5,5), new GPSCoordinate(22,7.1), new FastestPath(), new MechanicalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(2));
		
		user.takeARide(new GPSCoordinate(5,5), new GPSCoordinate(22,7.1), new FastestPath(), new ElectricalBike());
		assertEquals(user.getRide().getListStation().get(0), Network.getStations().get(0));
		assertEquals(user.getRide().getListStation().get(1), Network.getStations().get(6));
		
	}

}
