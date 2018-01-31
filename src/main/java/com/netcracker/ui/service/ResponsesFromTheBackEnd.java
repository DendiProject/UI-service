/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.annotation.Annotation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpRange;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 *
 * @author eliza
 */
  @Component
 public class ResponsesFromTheBackEnd  {
    int receipe_id=0;
    String receipeName = new String();    
   /* @Autowired
    private RestTemplate restTemplate;
     @Autowired
     private ObjectMapper objectMapper;*/

    //@Value( "${spring.environment.url}" )
      //  private String jdbcUrl;
     

     
    public ResponsesFromTheBackEnd(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

         UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8082/v1/Receipe")        
            .queryParam("receipe_id", receipe_id);


        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
            builder.build().encode().toUri(), 
            HttpMethod.GET, 
            entity, 
            String.class);
        
        JSONObject obj = new JSONObject(response.getBody());
        receipeName = obj.getJSONObject("Receipe").getString("receipe_name");

    }
 
}
