package Network;

import java.util.ArrayList;

import GPSCoordinate.GPSCoordinate;
import User.User;

public class Network {
	
	//Attributes
	
	private static ArrayList<Station> stations = new ArrayList<Station>();
	private static ArrayList<User> users = new ArrayList<User>();
	
	
	//Constructor
	
	/**
	 * The constructor is private so that there is only one isntance
	 * of a Network object
	 */
	
	private Network() {
		super();
			Network.stations = new ArrayList<Station>();
			Network.users = new ArrayList<User>();
		}
	
	
	//Getters and setters
	
	/**
	 * Returns the Plus stations from a Array of stations
	 * If you want all the plus stations from the network you should write :
	 * Network.getPlusStations(Netwotk.getStations())
	 * 
	 * @param listStation
	 * @return
	 */
	
	public static ArrayList<Station> getPlusStations(ArrayList<Station> listStation) {
		ArrayList<Station> listPlus = new ArrayList<Station>();
		for (Station station : listStation) {
			if (station instanceof PlusStation) {
				listPlus.add(station);
			}
		}
		return listPlus;
	}
	
	/**
	 * Returns the Standard stations from a Array of stations
	 * If you want all the standard stations from the network you should write :
	 * Network.getStandardStations(Netwotk.getStations())
	 * 
	 * @param listStation
	 * @return
	 */
	
	public static ArrayList<Station> getNonPlusStations(ArrayList<Station> listStation) {
		ArrayList<Station> listNonPlus = new ArrayList<Station>();
		for (Station station : listStation) {
			if (station instanceof StandardStation) {
				listNonPlus.add(station);
			}
		}
		return listNonPlus;
	}
	


	public static ArrayList<Station> getStations() {
		return stations;
	}

	public static ArrayList<User> getUsers() {
		return users;
	}

	public static void addStation(Station station) {
		Network.stations.add(station);
	}
	
	public static void addUser(User user) {
		Network.users.add(user);
	}
	
	
	//Methods

	
	/**
	 * Returns the available (not offline) stations in order to compute a ride
	 * @return
	 */
	public static ArrayList<Station> getAvailableStations() {
		ArrayList<Station> availableStations = new ArrayList<Station>();
		for(Station station : Network.stations) {
			if(station.isOnline()) {
				availableStations.add(station);
			}
		}
		return availableStations;
	}

	/**
	 * Returns the closest station to a point, will be used to calculate 
	 * @param a
	 * @return
	 */
	public static Station getClosestStationTo(GPSCoordinate a) {
		Station closestStation = Network.getAvailableStations().get(0);
		double distance = GPSCoordinate.distance(closestStation.getCoordinates(),a);
		for(Station station : Network.stations) {
			double tempdist = GPSCoordinate.distance(station.getCoordinates(),a);
			if(tempdist<distance) {
				closestStation = station;
				distance = tempdist;
			}
		}
		return closestStation;
	}
	
	
	/**
	 * The main method creates an instance of a Network, and it is the unique
	 * instance that will always be used
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		Network network = new Network();
		
	}
	

}
