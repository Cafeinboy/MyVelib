package Exception;

public class NetworkFactoryException extends Exception {

	/**
	 * Just to say at the client there is a problem with the command.
	 */
	private static final long serialVersionUID = -4151561772625806873L;

	public NetworkFactoryException() {
		super();
		System.out.println("This network does not exist, rectify the entrance.\n");
	}

}
