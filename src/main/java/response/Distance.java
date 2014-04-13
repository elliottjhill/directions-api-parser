package response;

/**
 * Class representing the distance to be traveled on a particular leg or step of a route.
 * @author Elliott Hill
 *
 */
public class Distance {
	/** A textual representation of the distance to be traveled */
	private String text;
	
	/** An integer value in cm of the distance to be traveled */
	private int value;
	
	/**
	 * Get the text representation of the distance to be traveled
	 * @return
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Get the value of the distance to be traveled in centimeters (cm)
	 * @return
	 */
	public int getValue() {
		return value;
	}
}
