package Network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import Bike.*;
import GPSCoordinate.*;
import RecordSystem.*;
import Ride.*;
import Time.Time;
import User.*;

public abstract class Station extends Observable {
	
	private static int uniqueID = 0;
	
	//Attributes
	
	private int ID;
	private String name;
	private boolean status;
	private boolean full;
	private ArrayList<ParkingSlot> parkingSlots;
	private GPSCoordinate coordinates;
	private LogTotalRecord allRecord;
	
	//Array containing the Users currently on their way towards this Station
	private ArrayList<User> users;
	
	//Constructor
	
	public Station(boolean status, GPSCoordinate coordinates, String name) {
		super();
		uniqueID = uniqueID + 1;
		this.ID = uniqueID;
		this.status = status;
		this.parkingSlots = new ArrayList<ParkingSlot>();
		this.coordinates = coordinates;
		this.name = name;
		this.users = new ArrayList<User>();
		//There are no Parking slots so it is full
		this.full = true;
		this.allRecord = new LogTotalRecord();
	}
	
	//Getters and setters

	public int getID() {
		return ID;
	}

	public boolean isOnline() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean isFull() {
		return this.full;
	}
	
	public void setFull(boolean full) {
		this.full = full;
	}

	public ArrayList<ParkingSlot> getParkingSlots() {
		return parkingSlots;
	}

	public void setParkingSlots(ArrayList<ParkingSlot> parkingSlots) {
		this.parkingSlots = parkingSlots;
	}
	
	public void addParkingSlot(ParkingSlot parkingSlot) {
		this.parkingSlots.add(parkingSlot);
		if(parkingSlot.isFree() && !parkingSlot.isOutOfOrder()) {
			this.full = false;
			this.allRecord.addRecord(new Record(Time.getTimeInMinuteSinceCreation(), parkingSlot));
		}
	}

	public GPSCoordinate getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(GPSCoordinate coordinates) {
		this.coordinates = coordinates;
	}

	public int getNumberOfSpots() {
		return this.getParkingSlots().size();
	}
	
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object station) {
		if (station instanceof Station) {
			Station stat = (Station) station;
			return (this.ID == stat.ID && this.status==stat.status && this.parkingSlots==stat.parkingSlots && this.coordinates.equals(stat.coordinates) && this.name.equals(stat.name) && this.users==stat.users && this.full==stat.full );	
		}
		return false;
	}
	
	//Methods
	
	/**
	 * Adds a bike to the station, if possible, and puts it in the first
	 * free parking slot
	 * @param bike
	 */
	
	public void addBike(Bike bike) {
		if(this.isFull()) {
			System.err.println("The station is full");
		}
		else {
			ParkingSlot nextSpot = this.getFreeSlots().get(0);
			nextSpot.giveBike(bike);
			this.allRecord.addRecord(new Record(Time.getTimeInMinuteSinceCreation(), nextSpot));
			if(this.numberOfFreeSpots() == 0) {
				this.getFull();
			}
		}
	}
	
	/**
	 * Returns the ArrayList of free slots in the station
	 * @return
	 */
	
	public ArrayList<ParkingSlot> getFreeSlots() {
		ArrayList<ParkingSlot> freeSlots = new ArrayList<ParkingSlot>();
		for(ParkingSlot ps : this.parkingSlots) {
			if(ps.isFree() && !ps.isOutOfOrder()) {
				freeSlots.add(ps);
			}
		}
		return freeSlots;
	}
	
	/**
	 * Returns the number of free spots at the station
	 * @return
	 */
	
	public int numberOfFreeSpots() {
		return this.getFreeSlots().size();
	}
	

	
	/**
	 * This method will be used to add time credit in the case of a Plus Station,
	 * but should do nothing if it is a Standard Station
	 * @param u
	 */
	public void giveTimeCredit(User u) {};
	
	
	/**
	 * Adds an user to the observers of the station, should be done every time a User
	 * sets this station as a destination
	 */
	public void addUser(User user) {
		this.users.add(user);
	}
	
	/**
	 * Removes an user, should be done every time an user of this station gives back his 
	 * bike, whether it is at this station or not
	 */
	public void removeUser(User user) {
		if(this.users.contains(user)) {
			this.users.remove(user);
		}
	}
	
	/**
	 * Notifies the users if the station goes offline or gets full.
	 * The users should then be removed.
	 */
	public void notifyUsers(String message) {
		for(User user : this.users) {
			user.update(this, message);
		}
	}
	
	
	/**
	 * When the station goes offline, it notifies its observers, then removes them from
	 * the observers list, and changes its status
	 */
	public void goOffline() {
		String message = "Your destination station " + this.getName() + " has gone offline. Computing new destination ...";
		this.notifyUsers(message);
		this.setUsers(new ArrayList<User>());
		this.setStatus(false);	
	}
	
	/**
	 * When the station gets full, it notifies its observers, then removes them from
	 * the observers list, and changes its status
	 */
	public void getFull() {
		String message = "Your destination station" + this.getName() + " has no more available parking slots. Computing new destination ...";
		this.notifyUsers(message);
		this.setUsers(new ArrayList<User>());
		this.setFull(true);	
	}
	

	
	/**
	 * Returns the number of (available) mechanical bikes
	 * @return
	 */
	public int numberOfMechanicalBike() {
		int nbMechBike = 0;
		for(ParkingSlot ps : this.getParkingSlots()) {
			if(!ps.isOutOfOrder() && ps.getBike() instanceof MechanicalBike) {
				nbMechBike++;
			}
		}
		return nbMechBike;
	}
	
	/**
	 * Returns the number of (available) electrical bikes
	 * @return
	 */
	public int numberOfElectricalBike() {
		int nbElecBike = 0;
		for(ParkingSlot ps : this.getParkingSlots()) {
			if(ps.isOutOfOrder()==false && ps.getBike() instanceof ElectricalBike) {
				nbElecBike++;
			}
		}
		return nbElecBike;
	}
	
	/**
	 * Returns the slots (not out of order) that have a Mechanical bike
	 * @return
	 */
	public ArrayList<ParkingSlot> getSlotsWithMechanicalBike() {
		ArrayList<ParkingSlot> slots =new ArrayList<ParkingSlot>();
		for(ParkingSlot ps : this.getParkingSlots()) {
			if(!ps.isOutOfOrder() && ps.getBike() instanceof MechanicalBike) {
				slots.add(ps);
			}
		}
		return slots;
	}
	
	/**
	 * Returns the slots (not out of order) that have a Electrical bike
	 * @return
	 */
	public ArrayList<ParkingSlot> getSlotsWithElectricalBike() {
		ArrayList<ParkingSlot> slots =new ArrayList<ParkingSlot>();
		for(ParkingSlot ps : this.getParkingSlots()) {
			if(!ps.isOutOfOrder() && ps.getBike() instanceof ElectricalBike) {
				slots.add(ps);
			}
		}
		return slots;
	}
	
	/**
	 * Returns the number of bikes of the same type as the
	 * bike given as an argument
	 * @param bike
	 * @return
	 */
	public int numberOfKindBike(Bike bike) {
		if (bike instanceof MechanicalBike) {
			return this.numberOfMechanicalBike();
		}
		if (bike instanceof ElectricalBike) {
			return this.numberOfElectricalBike();
		}
		else {
			return 0;
		}
	}
	
	/**
	 * This function checks if it is possible to give back a bike,
	 * but it should not be necessary as that verification should be
	 * done beforehand
	 * It also checks if the User has a bike to give back, then removes
	 * it from the User, and it puts the bike in the first available free ParkingSlot
	 */
	
	
	
	public void giveBackBike(User user) {
		if(user.getRide().getBike() != null) {
			Bike bike = user.getRide().getBike();
			if(this.isFull() || !this.isOnline()) {
				System.out.println("You cannot return a bike here");
				Ride oldRide = user.getRide();
				Ride newRide = new Ride(oldRide.getListStation().get(1).getCoordinates(), oldRide.getFinishingPoint(), oldRide.getRideStrategy());
				newRide.haveARide(oldRide.getBike());
				user.getRide().setListStation(new ArrayList<Station>(Arrays.asList(oldRide.getListStation().get(0), newRide.getListStation().get(1))));
			}
			else {
				ParkingSlot nextSpot = this.getFreeSlots().get(0);
				nextSpot.giveBike(bike);
				this.allRecord.putAnEndForARecord(nextSpot);
				if(this.numberOfFreeSpots() == 0) {
					this.getFull();
				}
			}
			user.getRide().setBike(null);
		}
		else {
			System.err.println("User does not have a bike");
		}
	}
	
	/**
	 * This function allows a User to take a bike of the wanted type,
	 * it will check if such a bike is available (even though that should
	 * be done beforehand), and returns a bike of the wanted type.
	 * The String condition will be quite strict : "electrical" or "mechanical" only, 
	 * be careful when you call it.
	 * If there is no bike of the wanted type, a new beginning station will be calculated
	 * @param bikeType
	 */
	public void takeBike(Bike bike, User user) {
		if(user.getRide().getBike() == null) {
			if(bike instanceof MechanicalBike) {
				if(this.numberOfMechanicalBike() == 0) {
					System.out.println("No mechanical bike at this station");
					user.getRide().haveARide(bike);
				}
				else {
					ParkingSlot nextSlot = this.getSlotsWithMechanicalBike().get(0);
					Bike realbike = nextSlot.takeBike();
					this.allRecord.addRecord(new Record(Time.getTimeInMinuteSinceCreation(), nextSlot));
					this.setFull(false);
					user.getRide().setBike(realbike);
				}
			}
			else if (bike instanceof ElectricalBike) {
				if(this.numberOfElectricalBike() == 0) {
					System.out.println("No electrical bike at this station");
					user.getRide().haveARide(bike);
				}
				else {
					ParkingSlot nextSlot = this.getSlotsWithElectricalBike().get(0);
					Bike realbike = nextSlot.takeBike();
					this.allRecord.addRecord(new Record(Time.getTimeInMinuteSinceCreation(), nextSlot));
					this.setFull(false);
					user.getRide().setBike(realbike);
				}
			}
		}
		else {
			System.err.println("User already has a bike");
		}
	}
	
}
