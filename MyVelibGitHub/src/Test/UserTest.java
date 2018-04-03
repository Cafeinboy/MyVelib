package Test;

import static org.junit.Assert.*;


import org.junit.Test;

import Card.NoCard;
import Card.VlibreCard;
import Card.VmaxCard;
import Exception.NetworkFactoryException;
import Exception.UserFactoryException;
import GPSCoordinate.GPSCoordinate;
import Network.Network;
import User.User;
import User.UserFactory;

public class UserTest {

	@Test
	public void constructorTest() {
		Object user1 = new User();
		Object user2 = new User("Kevin", new VlibreCard());
		if(user1 instanceof User) {
			User testuser = (User) user1;
			assertEquals(testuser.getName(), "");
			assertEquals(testuser.getID(), 1);
			assertEquals(testuser.getLocation(), new GPSCoordinate());
			assertTrue(testuser.getCard() instanceof NoCard);
			assertNotNull(testuser.getRide());
			assertEquals(testuser.getPriceOfTheRide(), 0);
		}
		else {
			System.err.println("user1 is not a User");
		}
		if(user2 instanceof User) {
			User testuser = (User) user2;
			assertEquals(testuser.getName(), "Kevin");
			assertEquals(testuser.getID(), 2);
			assertEquals(testuser.getLocation(), new GPSCoordinate());
			assertTrue(testuser.getCard() instanceof VlibreCard);
			assertNotNull(testuser.getRide());
			assertEquals(testuser.getPriceOfTheRide(),0);
		}
	}
	
	@Test
	public void factoryTest() throws UserFactoryException, NetworkFactoryException {
		Network net1= new Network("net1");
		Object test1 = UserFactory.createUser("User 1", "no card", "net1");
		assertTrue(test1 instanceof User);
		User user1 = (User) test1;
		assertTrue(user1.getCard() instanceof NoCard);
		
		Object test2 = UserFactory.createUser("User 2", "VLIBRE", "net1");
		assertTrue(test2 instanceof User);
		User user2 = (User) test2;
		assertTrue(user2.getCard() instanceof VlibreCard);
		
		Object test3 = UserFactory.createUser("User 3", "vMaX", "net1");
		assertTrue(test3 instanceof User);
		User user3 = (User) test3;
		assertTrue(user3.getCard() instanceof VmaxCard);
	}

}
