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
 * @author Elliott Hill
 *
 */
// TODO proper logging
public class RequestDirections {
	/** API End point to connect to to get directions from one point to another */
	private static final String API_END_POINT = "https://maps.googleapis.com/maps/api/directions/json";
	/** Our API key to connect to the API */
	private static final String API_KEY = "AIzaSyD6wVp9xuWe2ULXw1ghK0FSw8J9OfvTzkw";
	/** HttpClient instance to be used across all isntance */
	private static HttpClient client;
	/** The returned Api Response from the directions API */
	private ApiResponse apiResponse;
	
	// TODO: Add in how to get points A and points B
	public RequestDirections() {
		
	}
	
	/**
	 * Create and execute the API call to the directions API and parse and return the response.
	 * @return the Api response from calling this Api call
	 */
	public ApiResponse getApiResponse() {
		makeRequest();
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
		
		if (resp.getStatusLine().getStatusCode() == 200) {
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
		// TODO: Decide mode to use
		return "?origin=Cambridge,UK&destination=London,UK&sensor=false&key="+getApiKey();
	}
	
	/**
	 * Get the API key that we will be using to authenticate with the directions API
	 */
	private String getApiKey() {
		return API_KEY;
	}
}
