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
 import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
 import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
 import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/** Spring 3.1.x use these */
//import static org.springframework.test.web.client.match.RequestMatchers.method;
//import static org.springframework.test.web.client.match.RequestMatchers.requestTo;
//import static org.springframework.test.web.client.response.ResponseCreators.withServerError;
//import static org.springframework.test.web.client.response.ResponseCreators.withStatus;
//import static org.springframework.test.web.client.response.ResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author eliza
 */

public class ResponceForTest extends AbstractJUnit4SpringContextTests {   

     @Autowired
    private RestTemplate restTemplate;
     
     @Autowired
    private ResponsesFromTheBackEnd responsesFromTheBackEnd;

    private MockRestServiceServer mockServer;

    
    @Before
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }
    @Test
    public void testGetMessage() {
        mockServer.expect(requestTo("http://localhost:8082/v1/Receipe"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("resultSuccess", MediaType.TEXT_PLAIN));

        String result = responsesFromTheBackEnd.getResult();

        mockServer.verify();
        assertThat(result, allOf(containsString("SUCCESS"),
                       containsString("resultSuccess")));
    }
    
}
