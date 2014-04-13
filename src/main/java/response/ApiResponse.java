package response;

/**
 * Represents an API reqsponse from the Google Directions API
 * @author Elliott Hill
 *
 */
public class ApiResponse {
	/** Status response returned from the API */
	private String status;
	/** The possible routes returned from the API call */
	private Route[] routes;
	
	/**
	 * Get the status response returned from the API
	 * @return the status response returned from the API
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Get all possible routes returned from the API call#
	 * @return all possible routes returned from the API call#
	 */
	public Route[] getRoutes() {
		return routes;
	}
}
