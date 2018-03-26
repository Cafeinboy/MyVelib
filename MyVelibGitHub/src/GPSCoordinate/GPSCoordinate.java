package GPSCoordinate;

/**
 * This class provides a certain number of geometric function for the GPS coordinates. We have made an hypothesis to begin the project, the coordinate will be given by a 
 * (x,y) position, with can be given by a grid over Paris System Velib. That's not matter because we can change quickly the way to compute that with the real GPS coordinate.
 * @author Kevin HIVET
 *
 */
public class GPSCoordinate {
	
	//Attributes
	
	private double x;
	private double y;
	
	//Constructors
	
	public GPSCoordinate() {
		super();
		this.x = 0;
		this.y = 0;
	}
	

	public GPSCoordinate(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	//Methods
	
	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	/**
	 * This function permit to give the distance between to GPS coordinates.
	 * @param a 
	 * First Position
	 * @param b
	 * Second Position
	 * @return
	 * The distance between both.
	 */
	public static double distance(GPSCoordinate a, GPSCoordinate b) {
		return Math.sqrt(Math.pow((a.getX()-b.getX()),2) + Math.pow((a.getY()-b.getY()),2));
	}
	
	/**
	 * An override method to know if two GPS object are equal.
	 * @param gps 
	 * It is the object with which we want to compare.
	 */
	@Override
	public boolean equals(Object gps) {
		if (gps instanceof GPSCoordinate) {
			GPSCoordinate GPS = (GPSCoordinate) gps;
			return this.x == GPS.x && this.y == GPS.y;
		}
		return false;
	}
	
}
