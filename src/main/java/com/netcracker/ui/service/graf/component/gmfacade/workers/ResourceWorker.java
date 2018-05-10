/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.workers;

import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
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
public class ResourceWorker {
    private String connectionUrl;

    public ResourceWorker(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public String addResource(String name, String ingredientOrResource, 
            String measuring, String userId, String pictureId) throws NotFoundBean{
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("name", name);
        parameters.add("ingredientOrResource", ingredientOrResource);
        parameters.add("measuring", measuring);
        parameters.add("userId", userId);
        parameters.add("pictureId", pictureId);
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "resource/addresource").queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
        String id = response.getBody();
        
        return id;
    }
}
