package com.stepdefinition.com;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.certificate.Certificate;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestingAPI {
	
	public static String testcertificate_password= "GISMULE - Development";
	public static String testcertificate_name="gismule-client-cert-dev.pfx";
	public Response resp;
	
	@Test
	public void trigger_test_url_throgh_api() {
		try {
			RestAssured.config = RestAssured.config().sslConfig(Certificate.getSslConfig(testcertificate_password,testcertificate_name));
		     Response response=null;
		        String authorization="Basic MTA3Y2ZlMTdjM2RiNDViNTg0YzAwOTU2YzRlYWFmN2Y6OWI2YTkxYzRiOGNENDI3MDg3OTY1QjZhYWE3NGM1NWY=";
		        	response  = RestAssured.given().contentType("application/json; charset=UTF-8").header("Authorization",authorization).when().get("https://dev-us-e1.apis.roche.com/pharma-cmg-icx-sbc-exp-stage/v1/hcp/getByMDMID?mdmId=23124683&offsetValue=0&maxCount=5").then().assertThat()
							.extract().response();
					System.out.println("not"+response.asString());
					int statusCode = response.getStatusCode();
		            System.out.println("ResponseCode"+statusCode);
				    // Assert that the correct status code is returned.
				    Assert.assertEquals(statusCode, 200, "Correct status code is not returned");
				    String mdmId = response.jsonPath().getString("searchDetails.mdmId");
				    System.out.println("mdmId"+mdmId);
				    Assert.assertEquals(mdmId, "[23124683]", "Correct status code is not returned");
				    boolean equals = mdmId.equals("[23124683]");
				    System.out.println("mdm Id ="+mdmId+"is showing as Expected ");
//				    Assert.assertEquals(response.jsonPath().getString("mdmId"), "23124683", "Correct mdmId  is not returned");
		
      }catch(Exception e) {

}
     }
}