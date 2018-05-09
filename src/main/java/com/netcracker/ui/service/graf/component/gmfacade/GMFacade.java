/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade;

import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.graf.component.Node;
import org.json.JSONObject;
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
public class GMFacade {
    private String connectionUrl;

    public GMFacade(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public Node addNode(Node node) throws NotFoundBean
    {
        Node newNode = new Node();
        //Cоздание новой ноды на gm
        newNode.setNodeId(getIdForNewNode("1111","1111"));
        //Добавление к новой ноде description
        newNode.setDescription(node.getDescription());
        addNodeDescription(newNode.getNodeId(), newNode.getDescription());
        //Добавление к новой ноде label
        newNode.setLabel(node.getLabel());
        addNodeLabel(newNode.getNodeId(), newNode.getLabel());
        //Добавление к новой ноде picture
        newNode.setPictureId(node.getPictureId());
        addNodePicture(newNode.getNodeId(), newNode.getPictureId());
        return node;
    }
    
    private String getIdForNewNode(String receipeId, String userId) throws NotFoundBean
    {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("receipeId", receipeId);
        parameters.add("userId", userId);
        //Запрос на получение id для новой картинки
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "node/addnode").queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
        String id = response.getBody();
        
        return id;
    }
    
    private void addNodeDescription(String nodeId, String description) throws NotFoundBean
    {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("description", description);
        //Запрос на получение id для новой картинки
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "node/addnodedescription/"+nodeId).queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
    }
    
    private void addNodeLabel(String nodeId, String label) throws NotFoundBean
    {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("label", label);
        //Запрос на получение id для новой картинки
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "node/addnodedelabel/"+nodeId).queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
    }
    
    private void addNodePicture(String nodeId, String pictureId) throws NotFoundBean
    {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("pictureId", pictureId);
        //Запрос на получение id для новой картинки
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "node/addnodepicture/"+nodeId).queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
    }
}
