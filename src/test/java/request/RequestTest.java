package request;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import response.ApiResponse;

/**
 * This is a test to integration with the Google Directions API. This isn't really a unit test, its
 * an integration test as it connects to the Google Directions API and tests communication with the 
 * service.
 * 
 * 
 * @author Elliott Hill
 *
 */
public class RequestTest {
	
	@Before
	public void init() {
		RequestDirections.setApiKey("AIzaSyD6wVp9xuWe2ULXw1ghK0FSw8J9OfvTzkw");
	}
	
	private void printApiResponse(ApiResponse apiResp) {
		Gson gson = new GsonBuilder().setFieldNamingPolicy(
				FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		System.out.println(gson.toJson(apiResp));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getDirectionsWithNoApiKey_ExceptionIllegalArgumentException() {
		RequestDirections.setApiKey(null);
		RequestDirections rd = new RequestDirections("Cambridge,UK", "London,UK");
		rd.getApiResponse();
	}

	// Integration Test
	@Test
	public void directionsWithLocation_Excect200ResponseCode() {
		RequestDirections rd = new RequestDirections("Cambridge,UK", "London,UK");
		rd.getApiResponse();
		Assert.assertEquals(200, rd.getResponseCode());
		Assert.assertEquals("OK", rd.getApiResponse().getStatus());
		System.out.println("With Location");
		printApiResponse(rd.getApiResponse());		
	}

	// Integration Test
	@Test
	public void directionsWithCoOrdinates_Excect200ResponseCode() {
		RequestDirections rd = new RequestDirections("52.205322,0.121766", "51.508526,-0.1255005");
		rd.getApiResponse();
		Assert.assertEquals(200, rd.getResponseCode());
		Assert.assertEquals("OK", rd.getApiResponse().getStatus());
		System.out.println("With Coords");
		printApiResponse(rd.getApiResponse());		
	}
	
	// Integration Test
	@Test
	public void directionsWitTravelModeWalking_Excect200ResponseCode() {
		RequestDirections rd = new RequestDirections("52.205322,0.121766", "51.508526,-0.1255005");
		rd.setTravelMode(TravelMode.WALKING);
		rd.getApiResponse();
		Assert.assertEquals(200, rd.getResponseCode());
		Assert.assertEquals("OK", rd.getApiResponse().getStatus());
	}
	
	// Integration Test
	@Test
	public void directionsWitTravelModeDriveing_Excect200ResponseCode() {
		RequestDirections rd = new RequestDirections("52.205322,0.121766", "51.508526,-0.1255005");
		rd.setTravelMode(TravelMode.DRIVING);
		rd.getApiResponse();
		Assert.assertEquals(200, rd.getResponseCode());
		Assert.assertEquals("OK", rd.getApiResponse().getStatus());
	}
	
	// Integration Test
	@Test
	public void directionsWitTravelModeCycling_Excect200ResponseCode() {
		RequestDirections rd = new RequestDirections("52.205322,0.121766", "51.508526,-0.1255005");
		rd.setTravelMode(TravelMode.BICYCLING);
		rd.getApiResponse();
		Assert.assertEquals(200, rd.getResponseCode());
		Assert.assertEquals("OK", rd.getApiResponse().getStatus());
	}
	
	// Integration Test
	@Test
	public void directionsWitTravelModeTransit_Excect200ResponseCode() {
		RequestDirections rd = new RequestDirections("52.205322,0.121766", "51.508526,-0.1255005");
		rd.setTravelMode(TravelMode.TRANSIT);
		rd.getApiResponse();
		Assert.assertEquals(200, rd.getResponseCode());
		Assert.assertEquals("OK", rd.getApiResponse().getStatus());
	}

	@Test (expected = IllegalArgumentException.class)
	public void noOrigin_ExpectIllegalArgumentException() {
		RequestDirections rd = new RequestDirections(null, "London, UK");
		rd.getApiResponse();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void noDestination_ExpectIllegalArgumentException() {
		RequestDirections rd = new RequestDirections(null, "London, UK");
		rd.getApiResponse();
		
	}
}
