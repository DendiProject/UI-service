/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.workers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.receipe.view.basic.objects.Resource;
import com.netcracker.ui.service.receipe.view.basic.objects.Tag;
import com.netcracker.ui.service.receipe.view.basic.objects.intermediate.storages.ShortReceipe;
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
public class TagWorker {
    private String connectionUrl;

    public TagWorker(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public void addTagToReceipe(String receipeId,String tagName) throws NotFoundBean{
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("tagName", tagName);
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipe/addtag/"+receipeId).queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
    }
    
    public List<ShortReceipe> getReceipesByTag(String  tagName, int size ) throws NotFoundBean, IOException{
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipe/getbytag/"+tagName).
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
    
    //НЕ ПРОВЕРЕНО
    public List<Tag> getTagsByLetters(String  letters, int size) throws NotFoundBean, IOException{
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipe/getbytag/"+letters).
                queryParam("page", 0).queryParam("size", size);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        
        JSONArray array = new JSONArray(response.getBody());
        List<Tag> tags = new ArrayList<>();
        BeansFactory<ObjectMapper> bfOM2 = BeansFactory.getInstance();
        ObjectMapper mapper = bfOM2.getBean(ObjectMapper.class);
        for(int i=0; i<array.length(); i++)
        {
            tags.add(mapper.readValue(array.get(i).toString(),
                    Tag.class));
        }
        
        return tags;
    }
}
