package CLUI;

import java.util.ArrayList;
import java.util.Random;

import Bike.Bike;
import Bike.BikeFactory;
import Bike.ElectricalBike;
import Bike.MechanicalBike;
import Exception.*;
import GPSCoordinate.GPSCoordinate;
import Network.*;
import Network.SortNetwork.SortFactory;
import PlanningRide.PlanningRideFactory;
import PlanningRide.PlanningRideStrategy;
import ReadFile.ReadAText;
import Time.Time;
import User.*;

public abstract class FactoryCommandTerminal {
	
	public static String command(String str) {
				
		String[] words = str.split(" ");
		
		if (words[0].equalsIgnoreCase("exit") && words.length == 1) {
			return "exit";
		}
		else if (words[0].equalsIgnoreCase("scene") && words.length == 1) {
			scenario("ScenarioTXT\\BikeTest");
			return "";
		}
		else if (words[0].equalsIgnoreCase("help") && words.length == 1) {
			help();
			return "";
		}
		else if (words[0].equalsIgnoreCase("setupempty") && words.length == 2) {
			setupEmptyVelibNetwork(words[1]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("setup") && words.length == 2) {
			setupVelibNetwork(words[1]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("setup") && words.length == 6) {
			setupNetworkFromSpecification(words[1], words[2], words[3], words[4], words[5]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("addUser") && words.length == 4) {
			addUser(words[1], words[2], words[3]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("addSlot") && words.length == 3) {
			addSlot(words[1], words[2]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("addSlotName") && words.length == 3) {
			addSlotName(words[1], words[2]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("addBike") && words.length == 4) {
			addBike(words[1], words[2], words[3]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("addBikeName") && words.length == 4) {
			addBikeName(words[1], words[2], words[3]);
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
		else if (words[0].equalsIgnoreCase("rentBike") && words.length == 4) {
			rentABike(words[1], words[2], words[3]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("returnBike") && words.length == 4) {
			returnABike(words[1], words[2], words[3]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("returnBike") && words.length == 3) {
			returnABikeTimeCurrent(words[1], words[2]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("displayStation") && words.length == 3) {
			displayStation(words[1], words[2]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("displayUser") && words.length == 3) {
			displayUser(words[1], words[2]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("display") && words.length == 2) {
			displayNetwork(words[1]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("sortStation") && words.length == 3) {
			sortStation(words[1], words[2]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("sortStation") && words.length == 5) {
			sortStationMinMax(words[1], words[2], words[3], words[4]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("scenario") && words.length == 2) {
			scenario(words[1]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("addtime") && words.length == 2) {
			addTime(words[1]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("havearide") && words.length == 9) {
			haveARide(words[1], words[2], words[3], words[4], words[5], words[6], words[7], words[8]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("addstation") && words.length == 7) {
			addStation(words[1], words[2], words[3], words[4], words[5], words[6]);
			return "";
		}
		else if (words[0].equalsIgnoreCase("") && words.length == 1) {
			return "";
		}
		else {
			System.out.println("Wrong command ! \n"
					+ "A problem has occurred retry your command\n");
			return "";
		}
		
	}
	
	private static void help() {
		System.out.println("Hi, you have for asked help so I am here. You can see below all the available commands and the required arguments\n\n");
		System.out.println("          setupempty <velibnetworkName>\n"
				+ "To create a myVelib network with a given name and nothing else. You just have a town, a system.\n");
		System.out.println("          setup <velibnetworkName>\n"
				+ "To create a myVelib network with a given name and consisting of 10 standard stations each of which has 10 parking slots"
				+ "and such that stations are arranged on a square grid with a 4km width. Initially 75% of the parking slots are occupied,\n"
				+ "with 70% of mechanical bikes and 30% of electrical bikes randomly distributed over the 10 stations.\n");
		System.out.println("          setup <name> <nstations> <nslots> <sidearea> <nbikes> \n"
				+ "To create a myVelib network with a given name and consisting of nstations standard stations each of which has nslots"
				+ " parking slots and such that the stations are arranged on a square grid whose side is "
				+ "sidearea and initially populated with 0.7*nbikes mechanical bikes and 0.3*nbikes electrical bikes randomly distributed over the nstations stations.\n");
		System.out.println("          addStation <statusStation> <xPosition> <yPosition> <stationName> <velibNetwork> <kindStation>\n"
				+ "To add a ceratin station with a name stationName, in a network velibNetwork, at a certain position (xPosition, yPosition) with a "
				+ "certain availibility statusStation and a certain characteristic kindStation.\n");
		System.out.println("          addUser <userName> <cardType> <velibnetworkName>\n"
				+ "To add a user with name userName and card cardType (i.e. 'none' if the user has no card) to a myVelib network velibnetworkName.\n");
		System.out.println("          addSlot <stationID> <quantityOfSlots>\n"
				+ "To add quantityOfSlots slots, to a station with the ID stationID.\n");
		System.out.println("          addSlotName <stationName> <quantityOfSlots>\n"
				+ "To add quantityOfSlots slots, at a station with the name stationName.\n");
		System.out.println("          addBike <stationID> <kindBike> <quantityOfBikes>\n"
				+ "To add a certain bike kindBike with a quantity quantityOfBikes, at a station with the ID stationID.\n");
		System.out.println("          addBike <stationName> <kindBike> <quantityOfBikes>\n"
				+ "To add a certain bike kindBike with a quantity quantityOfBikes, at a station with the name stationName.\n");
		System.out.println("          offline <velibnetworkName> <stationID>\n"
				+ "To put offline the station stationID of the myVelib network velibnetworkName.\n");
		System.out.println("          online <velibnetworkName> <stationID>\n"
				+ "To put online the station stationID of the myVelib network velibnetworkName.\n");
		System.out.println("          rentBike <userID> <stationID>\n"
				+ "To let the user userID rent a mechanical bike from the station stationID (if no bikes are available should behave accordingly).\n");
		System.out.println("          rentBike <userID> <stationID> <kindBike>\n"
				+ "To let the user userID rent a bike (kind chosen by kindBike) from the station stationID (if no bikes are available should behave accordingly).\n");
		System.out.println("          returnBike <userID> <stationID> <time>\n"
				+ "To let the user userID return a bike to the station stationID at a given instant of time time (if no parking bay is "
				+ "available should behave accordingly). This command should display the cost of the rent.\n");
		System.out.println("          returnBike <userID> <stationID>\n"
				+ "To let the user userID return a bike to the station stationID at the current time (if no parking bay is "
				+ "available should behave accordingly). This command should display the cost of the rent.\n");
		System.out.println("          displayStation <velibnetworkName> <stationID>\n"
				+ "To display the statistics (as of Section 2.4) of the station stationID of a myVelib network velibnetwork.\n");
		System.out.println("          displayUser <velibnetworkName> <userID>\n"
				+ "To display the statistics (as of Section 2.4) of the user stationID of a myVelib network velibnetwork.\n");
		System.out.println("          sortStation <velibnetworkName> <sortpolicy>\n"
				+ "To display the stations in increasing order w.r.t. to the sorting policy (as of Section 2.4) of user sortpolicy.\n");
		System.out.println("          sortStation <velibnetworkName> <sortpolicy> <minTime> <maxTime>\n"
				+ "To display the stations in increasing order w.r.t. to the sorting policy (as of Section 2.4) of user sortpolicy taking into account "
				+ "the results between minTime and maxTime.\n");
		System.out.println("          display <velibnetworkName>\n"
				+ "To display the entire status (stations, parking bays, users) of a myVelib network velibnetworkName.\n");
		System.out.println("          haveARide <xFirst> <yFirst> <xSecond> <ySecond> <rideStrategy> <kindBikeWish> <velibNetwork> <userID>\n"
				+ "To let a user have a ride in a certain network, with a certain strategy, a certain wish bike, and between two points : the "
				+ "first (begining) and the second (finishing). Each point has two coordinates x and y.\n");
		System.out.println("          scenario <fileName>\n"
				+ "To launch a scenario from a text file by giving his name if it has been upload in the folder scenario (for example ScenarioTXT\\StationFullScenario), else you have to indicate the "
				+ "complete path.\n");
		
	}
	
	public static void setupVelibNetwork(String name) {
		
		FactoryCommandTerminal.setupNetworkFromSpecification(name, "10", "10", "4", "75");
		
	}
	
	public static void setupEmptyVelibNetwork(String name) {
		
		FactoryCommandTerminal.setupNetworkFromSpecification(name, "0", "0", "0", "0");
		
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
			    Bike nextBike = new MechanicalBike();
			    int isElectrical = new Random().nextInt(10);
			    if(isElectrical >= 7) {
			    	nextBike = new ElectricalBike();
			    }
			    if (net.getStations().get(randomNum).addBikeWithReturnInformation(nextBike)) {
			    	totalBikes++;
			    }
			    
			    boolean flag = true;
			    for (Station stat : net.getStations()) {
					if (!stat.isFull()) {
						flag = false;
						break;
					}
				}
			    
			    if (flag && totalBikes != nBikes1) {
			    	System.err.println("Too much bike and not enough parking slots, be careful and retry");
			    	Network.entireNet.remove(net);
			    	break;
			    	}
			}
			
			System.out.println("Done correctly\n");
			
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		}
		
	}
	
	public static void addStation( String statusStation, String xPosition, String yPosition, String stationName, String velibNetwork, String kindStation) {
		
		try {
			
			Network net = Network.findANetworkFromName(velibNetwork);
			
			double xPos = Double.parseDouble(xPosition);
			double yPos = Double.parseDouble(yPosition);
			GPSCoordinate location = new GPSCoordinate(xPos, yPos);
			
			boolean status = Boolean.parseBoolean(statusStation);
			
			Station stat = StationFactory.createAStation(status, location, stationName, kindStation);
			net.addStation(stat);
			
			System.out.println("Done correctly\n");
			
		} catch (StationFactoryException e) {
			
		} catch (NetworkFactoryException e) {
			
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		}
		
	}
	
	public static void addUser(String name, String cardType, String velibNetwork) {
				
		try {
			UserFactory.createUser(name,cardType,velibNetwork);	
			System.out.println("Done correctly\n");
		} catch (UserFactoryException e) {
			
		} catch (NetworkFactoryException e) {
			
		} 
	}
	
	public static void addSlot(String stationID, String quantity) {
		
		try {
			
			int quant = Integer.parseInt(quantity);
			int IDstation = Integer.parseInt(stationID);
			
			Station station = Station.findAStationFromID(IDstation);
			
			station.addNParkingSlot(quant);
			
		} catch (StationFindException e) {
			
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		} 
	}
	
	public static void addSlotName(String stationName, String quantity) {
		
		try {
			
			int quant = Integer.parseInt(quantity);
			
			Station station = Station.findAStationFromName(stationName);
			
			station.addNParkingSlot(quant);
			
		} catch (StationFindException e) {
			
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		} 
	}
	
	public static void addBike(String stationID, String kindBike, String quantity) {
		
		try {
			
			int quant = Integer.parseInt(quantity);
			int IDstation = Integer.parseInt(stationID);
			
			Station station = Station.findAStationFromID(IDstation);
			
			int totalBike = 0;
			
			while (totalBike != quant) {
				Bike bike = BikeFactory.createABike(kindBike);
				if(station.addBikeWithReturnInformation(bike)) {
					totalBike++;
				}
				else {
					System.err.println("The station is full, stop adding bikes, or add slots before");
					break;
				}
			}
			
			System.out.println("Done correctly\n");
			
		} catch (StationFindException e) {
			
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		} catch (BikeFactoryException e) {
			
		} 
	}
	
	public static void addBikeName(String stationName, String kindBike, String quantity) {
		
		try {
						
			Station station = Station.findAStationFromName(stationName);
			
			addBike(String.valueOf(station.getID()), kindBike, quantity);
			
		} catch (StationFindException e) {
			
		}
	}
	
	public static void goOffline(String velibNet, String IDStation) {
		
		try {
			// just to know the existence ... 
			Network.findANetworkFromName(velibNet);
			int ID = Integer.parseInt(IDStation);
			Station stat = Station.findAStationFromID(ID);
			stat.goOffline();;				
			System.out.println("Done correctly\n");
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		} catch (StationFindException e) {
			
		} catch (NetworkFactoryException e) {
			
		}
		
	}
	
	public static void goOnline(String velibNet, String IDStation) {
		
		try {
			// just to know the existence ... 
			Network.findANetworkFromName(velibNet);
			int ID = Integer.parseInt(IDStation);
			Station stat = Station.findAStationFromID(ID);
			stat.setStatus(true);		
			System.out.println("Done correctly\n");
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		} catch (StationFindException e) {
			
		} catch (NetworkFactoryException e) {
			
		}
		
	}
	
	public static void rentABike(String userID, String stationID) {
		
		rentABike(userID, stationID, "mechanical");
	}
	
	public static void rentABike(String userID, String stationID, String bike) {
		
		try {
			int statID = Integer.parseInt(stationID);
			Station stat = Station.findAStationFromID(statID);
			
			int userID1 = Integer.parseInt(userID);
			User user = User.findAUserFromID(userID1);
			
			Bike bike1 = BikeFactory.createABike(bike);
			
			stat.takeBike(bike1, user);
			
			System.out.println("Done correctly\n");
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		} catch (UserFindFactoryException e) {
			
		} catch (StationFindException e) {
			
		} catch (BikeFactoryException e) {
			
		}
	}
	
	public static void returnABikeTimeCurrent(String userID, String stationID) {
		
		returnABike(userID, stationID, String.valueOf(Time.getTimeInMinuteSinceCreation()));
		
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
			boolean flag = stat.giveBackBike(user);
			Time.setTimeInMinuteSinceCreation(timeOfSimulation);
			
			if(flag) {
				System.out.println("The price of the course for " + user.getName() + " going from " + 
			user.getAllRide().get(user.getAllRide().size()-1).getListStation().get(0).getName() + 
			" to " + user.getAllRide().get(user.getAllRide().size()-1).getListStation().get(1).getName() + " was : " + 
			user.getAllRide().get(user.getAllRide().size() -1).getPrice());
			}
			else {
				System.out.println("Impossible station full");
			}
			
			System.out.println("Done correctly\n");
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		} catch (UserFindFactoryException e) {
			
		} catch (StationFindException e) {
			
		}
	}
	
	public static void displayStation(String velibNet, String stationID) {
		
		try {
			Network.findANetworkFromName(velibNet);
			
			int ID = Integer.parseInt(stationID);
			Station stat = Station.findAStationFromID(ID);
			
			System.out.println(stat.toStringBalance());
			
			System.out.println("Done correctly\n");
		} catch (NetworkFactoryException e) {
			
		} catch (StationFindException e) {
			
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		}
				
	}
	
	public static void displayUser(String velibNet, String userID) {
		
		try {
			Network.findANetworkFromName(velibNet);
			
			int ID = Integer.parseInt(userID);
			User user = User.findAUserFromID(ID);
			
			System.out.println(user.toStringBalance());
			
			System.out.println("Done correctly\n");
		} catch (NetworkFactoryException e) {
			
		} catch (UserFindFactoryException e) {
			
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		}
		
	}
	
	public static void displayNetwork(String velibNet) {
		
		try {
			Network net = Network.findANetworkFromName(velibNet);
			
			System.out.println(net.getStations());
			System.out.println(net.getUsers());

			System.out.println("Done correctly\n");
			
		} catch (NetworkFactoryException e) {
			
		}
		
	}
	
	private static void sortStation(String velibNetwork, String kindOfSort) {
		
		
		sortStationMinMax(velibNetwork, kindOfSort, "0" , String.valueOf(Time.getTimeInMinuteSinceCreation()));
		
	}
	
	private static void sortStationMinMax(String velibNetwork, String kindOfSort, String minTime, String maxTime) {
		
		try {
			
			Network net = Network.findANetworkFromName(velibNetwork);
			int min = Integer.parseInt(minTime);
			int max = Integer.parseInt(maxTime);
			
			ArrayList<Station> ordered = SortFactory.sortAList(kindOfSort, min, max, net);
			
			for(int i=0; i < ordered.size(); i++){
			    System.out.println(ordered.get(i).toStringBalance());
			} 
			
			System.out.println("Done correctly\n");
			
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		} catch (SortFactoryException e) {

		} catch (NetworkFactoryException e) {

		}
				
	}
	

	private static void scenario(String string) {
		
		ReadAText.readAText(string);	
		
	}
		
	private static void haveARide(String xFirst, String yFirst, String xSecond, String ySecond, String rideStrategy, String kindBikeWish, String velibNetwork, String userID) {
		
		try {
			double xFirst1 = Double.parseDouble(xFirst);
			double yFirst1 = Double.parseDouble(yFirst);
			GPSCoordinate beginingPoint = new GPSCoordinate(xFirst1, yFirst1);
			
			double xSecond1 = Double.parseDouble(xSecond);
			double ySecond1 = Double.parseDouble(xSecond);
			GPSCoordinate finishingPoint = new GPSCoordinate(xSecond1, ySecond1);
			
			PlanningRideStrategy strategy = PlanningRideFactory.createAStrategy(rideStrategy);
			
			Bike bike = BikeFactory.createABike(kindBikeWish);
			
			Network net = Network.findANetworkFromName(velibNetwork);
			
			int ID = Integer.parseInt(userID);
			User user = User.findAUserFromID(ID);
			
			user.takeARide(beginingPoint, finishingPoint, strategy, bike, net);
			
			System.out.println("Done correctly\n");
			
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		} catch (UserFindFactoryException e) {
			
		} catch (NetworkFactoryException e) {
			
		} catch (BikeFactoryException e) {
			
		} catch (PlanningRideFactoryException e) {
			
		}
		
	}
	
	private static void addTime(String time) {
		
		try {
			int addTime = Integer.parseInt(time);
			Time.addTime(addTime);
			
			System.out.println("Done correctly\n");
		} catch (NumberFormatException e) {
			System.out.println("An argument in the command is not correct, please try it again, or please read the Javadoc.\n");
		}
	}
}
