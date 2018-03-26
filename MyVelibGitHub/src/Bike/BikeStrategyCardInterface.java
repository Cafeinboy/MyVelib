package Bike;

import Card.*;

/**
 * This class just permit us to know what are the minimum necessary about the BikeStrategy. This function has been designed to provide a system 
	 * which can resolve by a double strategy the price of a ride. In fact, we doesn't know the type of bike and the type of card, so to be in agreement with 
	 * the open close requirements this classes ({@link BikeStrategyCardInterface}, {@link CardStrategyBikeInterface}) has been created.
 * @author Kévin HIVET
 *
 */
public interface BikeStrategyCardInterface {
	
	/**
	 * This method permit to compute the total price for a certain bike and each card. 
	 * @param card
	 * This function have a card as a parameter to know which kind of reduction have the client
	 */
	public int computeTotalPrice(Card card);
	
	/**
	 * This method permit to have the duration time of a ride
	 */
	public int getDurationPrice();

}
