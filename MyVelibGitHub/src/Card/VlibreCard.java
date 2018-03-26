package Card;

import Bike.ElectricalBike;
import Bike.MechanicalBike;

/**
 * Vlibre card, type of Card
 * @author Kévin HIVET
 *
 */
public class VlibreCard extends Card{
	
	//Methods

	public VlibreCard() {
		super();
	}

	public VlibreCard(int timecredit) {
		super(timecredit);
	}
	
	//Constructors

	@Override
	public String toString() {
		return "VlibreCard timecredit : " + getTimecredit() ;
	}

	@Override
	public int computeTotalPrice(MechanicalBike bike) {
		int duration = bike.getDurationPrice();
		int q1 = (int) Math.ceil((double) duration/60);
		int q2 = (int) Math.ceil((double) (duration - this.getTimecredit())/60);
		if (q1<=1) {
			return 0;
		}
		if (q2 < q1) {
			timecredit-= duration - q2*60;
			System.out.println("Crédit restant : " + timecredit);
			return q2-1;
		}
		return q1-1;
	}

	@Override
	public int computeTotalPrice(ElectricalBike bike) {
		int duration = bike.getDurationPrice();
		int q1 = (int) Math.ceil((double) duration/60);
		int q2 = (int) Math.ceil((double) (duration - this.getTimecredit())/60);
		if (q1<=1) {
			return 0;
		}
		if (q2 < q1) {
			timecredit-= duration - q2*60;
			System.out.println("Crédit restant : " + timecredit);
			return q2*2-1;
		}
		return q1*2-1;
	}
		
}
