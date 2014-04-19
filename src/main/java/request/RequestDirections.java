package request;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import response.ApiResponse;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * This class will connect to the Direction API and request direction from two requested points.
 * 
 * TODO: Request Strings must have no spaces in them
 * @author Elliott Hill
 *
 */
// TODO proper logging
public class RequestDirections {
	/** API End point to connect to to get directions from one point to another */
	private static final String API_END_POINT = "https://maps.googleapis.com/maps/api/directions/json";
	/** Our API key to connect to the API */
	private static String apiKey = null;
	/** HttpClient instance to be used across all isntance */
	private static HttpClient client;
	/** The returned Api Response from the directions API */
	private ApiResponse apiResponse;
	/** Origin Location String e.g. London, UK */
	private String origin;
	/** Origin logitude and latitude co-ordinates */
	private String destination;
	/** The response code from the directions API */
	private int responseCode = -1;
	/** The mode of travel to use in directions */
	private TravelMode travelMode;
	
	/**
	 * Create an object to make a request to the Directions API to get directions between two points
	 * @param origin		A string containing the origin location, either as a place name or 
	 * 						longitude / latitude location.
	 * @param destination	A String containing the destination location, either as a place name or
	 * 						a latitude / longitude location 
	 * 
	 */
	public RequestDirections(String origin, String destination) {
		this.origin = origin;
		this.destination = destination;
	}
	
	/**
	 * Create and execute the API call to the directions API and parse and return the response.
	 * @return the Api response from calling this Api call
	 * 
	 * @throws {@link IllegalArgumentException} if either origin or destination is null
	 */
	public ApiResponse getApiResponse() {
		if (apiKey == null) {
			throw new IllegalArgumentException("Must provide Api Key");
		}
		if (origin == null || destination == null) {
			throw new IllegalArgumentException("Must provide both origin and destination locations");
		}
		// No point making the call again if we've got a response or if we made a call previously
		if (apiResponse == null && getResponseCode() == -1) {
			makeRequest();
		}
		return apiResponse;
	}
	
	/**
	 * Make a request to the API end point to retrieve the directions for the request origin and 
	 * destination
	 */
	// TODO: Sort out errors and failed requests
	private void makeRequest() {
		HttpGet get = new HttpGet(API_END_POINT+getParameters());
		HttpResponse resp;
		try {			
			resp = getClient().execute(get); 
		} catch (ClientProtocolException cpe) {
			System.out.println("ClientProtocolException making request: ");
			cpe.printStackTrace();
			return;
		} catch (IOException ioe) {
			System.out.println("IO Exception making request: ");
			ioe.printStackTrace();
			return;
		}
		responseCode = resp.getStatusLine().getStatusCode();
		if (responseCode == 200) {
			// Parse the returned response
			try {
				HttpEntity e = resp.getEntity();
				InputStream is = e.getContent();
				int i;
				StringBuilder sb = new StringBuilder();
				while ((i = is.read()) != -1) {
					sb.append((char) i);
				}
				Gson response = new GsonBuilder().setFieldNamingPolicy(
						FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
				apiResponse = response.fromJson(sb.toString(), ApiResponse.class);

			} catch (IOException ioe) {
				System.out.println("Exception getting response");
				ioe.printStackTrace();
			}
		} else {
			System.out.println("Unexpected response code " + resp.getStatusLine().getStatusCode());
			return;
		}
	}
	
	/**
	 * Get the HttpClient that should be used to make Http requests to the API end point
	 * @return
	 */
	private static HttpClient getClient() {
		if (client == null) {
			client = HttpClients.createDefault();
		}
		
		return client;
	}

	
	/**
	 * Get the parameters to be appended to the end of the request string
	 * 
	 * @return the parameters to be appended to the end of the request string
	 */
	private String getParameters() {
		String requestParams = "?origin=" + origin + 
				"&destination=" + destination + 
				"&sensor=false" +
				"&key="+getApiKey();
		
		if (getTravelMode() != null) {
			requestParams += "&mode="+getTravelMode().toString().toLowerCase();
			// Transit requests need travel times 
			if (getTravelMode() == TravelMode.TRANSIT) {
				// TODO: Allow users to set Start / End Times
				requestParams += "&departure_time=" + (System.currentTimeMillis() / 1000);
				System.out.println(System.currentTimeMillis() / 1000);
			}
		}
		return requestParams;
	}
	
	/**
	 * Get the API key that we will be using to authenticate with the directions API
	 */
	private static String getApiKey() {
		return apiKey;
	}
	
	/**
	 * Set the API key to be used when contacting the directions API
	 * 
	 * @param apiKey The API Key String to be passed to the directions API
	 */
	public static void setApiKey(String apiKey) {
		RequestDirections.apiKey = apiKey;
	}

	/**
	 * Get the origin location for the requested journey.
	 * @return the origin location for the requested journey.
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Set the origin location for the requested journey. A location is either a human readable 
	 * place name e.g. "London, UK" or a longitude latitude string seperated by a comma
	 * @param origin the origin location for the requested journey
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * Get the destination location for the requested journey. 
	 * @return the destination location for the requested journey.
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Set the destination location for a requested journey. A location either is a human readable
	 *  place name e.g. "London, UK" or a longitude latitude string deperated by a comma
	 * @param destinationLocation
	 */
	public void setDestinationLocation(String destination) {
		this.destination = destination;
	}

	/**
	 * Get the HTTP response code from the directions API request call.
	 * @return the HTTP response code from the directions API request call.
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * Get the travel mode that will be used for this journey.
	 * @return the travel mode that will be used for this journey.
	 */
	public TravelMode getTravelMode() {
		return travelMode;
	}

	/**
	 * Set the {@link TravelMode} to be used for this journey
	 * @param travelMode the {@link TravelMode} to be used for this journey
	 */
	public void setTravelMode(TravelMode travelMode) {
		this.travelMode = travelMode;
	}
}
