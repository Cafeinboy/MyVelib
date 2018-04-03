package CLUI;

public abstract class FactoryCommandTerminal {
	
	public static String command(String str) {
		
		String[] words = str.split(" ");
		
		if (words[0].equalsIgnoreCase("exit")) {
			return "exit";
		}
		else if (words[0].equalsIgnoreCase("help")) {
			help();
			return null;
		}else if (words[0].equalsIgnoreCase("addUser")) {
			addUser();
			return null;
		}
		else if (words[0].equalsIgnoreCase("offline")) {
			goOffline();
			return null;
		}
		else if (words[0].equalsIgnoreCase("online")) {
			goOnline();
			return null;
		}
		else if (words[0].equalsIgnoreCase("rentBike")) {
			rentABike();
			return null;
		}
		else if (words[0].equalsIgnoreCase("returnBike")) {
			returnABike();
			return null;
		}
		else if (words[0].equalsIgnoreCase("displayStation")) {
			displayStation();
			return null;
		}
		else if (words[0].equalsIgnoreCase("displayUser")) {
			displayUser();
			return null;
		}
		else if (words[0].equalsIgnoreCase("display")) {
			displayNetwork();
			return null;
		}
		else if (words[0].equalsIgnoreCase("sortStation")) {
			displayNetwork();
			return null;
		}
		else {
			System.out.println("A problem has occurred retry your command");
			return null;
		}
		
	}
	
	public static void help() {
		System.out.println("Hi, you have asked help so I am here. You can see bellow all the commad available and the arguments required");
		System.out.println("setup <velibnetworkName>\n"
				+ "To create a myVelib network with given name and consisting of 10 stations each of which has 10 parking slots"
				+ "and such that stations are arranged on a square grid whose of side 4km and initially populated with a 75%"
				+ " bikes randomly distributed over the 10 stations.\n");
		System.out.println("setup <name> <nstations> <nslots> <sidearea> <nbikes> \n"
				+ "To create a myVelib network with given name and consisting of nstations stations each of which has nslots"
				+ " parking slots and such that stations are arranged on a square grid whose of side "
				+ "sidearea and initially populated with a nbikes bikes randomly distributed over the nstations stations.\n");
		System.out.println("addUser <userName> <cardType> <velibnetworkName>\n"
				+ "To add a user with name userName and card cardType (i.e. ‘‘none’’ if the user has no card) to a myVelib network velibnetworkName.\n");
		System.out.println("offline <velibnetworkName> <stationID>\n"
				+ "To put offline the station stationID of the myVelib network velibnetworkName.\n");
		System.out.println("online <velibnetworkName> <stationID>\n"
				+ "To put online the station stationID of the myVelib network velibnetworkName.\n");
		System.out.println("rentBike <userID> <stationID>\n"
				+ "To let the user userID renting a bike from station stationID (if no bikes are available should behave accordingly).\n");
		System.out.println("returnBike <userID> <stationID> <time>\n"
				+ "To let the user userID returning a bike to station stationID at a given instant of time time (if no parking bay is "
				+ "available should behave accordingly). This command should display the cost of the rent.\n");
		System.out.println("displayStation <velibnetworkName> <stationID>\n"
				+ "To display the statistics (as of Section 2.4) of station stationID of a myVelib network velibnetwork.\n");
		System.out.println("displayUser <velibnetworkName> <userID>\n"
				+ "To display the statistics (as of Section	2.4) of user stationID of a myVelib network velibnetwork.\n");
		System.out.println("sortStation <velibnetworkName> <sortpolicy>\n"
				+ "To display the stations in increasing order w.r.t. to the sorting policy (as of Section 2.4) of user sortpolicy.\n");
		System.out.println("display <velibnetworkName>\n"
				+ "To display the entire status (stations, parking bays, users) of an a myVelib network velibnetworkName.\n");
		
	}
	
	public static void setupVelibNetwork() {
		
	}
	
	public static void setupNetworkFromSpecification() {
		
	}
	
	public static void addUser() {
		
	}
	
	public static void goOffline() {
		
	}
	
	public static void goOnline() {
		
	}
	
	public static void rentABike() {
		
	}
	
	public static void returnABike() {
		
	}
	
	public static void displayStation() {
		
	}
	
	public static void displayUser() {
		
	}
	
	public static void displayNetwork() {
		
	}
}
