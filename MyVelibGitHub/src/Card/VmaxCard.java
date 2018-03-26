package Card;

import Bike.ElectricalBike;
import Bike.MechanicalBike;

/**
 * Vmax card, type of Card
 * @author Kévin HIVET
 *
 */
public class VmaxCard extends Card{
	
	//Constructors

	public VmaxCard() {
		super();
	}

	public VmaxCard(int timecredit) {
		super(timecredit);
	}
	
	//Methods

	@Override
	public String toString() {
		return "VmaxCard timecredit " + getTimecredit() ;
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
			return q2-1;
		}
		return q1-1;
	}
	
	
}
