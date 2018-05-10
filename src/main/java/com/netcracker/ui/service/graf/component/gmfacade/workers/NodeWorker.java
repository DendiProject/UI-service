/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.workers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.receipe.view.basic.objects.Receipe;
import com.netcracker.ui.service.receipe.view.basic.objects.Resource;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
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
public class NodeWorker {
    private String connectionUrl;

    public NodeWorker(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    //Добавление ноды в GM, и получение ее id
    public String getIdForNewNode(String receipeId, String userId) throws NotFoundBean
    {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("receipeId", receipeId);
        parameters.add("userId", userId);
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
    
    //Добавление description к ноде на GM
    public void addNodeDescription(String nodeId, String description) throws NotFoundBean
    {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("description", description);
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
    
    //Добавление label к ноде на GM
    public void addNodeLabel(String nodeId, String label) throws NotFoundBean
    {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("label", label);
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
    
    //Добавление изображения к ноде на GM
    public void addNodePicture(String nodeId, String pictureId) throws NotFoundBean
    {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("pictureId", pictureId);
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
    
    //Удаление ноды с GM
    public void deleteNode(String nodeId) throws NotFoundBean
    {
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "node/deletenode/"+nodeId);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.DELETE,
                entity,
                String.class);
    }
    
    //Добавление входящих ресурсов для ноды (ресурсы должны быть предварительно
    //добавлены на GM)
    public void addInputResources(String nodeId, List<Resource> resources) throws NotFoundBean, IOException{
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "node/addinputresources/"+nodeId);
        
        BeansFactory<ObjectMapper> bfOM2 = BeansFactory.getInstance();
        ObjectMapper mapper = bfOM2.getBean(ObjectMapper.class);
        String userJsonList =  mapper.writeValueAsString(resources);
        HttpEntity<String> entity = new HttpEntity<>(userJsonList, headers);
        
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
    }
    
    //Добавление исходящих ресурсов для ноды (ресурсы должны быть предварительно
    //добавлены на GM)
    public void addOutputResources(String nodeId, List<Resource> resources) throws NotFoundBean, IOException{
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "node/addoutputresources/"+nodeId);
        
        BeansFactory<ObjectMapper> bfOM2 = BeansFactory.getInstance();
        ObjectMapper mapper = bfOM2.getBean(ObjectMapper.class);
        String userJsonList =  mapper.writeValueAsString(resources);
        HttpEntity<String> entity = new HttpEntity<>(userJsonList, headers);
        
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
    }
    
    //Получение входящих ресурсов
    public List<Resource> getInputResources(String nodeId, 
            String ingredientOrResource ) throws NotFoundBean, IOException{
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("ingredientOrResource", ingredientOrResource);
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "node/getinputresources/"+nodeId).queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        
        
        return null;
    }
    
    //Получение исходящих ресурсов для ноды
    public List<Resource> getOutputResources(String nodeId, 
            String ingredientOrResource ) throws NotFoundBean, IOException{
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("ingredientOrResource", ingredientOrResource);
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "node/getoutputresources/"+nodeId).queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        
        
        return null;
    }
}