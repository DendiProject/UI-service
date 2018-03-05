/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;



/** Spring 3.2.x use these */
import static org.hamcrest.CoreMatchers.containsString;
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


import org.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;



import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
/**
 *
 * @author eliza
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ResponceForTest extends AbstractJUnit4SpringContextTests {  

     
    private MockRestServiceServer mockServer;
    
    
   //  RestTemplate restTemplate=new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    @Autowired        
    private RestTemplate restTemplate;

    
    
    @Before
    public void setUp() {        
        mockServer = MockRestServiceServer.createServer(restTemplate);        
    }

        @Test
	public void performGet() throws Exception {	

            this.mockServer.expect(manyTimes(),requestTo("http://localhost:8082/v1/Receipe"))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withSuccess( "Success",MediaType.APPLICATION_JSON));
                    
            
            
            ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:8082/v1/Receipe", String.class);
            mockServer.verify();
                
           
            assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));    
	
	}
        
   /*     @Test
    public void testGetMessage_404() throws Exception {
        mockServer.expect(requestTo("http://localhost:8082/v1/Receipe"))
                .andExpect(method(HttpMethod.GET))                
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

         ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:8082/v1/Receipe", String.class);
            mockServer.verify();
       // Assert.assertt
        //assertThat(result.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertThat(result, equalTo(HttpStatus.NOT_FOUND));
    }
    
     @Test
    public void testGetMessage_500() throws Exception {
        mockServer.expect(requestTo("http://localhost:8082/v1/Receipe"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:8082/v1/Receipe", String.class);
        mockServer.verify();
        
        assertThat(result.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
    }


        
    @Test
	public void testRetrieveReceipe() throws JSONException {
   /* HttpEntity<String> entity = new HttpEntity<String>(null, headers);

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
	}*/
    
}
