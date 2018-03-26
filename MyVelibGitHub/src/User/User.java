package User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import Bike.*;
import Card.*;
import GPSCoordinate.*;
import Network.PlusStation;
import PlanningRide.*;
import Ride.*;


public class User implements Observer, Functionnality {
	
	//Attributes
	
	private String name;
	private int ID;
	private GPSCoordinate location;
	private Card card;
	private static int numberID = 1;
	private Ride ride;
	private ArrayList<Ride> allRide;
	
	//Constructors
	
	public User() {
		super();
		this.name = "";
		this.ID = numberID;
		numberID++;
		this.location = new GPSCoordinate();
		this.card = new NoCard();
		this.ride = new Ride();
		this.allRide = new ArrayList<Ride>();
	}
	
	public User(String name, Card card) {
		super();
		this.name = name;
		this.card = card;
		this.location = new GPSCoordinate();
		this.ID = numberID;
		numberID++;
		this.ride = new Ride();
		this.allRide = new ArrayList<Ride>();
	}

	public User(String name, GPSCoordinate location, Card card) {
		super();
		this.name = name;
		this.location = location;
		this.card = card;
		this.ID = numberID;
		numberID++;
		this.ride = new Ride();
		this.allRide = new ArrayList<Ride>();
	}
	
	//Methods

	public String getName() {
		return name;
	}

	public int getID() {
		return ID;
	}

	public GPSCoordinate getLocation() {
		return location;
	}

	public Card getCard() {
		return card;
	}
	
	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}
	
	public ArrayList<Ride> getAllRide() {
		return allRide;
	}

	public void setAllRide(ArrayList<Ride> allRide) {
		this.allRide = allRide;
	}
	
	/**
	 * Function to allow a User to ask for a ride
	 * @param beginingPoint
	 * @param finishingPoint
	 * @param rideStrategy
	 * @param bikeType
	 */
	public void takeARide(GPSCoordinate beginingPoint, GPSCoordinate finishingPoint, PlanningRideStrategy rideStrategy, Bike wishBike) {
		this.ride = new Ride(beginingPoint, finishingPoint, rideStrategy);
		this.ride.haveARide(wishBike);
	}

	//Attention il va falloir appeler la fonction pour recalculer un trajet 
	//là-dedans
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			String message = (String) arg;
			System.out.println(message);
		}
		else {
			System.err.println("Wrong type of argument");
		}
	}
	
	/**
	 * Returns the price of the ride, if the user has one
	 */
	
	public int getPriceOfTheRide() {
		if(this.getRide().getBike() == null) {
			System.out.println(this.name + " has no ride");
			return 0;
		}
		int price =  this.ride.getBike().computeTotalPrice(this.getCard());
		this.ride.setPrice(price);
		return price;
	}

	/**
	 * This method returns an ArrayList with the total number of ride, the total time on a bike, the total charges, and the total time earned since the beginning for a client.
	 * @return 
	 * ArrayList(Integer) (total number of ride, the total time on a bike, the total charges, the total time earned)
	 */
	@Override
	public ArrayList<Integer> balance() {
		int numberOfRides = 0;
		int totalTimeOnBike = 0;
		int totalCharges = 0;
		int totalEarnCredit = 0;
		for (Ride ride : allRide) {
			numberOfRides++;
			totalTimeOnBike+= ride.getEndingTime()-ride.getBeginingTime();
			totalCharges+=ride.getPrice();
			if (ride.getListStation().get(1) instanceof PlusStation) {
				totalEarnCredit+=5;
			}
		}
		return new ArrayList<Integer>(Arrays.asList(numberOfRides,totalTimeOnBike,totalCharges,totalEarnCredit));
	}
				
}
