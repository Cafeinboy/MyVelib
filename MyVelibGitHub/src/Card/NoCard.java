package Card;

import Bike.ElectricalBike;
import Bike.MechanicalBike;

/**
 * No card, type of Card
 * @author Kévin HIVET
 *
 */
public class NoCard extends Card{
	
	//Constructors

	public NoCard() {
		super();
	}
	
	//Methods

	@Override
	public String toString() {
		return "NoCard timecredit is " + getTimecredit() ;
	}

	@Override
	public int computeTotalPrice(MechanicalBike bike) {
		int money = (int) Math.ceil((double) bike.getDurationPrice()/60);
		return money;
	}

	@Override
	public int computeTotalPrice(ElectricalBike bike) {
		int money = (int) (Math.ceil((double) bike.getDurationPrice()/60)*2);
		return money;
	}
	
	
}
