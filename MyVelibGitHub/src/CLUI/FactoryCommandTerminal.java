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
		System.out.println();
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
