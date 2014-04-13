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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Status = ").append(status).append(",\n")
			.append("Routes = [");
		for (Route r : routes) {
			sb.append(r.toString()).append(",");
		}
		sb.append("]");
		return sb.toString();
	}
}
