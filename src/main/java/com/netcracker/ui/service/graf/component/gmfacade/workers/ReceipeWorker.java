/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.workers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.receipe.view.basic.objects.intermediate.storages.ReceipeInformation;
import com.netcracker.ui.service.receipe.view.basic.objects.intermediate.storages.ShortReceipe;
import com.netcracker.ui.service.receipe.view.basic.objects.intermediate.storages.ShortResource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Artem
 */
public class ReceipeWorker {
    private String connectionUrl;

    public ReceipeWorker(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public ShortReceipe addReceipe(String name, String descriptionId, 
            String catalogId, String userId, boolean isPublic) 
            throws NotFoundBean, IOException{
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("name", name);
        parameters.add("descriptionId", descriptionId);
        parameters.add("catalogId", catalogId);
        parameters.add("userId", userId);
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipe/addreceipe").
                queryParams(parameters).queryParam("isPublic", isPublic);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
        
        BeansFactory<ObjectMapper> bfOM2 = BeansFactory.getInstance();
        ObjectMapper mapper = bfOM2.getBean(ObjectMapper.class);
        ShortReceipe shortReceipe = mapper.readValue(response.getBody(),
                    ShortReceipe.class);
        
        return shortReceipe;
    }
    
    public ReceipeInformation getReceipeInfo(String receipeId) throws NotFoundBean, IOException{
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipe/getreceipeinfo/"+receipeId);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        
        BeansFactory<ObjectMapper> bfOM2 = BeansFactory.getInstance();
        ObjectMapper mapper = bfOM2.getBean(ObjectMapper.class);
        ReceipeInformation receipeInformation = mapper.readValue(response.getBody(),
                    ReceipeInformation.class);
        
        return receipeInformation;
    }
    
    public void setReceipeCompleted(String receipeId) throws NotFoundBean{
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipe/setcompleted/"+receipeId);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
    }
    
    public void deleteReceipe(String receipeId, String userId) throws 
            NotFoundBean{
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("userId", userId);
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipe/delete/"+receipeId).
                queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.DELETE,
                entity,
                String.class);
    }
    
    public String addReceipeResource(String receipeId, String userId, 
            String resourceId, double resourceNumber) throws NotFoundBean{
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("userId", userId);
        parameters.add("resourceId", resourceId);
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipe/addreceiperesources/"+receipeId).
                queryParams(parameters).queryParam("resourceNumber", resourceNumber);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
        
        String id = response.getBody();
        
        return id;
    }
    
    public List<ShortReceipe> getPublicAndCompletesReceipesByCatalogId(
            String catalogId, int size) throws NotFoundBean, IOException{
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
            .fromHttpUrl(
                connectionUrl + "receipe/getbycatalog/"+catalogId).
                queryParam("page", 0).queryParam("size", size);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        
        JSONArray array = new JSONArray(response.getBody());
        List<ShortReceipe> recepies = new ArrayList<>();
        BeansFactory<ObjectMapper> bfOM2 = BeansFactory.getInstance();
        ObjectMapper mapper = bfOM2.getBean(ObjectMapper.class);
        for(int i=0; i<array.length(); i++)
        {
            recepies.add(mapper.readValue(array.get(i).toString(),
                    ShortReceipe.class));
        }
        
        return recepies;
    }
}
