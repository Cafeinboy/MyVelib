package Bike;

import Card.*;

/**
 * A class to know the kind of the bike. Here it is a MechanicalBike.
 * @author Kévin HIVET
 *
 */
public class MechanicalBike extends Bike {
	
	private static int speed = 15;
	
	public MechanicalBike() {
		super();
	}

	public MechanicalBike(int rentTime) {
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

}
