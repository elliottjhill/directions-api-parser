package response;

/**
 * An object representing a leg of a particular route. 
 * 
 * @author Elliott Hill
 *
 */
public class Leg {
	/** The address that this leg of the trip starts from */
	private String startAddress;
	/** The address that this leg of the trip ends at */
	private String endAddress;
	/** The distance to be traveled on this leg */
	private Distance distance;
	/** The approximate duration of this leg of the journey */
	private Duration duration;
	/** The location of the destination */
	private Location endLocation;
	/** The location of the origin of this Leg */
	private Location startLocation;
	/** All steps for this leg of the journey */
	private Step[] steps;
	
	/**
	 * Get the start address for this Leg of the route
	 * @return the start address for this Leg of the route
	 */
	public String getStartAddress() {
		return startAddress;
	}
	
	/**
	 * Get the end address of this leg of the route
	 * @return the end address of this leg of the route
	 */ 
	public String getEndAddress() {
		return endAddress;
	}
	
	/**
	 * Get the distance to travel on this leg of the route
	 * @return the distance to travel on this leg of the route
	 */
	public Distance getDistance() {
		return distance;
	}
	
	/**
	 * Get the approximate duration of travel on this leg of the route
	 * @return the approximate duration of travel on this leg of the route
	 */
	public Duration getDuration() {
		return duration;
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
	/**
	 * Get all steps for this leg of the journey, to be traveled order
	 * @return all steps for this leg of the journey, to be traveled order
	 */
	public Step[] getSteps() {
		return steps;
	}
}
