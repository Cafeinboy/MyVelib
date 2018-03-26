package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Bike.ElectricalBike;
import Bike.MechanicalBike;

public class BikeTest {

	@Test
	public void testMechanicalBike() {
		Object a = new MechanicalBike();
		if(a instanceof MechanicalBike) {
			MechanicalBike mb = (MechanicalBike) a;
			assertEquals(mb.getRentTime(), 0);
			mb.setRentTime(10);;
			assertEquals(mb.getRentTime(), 10);
			assertEquals(mb.getSpeed(), 15);
		}
		else {
			fail("Not a bike");
		}
		Object b = new MechanicalBike(8);
		if(a instanceof MechanicalBike) {
			MechanicalBike mb = (MechanicalBike) b;
			assertEquals(mb.getRentTime(), 8);
		}
		else {
			fail("Not a bike");
		}
	}
	
	@Test
	public void testElectricalBike() {
		Object a = new ElectricalBike();
		if(a instanceof ElectricalBike) {
			ElectricalBike eb = (ElectricalBike) a;
			assertEquals(eb.getRentTime(), 0);
			eb.setRentTime(10);;
			assertEquals(eb.getRentTime(), 10);
			assertEquals(eb.getSpeed(), 20);
		}
		else {
			fail("Not a bike");
		}
		Object b = new ElectricalBike(8);
		if(a instanceof ElectricalBike) {
			ElectricalBike eb = (ElectricalBike) b;
			assertEquals(eb.getRentTime(), 8);
		}
		else {
			fail("Not a bike");
		}
	}

}
