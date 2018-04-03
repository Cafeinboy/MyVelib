	package User;

import Card.*;
import Exception.NetworkFactoryException;
import Exception.UserFactoryException;
import Network.Network;

public class UserFactory {
	
	/**
	 * This function accept two string in entry :
	 * @param name
	 * Which is a String to give the name of the user
	 * @param card
	 * Which permits to know what kind of card the user has. If, he hasn't got card put "NoCard"
	 * @return
	 * This Factory return a User from the specification of the String in input. 
	 * @throws UserFactoryException
	 * Just to say if you have put something not correct in the code. Read again what is said above. 
	 * @throws NetworkFactoryException 
	 */
	public static User createUser(String name, String card , String velibNetwork) throws UserFactoryException, NetworkFactoryException {
		
		Network net = null;
		
		for (Network netSearch : Network.entireNet) {
			if (netSearch.getName().equalsIgnoreCase(velibNetwork)) {
				net = netSearch;
				break;
			}
		}
		
		if (net == null) {
			throw new NetworkFactoryException();
		}
		
		if (card == null) {
			return null;
			}
		
		else if(card.equalsIgnoreCase("NoCard")) {
			User user = new User(name, new NoCard());
			net.addUser(user);
			return user;
			} 
		
		else if(card.equalsIgnoreCase("No Card")) {
			User user = new User(name, new NoCard());
			net.addUser(user);
			return user;
			} 
		
		else if(card.equalsIgnoreCase("Vlibre")) {
			User user = new User(name, new VlibreCard());
			net.addUser(user);
			return user;
			}
		
		else if(card.equalsIgnoreCase("V libre")) {
			User user = new User(name, new VlibreCard());
			net.addUser(user);
			return user;
			}
		
		else if(card.equalsIgnoreCase("Vmax")) {
			User user = new User(name, new VmaxCard());
			net.addUser(user);
			return user;
			}
		
		else if(card.equalsIgnoreCase("V max")) {
			User user = new User(name, new VmaxCard());
			net.addUser(user);
			return user;
			}
		
		throw new UserFactoryException();		
	}

}
