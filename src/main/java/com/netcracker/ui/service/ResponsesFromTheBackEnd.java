/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

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


/**
 *
 * @author eliza
 */
public class ResponsesFromTheBackEnd {
    int receipe_id=0;
    String receipeName = new String();
    public ResponsesFromTheBackEnd(){
       /* receipe_name = this.receipe_name;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
         headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8082/v1/shortViewOfReceipe")
        .queryParam("receipe_id", receipe_id)
        .queryParam("receipe_name", receipe_name);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
        builder.build().encode().toUri(), 
        HttpMethod.GET, 
        entity, 
        String.class);(
       RestTemplate restTemplate = new RestTemplate();
       String fooResourceUrl= "http://localhost:8082/v1/shortViewOfReceipe";
        ResponseEntity<String> response= restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));*/
       
        RestTemplate restTemplate = new RestTemplate();
       HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8082/v1/shortViewOfReceipe")
        .queryParam("receipe_name", receipeName)
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
    
    public String getReceipeName()
    {
        
        return receipeName;
    }
 
    
}
