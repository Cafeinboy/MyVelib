package Exception;

public class BikeFactoryException extends Exception {

	/**
	 * Just to say at the client there is a problem with the command.
	 */
	private static final long serialVersionUID = -4151561772625806873L;

	public BikeFactoryException() {
		super();
		System.out.println("This bike does not exist, rectify the entrance.\n");
	}

}

