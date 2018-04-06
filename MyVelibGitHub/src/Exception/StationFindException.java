package Exception;

public class StationFindException extends Exception{

	/**
	 * Just to say at the client there is a problem with the command.
	 */
	private static final long serialVersionUID = -4151561772625806873L;

	public StationFindException() {
		super();
		System.err.println("This station does not exist, rectify the entrance.\n");
	}

}
