/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.workers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.passageReceipe.storages.InviteInformation;
import com.netcracker.ui.service.passageReceipe.storages.UserStep;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class ReceipePassageWorker {
    private String connectionUrl;

    public ReceipePassageWorker(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public void makeReceipe(String sessionId, String receipeId, String userId,
            List<String> userIds) throws NotFoundBean, JsonProcessingException{
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("sessionId", sessionId);
        parameters.add("receipeId", receipeId);
        parameters.add("userId", userId);
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipepassage/makereceipe").
                queryParams(parameters);
        
        BeansFactory<ObjectMapper> bfOM2 = BeansFactory.getInstance();
        ObjectMapper mapper = bfOM2.getBean(ObjectMapper.class);
        String userJsonList =  mapper.writeValueAsString(userIds);
        HttpEntity<String> entity = new HttpEntity<>(userJsonList, headers);
        
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
    }
    
    public List<InviteInformation> userStart(String userId) throws NotFoundBean, 
            IOException{
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("userId", userId);
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipepassage/checkinvite").queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        
        JSONArray array = new JSONArray(response.getBody());
        List<InviteInformation> steps = new ArrayList<>();
        BeansFactory<ObjectMapper> bfOM2 = BeansFactory.getInstance();
        ObjectMapper mapper = bfOM2.getBean(ObjectMapper.class);
        for(int i=0; i<array.length(); i++)
        {
            steps.add(mapper.readValue(array.get(i).toString(),
                    InviteInformation.class));
        }
        
        return steps;
    }
    
    //Вернет следующий шаг, если до этого был предыдущий
    public UserStep getNextStep (String sessionId, String userId, 
            String perviousNodeId) throws NotFoundBean, IOException{
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("sessionId", sessionId);
        parameters.add("userId", userId);
        parameters.add("perviousNodeId", perviousNodeId);
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipepassage/getnextstep").queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        
        BeansFactory<ObjectMapper> bfOM2 = BeansFactory.getInstance();
        ObjectMapper mapper = bfOM2.getBean(ObjectMapper.class);
        UserStep step = mapper.readValue(response.getBody(), UserStep.class);
        if(!response.getHeaders().get("isLastNode").get(0).equals("true")){
            step.setIsLastNode(true);
        }
        else{
            step.setIsLastNode(false);
        }
        
        return step;
    }
    
    //вернет следующий шаг, если до этого не было шагов
    public UserStep getNextStep (String sessionId, String userId) throws NotFoundBean, IOException{
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("sessionId", sessionId);
        parameters.add("userId", userId);
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipepassage/getnextstep").queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        
        BeansFactory<ObjectMapper> bfOM2 = BeansFactory.getInstance();
        ObjectMapper mapper = bfOM2.getBean(ObjectMapper.class);
        UserStep step = mapper.readValue(response.getBody(), UserStep.class);
        
        return step;
    }
    
    public void completeReceipe(String sessionId, String receipeId, 
            String userId) throws NotFoundBean{
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("sessionId", sessionId);
        parameters.add("receipeId", receipeId);
        parameters.add("userId", userId);
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipepassage/completereceipe").queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
    }
    
    //верент текущий щаг, если прервалось выполнение
    public UserStep getNotCompletedStep (String sessionId, String userId) throws NotFoundBean, IOException{
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("sessionId", sessionId);
        parameters.add("userId", userId);
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipepassage/getnotcompletedstep").queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        
        BeansFactory<ObjectMapper> bfOM2 = BeansFactory.getInstance();
        ObjectMapper mapper = bfOM2.getBean(ObjectMapper.class);
        UserStep step = mapper.readValue(response.getBody(), UserStep.class);
        if(response.getHeaders().get("isLastNode").get(0).equals("true")){
            step.setIsLastNode(true);
        }
        else{
            step.setIsLastNode(false);
        }
        
        return step;
    }
    
    public Map<String, Boolean> getPassingGraph (String sessionId) throws NotFoundBean, IOException{
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("sessionId", sessionId);
        BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
        RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl + "receipepassage/getpassinggraph").queryParams(parameters);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        
        BeansFactory<ObjectMapper> bfOM2 = BeansFactory.getInstance();
        ObjectMapper mapper = bfOM2.getBean(ObjectMapper.class);
        Map<String, Boolean> nodes = mapper.readValue(response.getBody(), Map.class);
        
        return nodes;
    }
}
