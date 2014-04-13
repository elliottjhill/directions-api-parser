package response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A few simple tests to ensure that the JSON response is as expected
 * @author Elliott Hill
 *
 */
public class ResponseFormat {
	
	private Gson gson;
	@Before
	public void init() {
		gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
	}

	@Test
	public void TestEmptyResponseString_ExpectNullObject() {
		String resp = "{}";
		ApiResponse r = gson.fromJson(resp, ApiResponse.class); 
		
		Assert.assertNull(r.getRoutes());
	}
	
	@Test
	public void TestOneEmptyRouteResponseString_ExpectZeroRoutes() {
		String resp = "{\"routes\":[]}";
		ApiResponse r = gson.fromJson(resp, ApiResponse.class); 
		
		Assert.assertEquals(0, r.getRoutes().length);
	}
	
	@Test
	public void TestOneEmptyRouteResponseString_ExpectOneEmptyRoute() {
		String resp = "{\"routes\":[{ \"legs\": []}]}";
		ApiResponse r = gson.fromJson(resp, ApiResponse.class); 
		
		Assert.assertEquals(1, r.getRoutes().length);
		Assert.assertEquals(r.getRoutes()[0].getLegs().length, 0);
		
	}
	
	@Test
	public void TestTwoEmptyRouteResponseString_ExpectTwoEmptyRoute() {
		String resp = "{\"routes\":[{\"legs\": []},{\"legs\" : []}]}";
		ApiResponse r = gson.fromJson(resp, ApiResponse.class); 
		
		Assert.assertEquals(2, r.getRoutes().length);
		Assert.assertEquals(r.getRoutes()[0].getLegs().length, 0);
		
	}
	
	@Test
	public void TestOneRouteWithLegResponseString_ExpectOneLegWithDistanceAndDuration() {
		String resp = 
				"{" +
					"\"routes\":[" +
					"{ " +
						"\"legs\": [" +
							"{\"start_address\" : \"Here\", \"end_address\" : \"There\", " +
								"\"distance\" : {\"text\" : \"distText\", \"value\" : 1}" +
								", \"duration\" : {\"text\" : \"durText\", \"value\" : 2}" +
							"}" +
						
						"]" +
					"}]" +
				"}";
		ApiResponse r = gson.fromJson(resp, ApiResponse.class); 
		
		Assert.assertEquals(1, r.getRoutes().length);
		Assert.assertEquals(r.getRoutes()[0].getLegs().length, 1);
		Assert.assertEquals(r.getRoutes()[0].getLegs()[0].getStartAddress(), "Here");
		Assert.assertEquals(r.getRoutes()[0].getLegs()[0].getEndAddress(), "There");
		Assert.assertEquals(r.getRoutes()[0].getLegs()[0].getDistance().getText(), "distText");
		Assert.assertEquals(r.getRoutes()[0].getLegs()[0].getDistance().getValue(), 1);
		Assert.assertEquals(r.getRoutes()[0].getLegs()[0].getDuration().getText(), "durText");
		Assert.assertEquals(r.getRoutes()[0].getLegs()[0].getDuration().getValue(), 2);
		
	}
}
