package User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import Time.Time;
import Bike.*;
import Card.*;
import Exception.UserFindFactoryException;
import GPSCoordinate.*;
import Network.Network;
import Network.PlusStation;
import Network.Station;
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
	
	private static ArrayList<User> listOfUsers = new ArrayList<User>();
	
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
		
		listOfUsers.add(this);
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
		
		listOfUsers.add(this);
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
		
		listOfUsers.add(this);
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
	
	public void setLocation(double x, double y) {
		this.location = new GPSCoordinate(x, y);
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
	public void takeARide(GPSCoordinate beginingPoint, GPSCoordinate finishingPoint, PlanningRideStrategy rideStrategy, Bike wishBike, Network net) {
		if (this.ride.getBike() == null) {			
			this.ride = new Ride(beginingPoint, finishingPoint, rideStrategy, net);
			this.ride.setWishBike(wishBike);
			this.ride.haveARide(wishBike);
			this.ride.getListStation().get(1).addUser(this);
		}
		else {
			this.ride.getListStation().get(1).removeUser(this);
			
			Ride newRide = new Ride(beginingPoint, finishingPoint, rideStrategy, net);
			newRide.haveARide(wishBike);

			this.ride.setFinishingPoint(finishingPoint);
			this.ride.setRideStrategy(rideStrategy);
			this.ride.changeFinishingStation(newRide.getListStation().get(1));
			this.ride.getListStation().get(1).addUser(this);
		}
	}

	//Attention il va falloir appeler la fonction pour recalculer un trajet 
	//l�-dedans
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			String message = (String) arg;
			System.out.println(message);
			Ride oldRide = this.getRide();
			System.out.println(oldRide);
			Ride newRide = new Ride(oldRide.getListStation().get(0).getCoordinates(), oldRide.getFinishingPoint(), oldRide.getRideStrategy(), oldRide.getListStation().get(1).knowHisNetwork());
			newRide.haveARide(oldRide.getWishBike());
			this.getRide().setListStation(new ArrayList<Station>(Arrays.asList(oldRide.getListStation().get(0), newRide.getListStation().get(1))));
			System.out.println("Your new destination station is " + this.getRide().getListStation().get(1).getName());
		}
		else {
			System.err.println("Wrong type of argument");
		}
	}
	
	public static User findAUserFromID(int userID) throws UserFindFactoryException {
				
		User user = null;
		
		for (User userSearch : listOfUsers) {
			if (userSearch.getID() == userID) {
				user = userSearch;
				break;
			}
		}
		
		if (user == null) {
			throw new UserFindFactoryException();
		}
		
		return user;
		
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
	 * This function permit to add the ride in the historic of the user and so, that permit to compute different kinds of data after for the user.
	 * This function put an end for the ride.
	 */
	public void putAnEndToTheRide(Station finishingStation) {
		int price = this.getPriceOfTheRide();
		this.ride.setPrice(price);
		
		this.ride.setEndingTime(Time.getTimeInMinuteSinceCreation());
		
		this.ride.getListStation().get(1).removeUser(this);
		this.ride.changeFinishingStation(finishingStation);
		
		this.allRide.add(this.ride);
		this.ride = new Ride();
	}
	

	/**
	 * This method returns an ArrayList with the total number of ride, the total time on a bike, the total charges, and the total time earned since the beginning for a client.
	 * That take into account the ride in progress.
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
		if (this.ride.getNet() != null) {
			numberOfRides++;
			totalTimeOnBike+= Time.getTimeInMinuteSinceCreation()-ride.getBeginingTime();
		}
		return new ArrayList<Integer>(Arrays.asList(numberOfRides,totalTimeOnBike,totalCharges,totalEarnCredit));
	}

	@Override
	public String toString() {
		return "User : " + this.name + "\n";
	}
	
	public String toStringBalance() {
		ArrayList<Integer> list = this.balance();
		return "User : " + this.name + "\n     Number Of Rides : " + list.get(0) + "\n     Total Time On Bike : " + list.get(1)
			+ "\n     Total Charges : " + list.get(2) + "\n     Total Earn Credit : " + list.get(3) + "\n";
	}
	
	
				
}
