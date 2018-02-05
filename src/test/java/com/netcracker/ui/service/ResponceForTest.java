/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/** Spring 3.2.x use these */
 import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
 import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
 import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.netcracker.ui.service.SimpleRestServise;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import com.netcracker.ui.service.UiServiceApplication;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
/**
 *
 * @author eliza
 */


public class ResponceForTest extends AbstractJUnit4SpringContextTests {  

    private  SimpleRestServise simpleRestService;    
    private MockRestServiceServer mockServer;
    
    
   //  RestTemplate restTemplate=new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    @Autowired        
    private RestTemplate restTemplate;

  /*   
    @Before
    public void setUp() {        
        mockServer = MockRestServiceServer.createServer(restTemplate);        
    }
/*
        @Test
	public void performGet() throws Exception {	

            this.mockServer.expect(requestTo("http://google.com"))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withSuccess("resultSuccess", MediaType.TEXT_PLAIN));
            
            
            
            String result = simpleRestService.getMessage();
            mockServer.verify();
                
   
        assertThat(result, allOf(containsString("SUCCESS"),
                       containsString("resultSuccess")));       
	
	}
        @Test
    public void testGetMessage_404() {
        mockServer.expect(requestTo("http://google.com"))
                .andExpect(method(HttpMethod.GET))                
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        String result = simpleRestService.getMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"),
                       containsString("404")));
    }
     @Test
    public void testGetMessage_500() {
        mockServer.expect(requestTo("http://google.com"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        String result = simpleRestService.getMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"),
                       containsString("500")));
    }
*/
    @Test
	public void performGet() throws Exception {
            
        }
        
    @Test
	public void testRetrieveReceipe() throws JSONException {
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/v1/Receipe"),
				HttpMethod.GET, entity, String.class);

		String expected = "{\"Receipe\": \n" +
                            "  {\n" +
                            "    \"receipe_id\": 1,\n" +
                            "    \"receipe_name\": \"d\",\n" +
                            "    \"steps\": [\n" +
                            "    {\"step_number\": 1, \n" +
                            "   \"destription\": \"aaa\",\n" +
                            "   \"image_id\": 1},\n" +
                            "   {\"step_number\": 2, \n" +
                            "   \"destription\": \"bbb\",\n" +
                            "   \"image_id\": 2}\n" +
                            "    ]\n" +
                            "  }\n" +
                            "}\n" +
                            "}\n" +
                            "}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
        }
        
        	private String createURLWithPort(String uri) {
		return "http://localhost:8082"  + uri;
	}
    
}
