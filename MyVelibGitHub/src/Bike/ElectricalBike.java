package Bike;

import Card.*;

/**
 * A class to know the kind of the bike. Here it is a ElectricalcalBike.
 * @author K�vin HIVET
 *
 */
public class ElectricalBike extends Bike {
	
	private static int speed = 20;

	public ElectricalBike() {
		super();
	}

	public ElectricalBike(int rentTime) {
		super(rentTime);
	}



	@Override
	public int computeTotalPrice(Card card) {
		return card.computeTotalPrice(this);
	}
	
	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public String toString() {
		return "Electrical Bike";
	}
	
	

}
