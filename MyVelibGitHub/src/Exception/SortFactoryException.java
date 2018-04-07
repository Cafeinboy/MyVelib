package Exception;

public class SortFactoryException extends Exception {

	/**
	 * Just to say at the client there is a problem with the command.
	 */
	private static final long serialVersionUID = -4151561772625806873L;

	public SortFactoryException() {
		super();
		System.err.println("Something went wrong when sorting the stations, rectify the entry. Read the Javadoc because your command is wrong.\n");
	}

}
