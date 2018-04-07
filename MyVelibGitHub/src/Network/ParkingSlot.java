package Network;

import Bike.Bike;
import Bike.MechanicalBike;

public class ParkingSlot {
	
	private static int uniqueID = 0;
	
	//Attributes
	
	private int slotID;
	private Bike bike;
	private boolean outOfOrder;
	private Station station;
	
	//Constructor
	
	public ParkingSlot() {
		super();
		uniqueID = uniqueID + 1;
		this.slotID = uniqueID;
		this.station = null;
		this.bike = null;
	}	
	
	/**
	 * Creates a new empty ParkingSlot
	 * @param station
	 */
	public ParkingSlot(Station station) {
		super();
		uniqueID = uniqueID + 1;
		this.slotID = uniqueID;
		this.station = station;
		this.bike = null;
	}
	
	/**
	 * Creates a new station with a bike, that hence is not free
	 * @param bike
	 * @param station
	 */
	public ParkingSlot(Station station, Bike bike) {
		super();
		uniqueID = uniqueID + 1;
		this.slotID = uniqueID;
		this.bike = bike;
		this.station = station;
	}

	@Override
	public String toString() {
		return "ParkingSlot : slotID " + slotID + "; bike : " + bike + "\n";
	}

	//Getters and setters

	public int getSlotID() {
		return slotID;
	}
	
	/**
	 * A ParkingSlot is free if it has no bike
	 * @return
	 */
	public boolean isFree() {
		return this.bike == null;
	}



	public boolean isOutOfOrder() {
		return outOfOrder;
	}

	public void setOutOfOrder(boolean outOfOrder) {
		this.outOfOrder = outOfOrder;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
	
	public Bike getBike() {
		return this.bike;
	}
	
	public void setBike(Bike bike) {
		this.bike = bike;
	}
	
	//Methods
	
	/**
	 * Puts a bike in this ParkingSlot, if it is possible
	 * @param bike
	 */
	public void giveBike(Bike bike) {
		if(this.isFree() && !this.isOutOfOrder()) {
			this.setBike(bike);
		}
		else {
			System.err.println("The parking slot is occupied or out of order");
		}
	}
	
	/**
	 * Returns the bike from this ParkingSlot, and frees it
	 * Will return null if there is no bike
	 * @return
	 */
	public Bike takeBike() {
		Bike bike = this.bike;
		this.bike = null;
		return bike;
	}
	
	@Override
	public boolean equals(Object park) {
		if (park instanceof ParkingSlot) {
			ParkingSlot parkSlot = (ParkingSlot) park;
			return this.slotID == parkSlot.getSlotID();
		}
		return false;
	}
	
}
