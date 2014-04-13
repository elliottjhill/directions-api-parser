package response;

/**
 * A class representing the duration of a leg or step of a route
 * @author Elliott Hill
 *
 */
public class Duration {
	/** The text representation of the duration */
	private String text;
	/** The integer value of the duration in seconds */
	private int value;
	
	/**
	 * Get the textual representation of the duration
	 * @return the textual representation of the duration
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Get the integer value of the duration in seconds
	 * @return
	 */
	public int getValue() {
		return value;
	}
}
