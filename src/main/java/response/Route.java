package response;

/**
 * An object representing a possible route as returned by the API
 * @author Elliott Hill
 *
 */
public class Route {
	/** An array of legs for this route, to be traveled in order */
	private Leg[] legs;
	
	/**
	 * Get the legs of the route to be traversed in order
	 * @return the legs of the route
	 */
	public Leg[] getLegs() {
		return legs;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Route [ Legs = [");
		for (Leg l : legs) {
			sb.append(l.toString()).append(",");
		}
		sb.append("]");
		return sb.toString();
	}
}
