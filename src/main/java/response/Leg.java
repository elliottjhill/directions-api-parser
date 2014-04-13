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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Leg = [startAddress = ").append(startAddress).append(", endAddress = ")
			.append(endAddress).append("]");
		
		return sb.toString();
	}
}
