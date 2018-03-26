package Time;

import Time.*;
import Bike.*;
import Card.*;

public class TestDuringProcess {
	
	public static void main(String[] args) {
		//Card card = new NoCard();
		//System.out.println(card.getTimecredit());
		
		//User user = new User();
		//System.out.println(user.getCard());
		
		MechanicalBike bike1 = new MechanicalBike(0);
		ElectricalBike bike2 = new ElectricalBike(0);
		Time.addTime(70);
		System.out.println(bike1.computeTotalPrice(new NoCard()));
		System.out.println(bike2.computeTotalPrice(new NoCard()));
		System.out.println(bike1.computeTotalPrice(new VlibreCard(10)));
		System.out.println(bike2.computeTotalPrice(new VlibreCard(10)));
		System.out.println(bike2.computeTotalPrice(new VmaxCard(10)));
		System.out.println(bike2.computeTotalPrice(new VmaxCard(10)));
		System.out.println(Integer.MAX_VALUE);
		
	}

}
