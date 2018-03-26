package Card;

import Bike.*;


/**
 * Provide the minimum to create all kinds of card
 * @author Kévin HIVET
 *
 */
public abstract class Card implements CardStrategyBikeInterface{
	
	//Attribute
	protected int timecredit;
	
	//Constructors
	public Card() {
		super();
	}

	public Card(int timecredit) {
		super();
		this.timecredit = timecredit;
	}

	//Methods
	public int getTimecredit() {
		return timecredit;
	}

	public void setTimecredit(int timecredit) {
		this.timecredit = timecredit;
	}
	
	/**
	 * The user who returns a bike to a PlusStation earns a number of minutes add to his time credit
	 * @param time
	 * The number of minutes to add
	 */	
	public void addTimecredit(int time) {
		this.timecredit += time;
	}
	
	public abstract int computeTotalPrice(MechanicalBike bike);
	
	public abstract int computeTotalPrice(ElectricalBike bike);

	@Override
	public String toString() {
		return "Card timecredit : " + timecredit;
	}	
		
}
