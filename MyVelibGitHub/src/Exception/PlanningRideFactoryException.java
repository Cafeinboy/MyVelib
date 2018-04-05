package Exception;

public class PlanningRideFactoryException extends Exception {

	/**
	 * Just to say at the client there is a problem with the command.
	 */
	private static final long serialVersionUID = -4151561772625806873L;

	public PlanningRideFactoryException() {
		super();
		System.out.println("This strategy does not exist, rectify the entrance.\n");
	}

}