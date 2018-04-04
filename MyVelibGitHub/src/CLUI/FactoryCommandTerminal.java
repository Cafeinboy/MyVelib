package CLUI;

import java.util.Random;

import Bike.MechanicalBike;
import Exception.*;
import GPSCoordinate.GPSCoordinate;
import Network.*;
import Time.Time;
import User.*;

public abstract class FactoryCommandTerminal {
	
	public static String command(String str) {
				
		String[] words = str.split(" ");
		
		if (words[0].equalsIgnoreCase("exit") && words.length == 1) {
			return "exit";
		}
		else if (words[0].equalsIgnoreCase("help") && words.length == 1) {
			help();
			return "";
		}else if (words[0].equalsIgnoreCase("setup") && words.length == 2) {
			setupVelibNetwork(words[1]);
			return "";
		}else if (words[0].equalsIgnoreCase("setup") && words.length == 6) {
			setupNetworkFromSpecification(words[1], words[2], words[3], words[4], words[5]);
			return "";
		}else if (words[0].equalsIgnoreCase("addUser") && words.length == 4) {
			addUser(words[1], words[2], words[3]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("offline") && words.length == 3) {
			goOffline(words[1], words[2]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("online") && words.length == 3) {
			goOnline(words[1], words[2]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("rentBike") && words.length == 3) {
			rentABike(words[1], words[2]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("returnBike") && words.length == 4) {
			returnABike(words[1], words[2], words[3]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("displayStation") && words.length == 3) {
			displayStation();
			return "";
		}
		else if (words[0].equalsIgnoreCase("displayUser") && words.length == 3) {
			displayUser();
			return "";
		}
		else if (words[0].equalsIgnoreCase("display") && words.length == 2) {
			displayNetwork();
			return "";
		}
		else if (words[0].equalsIgnoreCase("sortStation") && words.length == 3) {
			displayNetwork();
			return "";
		}
		else {
			System.out.println("Wrong command ! \n"
					+ "A problem has occurred retry your command\n\n");
			return "";
		}
		
	}
	
	public static void help() {
		System.out.println("Hi, you have asked help so I am here. You can see bellow all the commad available and the arguments required");
		System.out.println("          setup <velibnetworkName>\n"
				+ "To create a myVelib network with given name and consisting of 10 standard stations each of which has 10 parking slots"
				+ "and such that stations are arranged on a square grid whose of side 4km and initially populated with a 75%"
				+ " mechanical bikes randomly distributed over the 10 stations.\n");
		System.out.println("          setup <name> <nstations> <nslots> <sidearea> <nbikes> \n"
				+ "To create a myVelib network with given name and consisting of nstations standard stations each of which has nslots"
				+ " parking slots and such that stations are arranged on a square grid whose of side "
				+ "sidearea and initially populated with a nbikes mechanical bikes randomly distributed over the nstations stations.\n");
		System.out.println("          addUser <userName> <cardType> <velibnetworkName>\n"
				+ "To add a user with name userName and card cardType (i.e. ‘‘none’’ if the user has no card) to a myVelib network velibnetworkName.\n");
		System.out.println("          offline <velibnetworkName> <stationID>\n"
				+ "To put offline the station stationID of the myVelib network velibnetworkName.\n");
		System.out.println("          online <velibnetworkName> <stationID>\n"
				+ "To put online the station stationID of the myVelib network velibnetworkName.\n");
		System.out.println("          rentBike <userID> <stationID>\n"
				+ "To let the user userID renting a bike from station stationID (if no bikes are available should behave accordingly).\n");
		System.out.println("          returnBike <userID> <stationID> <time>\n"
				+ "To let the user userID returning a bike to station stationID at a given instant of time time (if no parking bay is "
				+ "available should behave accordingly). This command should display the cost of the rent.\n");
		System.out.println("          displayStation <velibnetworkName> <stationID>\n"
				+ "To display the statistics (as of Section 2.4) of station stationID of a myVelib network velibnetwork.\n");
		System.out.println("          displayUser <velibnetworkName> <userID>\n"
				+ "To display the statistics (as of Section 2.4) of user stationID of a myVelib network velibnetwork.\n");
		System.out.println("          sortStation <velibnetworkName> <sortpolicy>\n"
				+ "To display the stations in increasing order w.r.t. to the sorting policy (as of Section 2.4) of user sortpolicy.\n");
		System.out.println("          display <velibnetworkName>\n"
				+ "To display the entire status (stations, parking bays, users) of an a myVelib network velibnetworkName.\n");
		
	}
	
	public static void setupVelibNetwork(String name) {
		
		FactoryCommandTerminal.setupNetworkFromSpecification(name, "10", "10", "4", "75");
		
	}
	
	public static void setupNetworkFromSpecification(String name, String nStations, String nSlots, String sideSquare, String nBikes) {
		
		try {
			
			int nStations1 = Integer.parseInt(nStations);
			int nSlots1 = Integer.parseInt(nSlots);
			int sideSquare1 = Integer.parseInt(sideSquare);
			int nBikes1 = Integer.parseInt(nBikes);
			double step = sideSquare1/(Math.sqrt((double) nSlots1)-1);
			double x = 0;
			double y = 0;
			
			//Creating network and all stations required
			Network net = new Network(name);
			for (int i = 0; i < nStations1; i++) {
				Station statInProgress = new StandardStation(true, new GPSCoordinate());
				net.addStation(statInProgress);
				statInProgress.addNParkingSlot(nSlots1);
				if ( (int) x/sideSquare1 == 1) {
					y = y + step;
					x = x % sideSquare1;
				}
				else {
					x = x + step;
				}				
			}
			
			//Distributing the bikes on the system randomly
			int totalBikes = 0;
			while (totalBikes != nBikes1) {
			    int randomNum = new Random().nextInt(nStations1);
			    if (net.getStations().get(randomNum).addBikeWithReturnInformation(new MechanicalBike())) {
			    	totalBikes++;
			    }
			}
			
			System.out.println("Done correctly\n");
			
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		}
		
	}
	
	public static void addUser(String name, String cardType, String velibNetwork) {
				
		try {
			User user = UserFactory.createUser(name,cardType,velibNetwork);	
			System.out.println("Done correctly\n");
		} catch (UserFactoryException e) {
		} catch (NetworkFactoryException e) {
		} 
	}
	
	public static void goOffline(String velibNet, String IDStation) {
		
		try {
			// just to know the existence ... 
			Network net = Network.findANetworkFromName(velibNet);
			int ID = Integer.parseInt(IDStation);
			Station stat = Station.findAStationFromID(ID);
			stat.goOffline();;				
			System.out.println("Done correctly\n");
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		} catch (StationFactoryException e) {
			
		} catch (NetworkFactoryException e) {
			
		}
		
	}
	
	public static void goOnline(String velibNet, String IDStation) {
		
		try {
			// just to know the existence ... 
			Network net = Network.findANetworkFromName(velibNet);
			int ID = Integer.parseInt(IDStation);
			Station stat = Station.findAStationFromID(ID);
			stat.setStatus(true);		
			System.out.println("Done correctly\n");
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		} catch (StationFactoryException e) {
			
		} catch (NetworkFactoryException e) {
			
		}
		
	}
	
	public static void rentABike(String userID, String stationID) {
		
		try {
			int statID = Integer.parseInt(stationID);
			Station stat = Station.findAStationFromID(statID);
			
			int userID1 = Integer.parseInt(userID);
			User user = User.findAUserFromID(userID1);
			
			stat.takeBike(new MechanicalBike(), user);
			
			System.out.println("Done correctly\n");
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		} catch (UserFindFactoryException e) {
			
		} catch (StationFactoryException e) {
			
		}
	}
	
	public static void returnABike(String userID, String stationID, String time) {
		
		try {
			int statID = Integer.parseInt(stationID);
			Station stat = Station.findAStationFromID(statID);
			
			int userID1 = Integer.parseInt(userID);
			User user = User.findAUserFromID(userID1);
			
			int time1 = Integer.parseInt(time);
			
			int timeOfSimulation = Time.getTimeInMinuteSinceCreation();
			Time.setTimeInMinuteSinceCreation(time1);
			stat.giveBackBike(user);
			Time.setTimeInMinuteSinceCreation(timeOfSimulation);
			
			System.out.println("The price of the course was : " + user.getAllRide().get(user.getAllRide().size() -1).getPrice());
			
			System.out.println("Done correctly\n");
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		} catch (UserFindFactoryException e) {
			
		} catch (StationFactoryException e) {
			
		}
	}
	
	public static void displayStation() {
		
		System.out.println("Not yet implemented");
		
	}
	
	public static void displayUser() {
		
		System.out.println("Not yet implemented");
		
	}
	
	public static void displayNetwork() {
		
		System.out.println("Not yet implemented");
		
	}
}
