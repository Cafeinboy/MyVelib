package Exception;

public class UserFindFactoryException extends Exception{

	/**
	 * Just to say at the client there is a problem with the command.
	 */
	private static final long serialVersionUID = -4151561772625806873L;

	public UserFindFactoryException() {
		super();
		System.out.println("This User does not exist, rectify the entrance.\n");
	}

}
