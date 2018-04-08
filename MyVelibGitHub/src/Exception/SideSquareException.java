package Exception;

public class SideSquareException extends Exception {

	/**
	 * Just to say at the client there is a problem with the command.
	 */
	private static final long serialVersionUID = -4151561772625806873L;

	public SideSquareException() {
		super();
		System.err.println("The town can't do a size of zero.\n");
	}

}
