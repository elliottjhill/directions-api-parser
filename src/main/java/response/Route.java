package response;

/**
 * An object representing a possible route as returned by the API
 * @author Elliott Hill
 *
 */
public class Route {
	/** An array of legs for this route, to be traveled in order */
	private Leg[] legs;
	/** The copyrights details on this route */
	private String copyrights;
	
	/**
	 * Get the legs of the route to be traversed in order
	 * @return the legs of the route
	 */
	public Leg[] getLegs() {
		return legs;
	}
	
	/**
	 * Get the copyright details for this route
	 * @return
	 */
	public String getCopyrights() {
		return copyrights;
	}
}
