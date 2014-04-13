package response;

/**
 * An Object representing a location for use within a given journey. The location is defined by the 
 * latitude and longitude locations
 * 
 * @author Elliott Hill
 *
 */
public class Location {
	/** The latitude for this location */
	private float lat;
	/** The longitude for this location */
	private float lng;
	
	/**
	 * Get the latitude for this location
	 * @return the latitude for this location
	 */
	public float getLatitude() {
		return lat;
	}
	
	/**
	 * Get the longitude for this location
	 * @return the longitude for this location
	 */
	public float getLongitude() {
		return lng;
	}
}
