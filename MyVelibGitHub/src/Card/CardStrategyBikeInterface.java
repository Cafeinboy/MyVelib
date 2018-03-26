package Card;

import Bike.BikeStrategyCardInterface;
import Bike.ElectricalBike;
import Bike.MechanicalBike;

/**
 * This class just permit us to know what are the minimum necessary about the BikeStrategy. This function has been designed to provide a system 
	 * which can resolve by a double strategy the price of a ride. In fact, we doesn't know the type of bike and the type of card, so to be in agreement with 
	 * the open close requirements this classes ({@link BikeStrategyCardInterface}, {@link CardStrategyBikeInterface}) has been created.
 * @author Kévin HIVET
 *
 */
public interface CardStrategyBikeInterface {
	
	/**
	 * This method permit to compute the total price for a certain bike and this card. 
	 * @param bike
	 * This function have a {@link Bike.MechanicalBike} as a parameter to know which kind of reduction have the client for this bike
	 */
	public int computeTotalPrice(MechanicalBike bike);
	
	/**
	 * This method permit to compute the total price for a certain bike and this card. 
	 * @param bike 
	 * This function have a {@link Bike.ElectricalBike} as a parameter to know which kind of reduction have the client for this bike
	 */
	public int computeTotalPrice(ElectricalBike bike);

}
