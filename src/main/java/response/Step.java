package response;

/**
 * An Object representing a step in a leg of a route. A step is a section of a journey in which is 
 * it is possible to move from start location to end location without turning 
 * 
 * @author Elliott Hill
 *
 */
public class Step {
	/** The distance to travel on this step of the journey */
	private Distance distance;
	/** The Duration of travel on this step of the journey */
	private Duration duration;
	/** Get HTML instructions for this step of the journey */
	private String htmlInstructions;
	/** The location of the destination */
	private Location endLocation;
	/** The location of the origin of this Leg */
	private Location startLocation;
	
	
	/**
	 * Get the distance to travel on this step of the journey
	 * 
	 * @return the distance to travel on this step of the journey
	 */
	public Distance getDistance() {
		return distance;
	}
	
	/**
	 * Get the duration of travel on this step of the journey
	 * @return the duration of travel on this step of the journey
	 */
	public Duration getDuration() {
		return duration;
	}
	
	/**
	 *  Get the HTML instructions for this step of the journey
	 * 
	 * @return  the HTML instructions for this step of the journey
	 */
	public String getHtmlInstructions() {
		return htmlInstructions;
	}
	
	/**
	 * Get the {@link Location} of the origin of this leg of the journey
	 * @return the {@link Location} of the origin of this leg
	 */
	public Location getStartLocation() {
		return startLocation;
	}
	
	/**
	 * Get the {@link Location} of the destination of this leg of the journey
	 * @return the {@link Location} of the destination of this leg
	 */
	public Location getEndLocation() {
		return endLocation;
	}
}
