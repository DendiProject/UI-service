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
public class EdgeWorker  {
    private String connectionUrl;

    public EdgeWorker(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    //Создание связи между нодами на gm 
    public void addEdge(String startNodeId,String endNodeId) throws NotFoundBean
    {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("startNodeId", startNodeId);
        parameters.add("endNodeId", endNodeId);
        //Запрос на получение id для новой картинки
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "node/addedge").queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
    }
    
    //Удаление связи между нодами на gm
    public void deleteEdge(String startNodeId, String endNodeId) throws NotFoundBean
    {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("startNodeId", startNodeId);
        parameters.add("endNodeId", endNodeId);
        //Запрос на получение id для новой картинки
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "node/deleteedge").queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.DELETE,
                entity,
                String.class);
    }
}