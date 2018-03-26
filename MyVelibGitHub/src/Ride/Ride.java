package Ride;

import java.util.ArrayList;
import Bike.Bike;
import GPSCoordinate.*;
import Network.Station;
import PlanningRide.*;

public class Ride implements RideStrategyInterface{
	
	//Attributes
	
	private GPSCoordinate beginingPoint;
	private GPSCoordinate finishingPoint;
	private int beginingTime;
	private int endingTime;
	private Bike bike;
	private int price;
	private PlanningRideStrategy rideStrategy;
	private ArrayList<Station> listStation;
	
	//Constructors
	
	public Ride(GPSCoordinate beginingPoint, GPSCoordinate finishingPoint, PlanningRideStrategy rideStrategy) {
		super();
		this.beginingPoint = beginingPoint;
		this.finishingPoint = finishingPoint;
		this.rideStrategy = rideStrategy;
	}

	public Ride() {
		super();
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

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
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
	

	public PlanningRideStrategy getRideStrategy() {
		return rideStrategy;
	}

	public void setRideStrategy(PlanningRideStrategy rideStrategy) {
		this.rideStrategy = rideStrategy;
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
	public void haveARide(Bike wishBike) {
		this.listStation = this.rideStrategy.planARide(this.beginingPoint,this.finishingPoint, wishBike);
	}
	

}
