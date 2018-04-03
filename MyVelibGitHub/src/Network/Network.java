package Network;

import java.util.ArrayList;

import GPSCoordinate.GPSCoordinate;
import User.User;

public class Network {
	
	//Attributes
	
	private ArrayList<Station> stations;
	private ArrayList<User> users;
	static ArrayList<Network> entireNet = new ArrayList<Network>();
	private String name;
	
	//Constructor
		
	public Network(String name) {
		super();
			this.stations = new ArrayList<Station>();
			this.users = new ArrayList<User>();
			this.name = name;

			entireNet.add(this);
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
	


	public ArrayList<Station> getStations() {
		return this.stations;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<User> getUsers() {
		return this.users;
	}

	public void addStation(Station station) {
		this.stations.add(station);
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
	
	//Methods

	
	/**
	 * Returns the available (not offline) stations in order to compute a ride
	 * @return
	 */
	public ArrayList<Station> getAvailableStations() {
		ArrayList<Station> availableStations = new ArrayList<Station>();
		for(Station station : this.stations) {
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
	public Station getClosestStationTo(GPSCoordinate a) {
		Station closestStation = this.getAvailableStations().get(0);
		double distance = GPSCoordinate.distance(closestStation.getCoordinates(),a);
		for(Station station : this.stations) {
			double tempdist = GPSCoordinate.distance(station.getCoordinates(),a);
			if(tempdist<distance) {
				closestStation = station;
				distance = tempdist;
			}
		}
		return closestStation;
	}
	
	
}
