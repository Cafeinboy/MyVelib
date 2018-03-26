	package User;

import Card.*;
import Exception.UserFactoryException;

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
	 */
	public static User createUser(String name, String card ) throws UserFactoryException {
		
		if (card == null) {
			return null;
			}
		
		else if(card.equalsIgnoreCase("NoCard")) {
				return new User(name, new NoCard());
			} 
		
		else if(card.equalsIgnoreCase("No Card")) {
				return new User(name, new NoCard());
			} 
		
		else if(card.equalsIgnoreCase("Vlibre")) {
				return new User(name, new VlibreCard());
			}
		
		else if(card.equalsIgnoreCase("V libre")) {
				return new User(name, new VlibreCard());
			}
		
		else if(card.equalsIgnoreCase("Vmax")) {
				return new User(name, new VmaxCard());
			}
		
		else if(card.equalsIgnoreCase("V max")) {
				return new User(name, new VmaxCard());
			}
		
		throw new UserFactoryException();		
	}

}
