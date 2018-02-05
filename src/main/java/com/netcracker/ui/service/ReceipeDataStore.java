/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import java.util.List;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author eliza
 */
public class ReceipeDataStore implements DataStore{
     @Autowired
    private RestTemplate restTemplate;
     String httpResult;
   

    @Override
    public void load() {
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

         UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8082/v1/Receipe")        
            .queryParam("receipe_id", 1);


        HttpEntity<?> entity = new HttpEntity<>(headers);

       HttpEntity<String> response = restTemplate.exchange(
            builder.build().encode().toUri(), 
            HttpMethod.GET, 
            entity, 
            String.class);       
       httpResult =restTemplate.getForObject("http://localhost:8082/v1/Receipe", String.class);
        
    }

    @Override
    public void callBack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
