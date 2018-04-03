package Exception;

public class StationFactoryException extends Exception{

	/**
	 * Just to say at the client there is a problem with the command.
	 */
	private static final long serialVersionUID = -4151561772625806873L;

	public StationFactoryException() {
		super();
		System.out.println("This station does not exist, rectify the entrance.\n");
	}

}
