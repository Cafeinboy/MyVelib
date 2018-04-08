package Ride;

import java.util.ArrayList;
import java.util.Arrays;

import Bike.Bike;
import GPSCoordinate.*;
import Network.Network;
import Network.StandardStation;
import Network.Station;
import PlanningRide.*;

public class Ride implements RideStrategyInterface{
	
	//Attributes
	
	private GPSCoordinate beginingPoint;
	private GPSCoordinate finishingPoint;
	private int beginingTime;
	private int endingTime;
	private Bike wishBike;
	private Bike realBike;
	private int price;
	private PlanningRideStrategy rideStrategy;
	private ArrayList<Station> listStation;
	private Network net;
	
	//Constructors
	
	public Ride(GPSCoordinate beginingPoint, GPSCoordinate finishingPoint, PlanningRideStrategy rideStrategy, Network net) {
		super();
		this.beginingPoint = beginingPoint;
		this.finishingPoint = finishingPoint;
		this.rideStrategy = rideStrategy;
		this.net = net;
		this.listStation = new ArrayList<Station>(); 
		this.listStation.add(new StandardStation()); 
		this.listStation.add(new StandardStation());
	}

	public Ride() {
		super();
		this.listStation = new ArrayList<Station>(); 
		this.listStation.add(new StandardStation()); 
		this.listStation.add(new StandardStation());
	}
	
	//Getters and Setters

	public GPSCoordinate getBeginingPoint() {
		return beginingPoint;
	}

	public void setBeginingPoint(GPSCoordinate beginingPoint) {
		this.beginingPoint = beginingPoint;
	}

	public GPSCoordinate getFinishingPoint() {
		return finishingPoint;
	}

	public void setFinishingPoint(GPSCoordinate finishingPoint) {
		this.finishingPoint = finishingPoint;
	}

	public int getBeginingTime() {
		return beginingTime;
	}

	public void setBeginingTime(int beginingTime) {
		this.beginingTime = beginingTime;
	}

	public int getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(int endingTime) {
		this.endingTime = endingTime;
	}

	public Bike getWishBike() {
		return wishBike;
	}
	
	public Bike getRealBike() {
		return realBike;
	}

	public void setBike(Bike bike) {
		this.realBike = bike;
	}
	
	public ArrayList<Station> getListStation() {
		return listStation;
	}

	public void setListStation(ArrayList<Station> listStation) {
		this.listStation = listStation;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return this.price;
	}
	
	public Network getNet() {
		return net;
	}

	public void setNet(Network net) {
		this.net = net;
	}

	public PlanningRideStrategy getRideStrategy() {
		return rideStrategy;
	}

	public void setRideStrategy(PlanningRideStrategy rideStrategy) {
		this.rideStrategy = rideStrategy;
	}
	
	public void setWishBike(Bike wishBike) {
		this.wishBike = wishBike;
	}
	
	//Methods


	/**
	 * Changes the beginning station of the ride, is used if there are no more
	 * available bikes at the station when the user gets there
	 * @param station
	 */
	
	public void changeBeginingStation(Station station) {
		listStation.set(0, station);
	}
	
	/**
	 * Changes the finishing station of the ride, is used if there are no more
	 * available slots at the station when the user gets there
	 * @param station
	 */
	
	public void changeFinishingStation(Station station) {
		listStation.set(1, station);
	}


	//New /!\
	@Override
	public void haveARide(Bike wantedBike) {
		if (!net.isAKindBike(wantedBike)) {
			System.err.println("This kind of bike does not exist in the system. Maybe all have been rented.");
			this.listStation =  new ArrayList<Station>(Arrays.asList(null, null));
		}
		else {
			this.listStation = this.rideStrategy.planARide(this.beginingPoint,this.finishingPoint, wishBike, this.net);
		}
	}

	@Override
	public String toString() {
		return "Ride [beginingPoint=" + beginingPoint + ", finishingPoint=" + finishingPoint + ", beginingTime="
				+ beginingTime + ", endingTime=" + endingTime + ", wishBike=" + wishBike + ", realBike=" + realBike
				+ ", price=" + price + ", rideStrategy=" + rideStrategy + ", listStation=" + listStation + ", net="
				+ net + "]";
	}

}
