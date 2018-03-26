package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Bike.ElectricalBike;
import Bike.MechanicalBike;
import Card.Card;
import Card.NoCard;
import Card.VlibreCard;
import Card.VmaxCard;

public class CardTest {
	
	MechanicalBike testMechanicalBike = new MechanicalBike();
	ElectricalBike testElectricalBike = new ElectricalBike();

	@Test
	public void noCardPriceTest() {
		Object test = new NoCard();
		if(test instanceof Card) {
			NoCard nocard = (NoCard) test;
			assertEquals(nocard.getTimecredit(), 0);
			assertEquals(nocard.computeTotalPrice(testMechanicalBike), 0);
			assertEquals(nocard.computeTotalPrice(testElectricalBike), 0);
			testMechanicalBike.setRentTime(10);
			testElectricalBike.setRentTime(80);
			assertEquals(nocard.computeTotalPrice(testMechanicalBike), 1);
			assertEquals(nocard.computeTotalPrice(testElectricalBike), 4);
			testMechanicalBike.setRentTime(60);
			assertEquals(nocard.computeTotalPrice(testMechanicalBike), 1);
			
		}
		else {
			System.err.println("test is not a Card");
		}
	}
	
	@Test
	public void vLibreCardPriceTest() {
		Object test = new VlibreCard();
		if(test instanceof Card) {
			VlibreCard vcard = (VlibreCard) test;
			assertEquals(vcard.getTimecredit(), 0);
			assertEquals(vcard.computeTotalPrice(testMechanicalBike), 0);
			assertEquals(vcard.computeTotalPrice(testElectricalBike), 0);
			testMechanicalBike.setRentTime(10);
			testElectricalBike.setRentTime(80);
			assertEquals(vcard.computeTotalPrice(testMechanicalBike), 0);
			assertEquals(vcard.computeTotalPrice(testElectricalBike), 3);
			testMechanicalBike.setRentTime(60);
			assertEquals(vcard.computeTotalPrice(testMechanicalBike), 0);
			//With time credit
			vcard.addTimecredit(20);
			assertEquals(vcard.computeTotalPrice(testMechanicalBike), 0);
			assertEquals(vcard.getTimecredit(), 20);
			assertEquals(vcard.computeTotalPrice(testElectricalBike), 1);
			assertEquals(vcard.getTimecredit(), 0);
			vcard.addTimecredit(15);
			assertEquals(vcard.computeTotalPrice(testElectricalBike), 3);
			assertEquals(vcard.getTimecredit(), 15);
			vcard.addTimecredit(10);
			assertEquals(vcard.computeTotalPrice(testElectricalBike), 1);
			assertEquals(vcard.getTimecredit(), 5);
			
		}
		else {
			System.err.println("test is not a Card");
		}
	}
	
	@Test
	public void vMaxCardPriceTest() {
		Object test = new VmaxCard();
		if(test instanceof Card) {
			VmaxCard vcard = (VmaxCard) test;
			assertEquals(vcard.getTimecredit(), 0);
			assertEquals(vcard.computeTotalPrice(testMechanicalBike), 0);
			assertEquals(vcard.computeTotalPrice(testElectricalBike), 0);
			testMechanicalBike.setRentTime(10);
			testElectricalBike.setRentTime(80);
			assertEquals(vcard.computeTotalPrice(testMechanicalBike), 0);
			assertEquals(vcard.computeTotalPrice(testElectricalBike), 1);
			testMechanicalBike.setRentTime(60);
			assertEquals(vcard.computeTotalPrice(testMechanicalBike), 0);
			//With time credit
			vcard.addTimecredit(20);
			assertEquals(vcard.computeTotalPrice(testMechanicalBike), 0);
			assertEquals(vcard.getTimecredit(), 20);
			assertEquals(vcard.computeTotalPrice(testElectricalBike), 0);
			assertEquals(vcard.getTimecredit(), 0);
			vcard.addTimecredit(15);
			assertEquals(vcard.computeTotalPrice(testElectricalBike), 1);
			assertEquals(vcard.getTimecredit(), 15);
			vcard.addTimecredit(10);
			assertEquals(vcard.computeTotalPrice(testElectricalBike), 0);
			assertEquals(vcard.getTimecredit(), 5);
			
		}
		else {
			System.err.println("test is not a Card");
		}
	}

}
