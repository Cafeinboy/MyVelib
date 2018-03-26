package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Bike.ElectricalBike;
import Bike.MechanicalBike;
import GPSCoordinate.GPSCoordinate;
import Network.Network;
import Network.ParkingSlot;
import Network.PlusStation;
import Network.StandardStation;
import Network.Station;
import Ride.Ride;
import User.User;

public class NetworkTest {
	
	@Test
	public void parkingSlotTest() {
		Object a = new ParkingSlot();
		if(a instanceof ParkingSlot) {
			ParkingSlot ps = (ParkingSlot) a;
			assertEquals(ps.getBike(), null);
			assertEquals(ps.isOutOfOrder(), false);
			assertEquals(ps.isFree(), true);
			assertEquals(ps.getStation(), null);
			
			ps.giveBike(new MechanicalBike());
			assertEquals(ps.isFree(), false);
			
			ps.setStation(new StandardStation(true, new GPSCoordinate(5, 10), "test station"));
			assertEquals(ps.getStation().getName(), "test station");
			
			ps.takeBike();
			assertEquals(ps.isFree(), true);
			
			ps.setOutOfOrder(true);
			assertTrue(ps.isOutOfOrder());

		}
	}

	@Test
	public void standardStationTest() {
		Object a = new StandardStation(true, new GPSCoordinate(5, 10), "test station");
		if(a instanceof Station) {
			Station standStation = (Station) a;
			assertEquals(standStation.getCoordinates(), new GPSCoordinate(5, 10));
			assertEquals(standStation.getFreeSlots(), new ArrayList<ParkingSlot>());
			assertEquals(standStation.isFull(), true);
			assertEquals(standStation.getID(), 1);
			assertEquals(standStation.getName(), "test station");
			assertEquals(standStation.getNumberOfSpots(), 0);
			assertEquals(standStation.getParkingSlots(), new ArrayList<ParkingSlot>());
			assertEquals(standStation.getSlotsWithElectricalBike(), new ArrayList<ParkingSlot>());
			assertEquals(standStation.getSlotsWithMechanicalBike(), new ArrayList<ParkingSlot>());
			assertEquals(standStation.getUsers(), new ArrayList<User>());
			assertEquals(standStation.isOnline(), true);
			assertEquals(standStation.numberOfFreeSpots(), 0);
			assertEquals(standStation.numberOfElectricalBike(), 0);
			assertEquals(standStation.numberOfMechanicalBike(), 0);
			
			
			standStation.addParkingSlot(new ParkingSlot(standStation));
			standStation.addParkingSlot(new ParkingSlot(standStation));
			standStation.addParkingSlot(new ParkingSlot(standStation));
			
			assertEquals(standStation.getNumberOfSpots(), 3);
			assertEquals(standStation.numberOfFreeSpots(), 3);
			assertEquals(standStation.numberOfElectricalBike(), 0);
			assertEquals(standStation.numberOfMechanicalBike(), 0);
			
			standStation.addBike(new MechanicalBike());
			
			assertEquals(standStation.getNumberOfSpots(), 3);
			assertEquals(standStation.numberOfFreeSpots(), 2);
			assertEquals(standStation.numberOfElectricalBike(), 0);
			assertEquals(standStation.numberOfMechanicalBike(), 1);
			
			standStation.addBike(new ElectricalBike());
			
			assertEquals(standStation.getNumberOfSpots(), 3);
			assertEquals(standStation.numberOfFreeSpots(), 1);
			assertEquals(standStation.numberOfElectricalBike(), 1);
			assertEquals(standStation.numberOfMechanicalBike(), 1);
			assertFalse(standStation.isFull());
			
			standStation.addBike(new MechanicalBike());
			
			assertEquals(standStation.getNumberOfSpots(), 3);
			assertEquals(standStation.numberOfFreeSpots(), 0);
			assertEquals(standStation.numberOfElectricalBike(), 1);
			assertEquals(standStation.numberOfMechanicalBike(), 2);
			assertTrue(standStation.isFull());
			
			standStation.addParkingSlot(new ParkingSlot(standStation));
			
			assertEquals(standStation.getNumberOfSpots(), 4);
			assertEquals(standStation.numberOfFreeSpots(), 1);
			assertEquals(standStation.numberOfElectricalBike(), 1);
			assertEquals(standStation.numberOfMechanicalBike(), 2);
			assertFalse(standStation.isFull());
			assertEquals(standStation.numberOfKindBike(new MechanicalBike()), 2);
			assertEquals(standStation.numberOfKindBike(new ElectricalBike()), 1);
			
			
			User user1 = new User();
			user1.setRide(new Ride());
			
			standStation.takeBike(new MechanicalBike(), user1);
			assertEquals(standStation.numberOfFreeSpots(), 2);
			assertEquals(standStation.numberOfElectricalBike(), 1);
			assertEquals(standStation.numberOfMechanicalBike(), 1);
			
			User user2 = new User();
			user2.setRide(new Ride());
			
			standStation.takeBike(new ElectricalBike(), user2);
			assertEquals(standStation.numberOfFreeSpots(), 3);
			assertEquals(standStation.numberOfElectricalBike(), 0);
			assertEquals(standStation.numberOfMechanicalBike(), 1);
			
			standStation.giveBackBike(user1);
			assertEquals(standStation.numberOfFreeSpots(), 2);
			assertEquals(standStation.numberOfElectricalBike(), 0);
			assertEquals(standStation.numberOfMechanicalBike(), 2);
			
			standStation.giveBackBike(user2);
			assertEquals(standStation.numberOfFreeSpots(), 1);
			assertEquals(standStation.numberOfElectricalBike(), 1);
			assertEquals(standStation.numberOfMechanicalBike(), 2);
			
			user1.getRide().setBike(new MechanicalBike());
			standStation.giveBackBike(user1);
			
			assertTrue(standStation.isFull());
			
			standStation.takeBike(new ElectricalBike(), user2);
			assertEquals(standStation.numberOfFreeSpots(), 1);
			
			standStation.getFreeSlots().get(0).setOutOfOrder(true);
			assertEquals(standStation.numberOfFreeSpots(), 0);
			
		}
	}
	
	@Test
	public void plusStationTest() {
		Object a = new PlusStation(true, new GPSCoordinate(5, 10), "test station");
		if(a instanceof Station) {
			Station plusStation = (Station) a;
			assertEquals(plusStation.getCoordinates(), new GPSCoordinate(5, 10));
			assertEquals(plusStation.getFreeSlots(), new ArrayList<ParkingSlot>());
			assertEquals(plusStation.isFull(), true);
			assertEquals(plusStation.getName(), "test station");
			assertEquals(plusStation.getNumberOfSpots(), 0);
			assertEquals(plusStation.getParkingSlots(), new ArrayList<ParkingSlot>());
			assertEquals(plusStation.getSlotsWithElectricalBike(), new ArrayList<ParkingSlot>());
			assertEquals(plusStation.getSlotsWithMechanicalBike(), new ArrayList<ParkingSlot>());
			assertEquals(plusStation.getUsers(), new ArrayList<User>());
			assertEquals(plusStation.isOnline(), true);
			assertEquals(plusStation.numberOfFreeSpots(), 0);
			assertEquals(plusStation.numberOfElectricalBike(), 0);
			assertEquals(plusStation.numberOfMechanicalBike(), 0);
			
			
			plusStation.addParkingSlot(new ParkingSlot(plusStation));
			plusStation.addParkingSlot(new ParkingSlot(plusStation));
			plusStation.addParkingSlot(new ParkingSlot(plusStation));
			
			assertEquals(plusStation.getNumberOfSpots(), 3);
			assertEquals(plusStation.numberOfFreeSpots(), 3);
			assertEquals(plusStation.numberOfElectricalBike(), 0);
			assertEquals(plusStation.numberOfMechanicalBike(), 0);
			
			plusStation.addBike(new MechanicalBike());
			
			assertEquals(plusStation.getNumberOfSpots(), 3);
			assertEquals(plusStation.numberOfFreeSpots(), 2);
			assertEquals(plusStation.numberOfElectricalBike(), 0);
			assertEquals(plusStation.numberOfMechanicalBike(), 1);
			
			plusStation.addBike(new ElectricalBike());
			
			assertEquals(plusStation.getNumberOfSpots(), 3);
			assertEquals(plusStation.numberOfFreeSpots(), 1);
			assertEquals(plusStation.numberOfElectricalBike(), 1);
			assertEquals(plusStation.numberOfMechanicalBike(), 1);
			assertFalse(plusStation.isFull());
			
			plusStation.addBike(new MechanicalBike());
			
			assertEquals(plusStation.getNumberOfSpots(), 3);
			assertEquals(plusStation.numberOfFreeSpots(), 0);
			assertEquals(plusStation.numberOfElectricalBike(), 1);
			assertEquals(plusStation.numberOfMechanicalBike(), 2);
			assertTrue(plusStation.isFull());
			
			plusStation.addParkingSlot(new ParkingSlot(plusStation));
			
			assertEquals(plusStation.getNumberOfSpots(), 4);
			assertEquals(plusStation.numberOfFreeSpots(), 1);
			assertEquals(plusStation.numberOfElectricalBike(), 1);
			assertEquals(plusStation.numberOfMechanicalBike(), 2);
			assertFalse(plusStation.isFull());
			assertEquals(plusStation.numberOfKindBike(new MechanicalBike()), 2);
			assertEquals(plusStation.numberOfKindBike(new ElectricalBike()), 1);
			
			
			User user1 = new User();
			user1.setRide(new Ride());
			
			plusStation.takeBike(new MechanicalBike(), user1);
			assertEquals(plusStation.numberOfFreeSpots(), 2);
			assertEquals(plusStation.numberOfElectricalBike(), 1);
			assertEquals(plusStation.numberOfMechanicalBike(), 1);
			
			User user2 = new User();
			user2.setRide(new Ride());
			
			plusStation.takeBike(new ElectricalBike(), user2);
			assertEquals(plusStation.numberOfFreeSpots(), 3);
			assertEquals(plusStation.numberOfElectricalBike(), 0);
			assertEquals(plusStation.numberOfMechanicalBike(), 1);
			
			plusStation.giveBackBike(user1);
			assertEquals(plusStation.numberOfFreeSpots(), 2);
			assertEquals(plusStation.numberOfElectricalBike(), 0);
			assertEquals(plusStation.numberOfMechanicalBike(), 2);
			
			plusStation.giveBackBike(user2);
			assertEquals(plusStation.numberOfFreeSpots(), 1);
			assertEquals(plusStation.numberOfElectricalBike(), 1);
			assertEquals(plusStation.numberOfMechanicalBike(), 2);
			
			user1.getRide().setBike(new MechanicalBike());
			plusStation.giveBackBike(user1);
			
			assertTrue(plusStation.isFull());
			
			plusStation.takeBike(new ElectricalBike(), user2);
			assertEquals(plusStation.numberOfFreeSpots(), 1);
			
			plusStation.getFreeSlots().get(0).setOutOfOrder(true);
			assertEquals(plusStation.numberOfFreeSpots(), 0);
			
			
		}
	}

	
	@Test
	public void networkTest() {
		
		assertEquals(Network.getAvailableStations().size(), 0);
		assertEquals(Network.getPlusStations(Network.getAvailableStations()).size(), 0);
		assertEquals(Network.getNonPlusStations(Network.getAvailableStations()).size() ,0);
		assertEquals(Network.getStations().size(), 0);
		assertEquals(Network.getUsers().size(), 0);
		
		Network.addStation(new StandardStation(true, new GPSCoordinate(5, 10), "test station 1"));
		Network.addStation(new PlusStation(true, new GPSCoordinate(10, 10), "test station 2"));
		
		assertEquals(Network.getAvailableStations().size(), 2);
		assertEquals(Network.getPlusStations(Network.getAvailableStations()).size(), 1);
		assertEquals(Network.getNonPlusStations(Network.getAvailableStations()).size() ,1);
		assertEquals(Network.getStations().size(), 2);
		assertEquals(Network.getPlusStations(Network.getAvailableStations()).get(0).getName(), "test station 2");
		
		Network.addUser(new User());
		assertEquals(Network.getUsers().size(), 1);
		
		Network.getPlusStations(Network.getAvailableStations()).get(0).goOffline();
		assertEquals(Network.getAvailableStations().size(), 1);
		assertEquals(Network.getPlusStations(Network.getAvailableStations()).size(), 0);
		assertEquals(Network.getNonPlusStations(Network.getAvailableStations()).size() ,1);
		assertEquals(Network.getStations().size(), 2);
		
	}
}