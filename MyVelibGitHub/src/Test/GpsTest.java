package Test;

import org.junit.Test;

import GPSCoordinate.GPSCoordinate;
import junit.framework.TestCase;

public class GpsTest extends TestCase {

	@Test
	public void test() {
		Object a = new GPSCoordinate();
		if(a instanceof GPSCoordinate) {
			GPSCoordinate gps = (GPSCoordinate) a;
			assertEquals(gps.getX(), 0.0);
			assertEquals(gps.getY(), 0.0);
		}
		else {
			System.err.println("Not a GPSCoordinate");
		}
		
		Object b = new GPSCoordinate(4, 8);
		if(b instanceof GPSCoordinate) {
			GPSCoordinate gps2 = (GPSCoordinate) b;
			assertEquals(gps2.getX(), 4.0);
			assertEquals(gps2.getY(), 8.0);
		}
		else {
			System.err.println("Not a GPSCoordinate 2");
		}
		
		GPSCoordinate gps = (GPSCoordinate) a;
		GPSCoordinate gps2 = (GPSCoordinate) b;
		assertEquals(GPSCoordinate.distance(gps, gps), 0.0);
		assertEquals(GPSCoordinate.distance(gps, gps2), Math.sqrt(80));
		
		assertFalse(gps.equals(gps2));
		GPSCoordinate gps3 = new GPSCoordinate();
		assertTrue(gps.equals(gps3));
		
	}

}
