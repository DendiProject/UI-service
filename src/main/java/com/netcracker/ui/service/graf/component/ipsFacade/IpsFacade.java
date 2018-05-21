/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.ipsFacade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.graf.component.ipsFacade.stores.UserInfo;
import com.netcracker.ui.service.receipe.view.basic.objects.intermediate.storages.ShortResource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Artem
 */
public class IpsFacade
{
    private String connectionUrl;

    public IpsFacade(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public UserInfo getUserByName(String id){
        try
        {
            BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
            RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
            headers.setContentType(MediaType.APPLICATION_JSON);

            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(connectionUrl + "users/getinfo/"+id);

            HttpEntity<?> entity = new HttpEntity<>(headers);
            HttpEntity<String> response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    String.class);
            
            BeansFactory<ObjectMapper> bfOM2 = BeansFactory.getInstance();
            ObjectMapper mapper = bfOM2.getBean(ObjectMapper.class);;

            UserInfo userInfo = mapper.readValue(response.getBody(),
                    UserInfo.class);
            return userInfo;
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public List<UserInfo> getAllUsers(){
        try
        {
            BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
            RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
            headers.setContentType(MediaType.APPLICATION_JSON);

            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(connectionUrl + "users/getall");

            HttpEntity<?> entity = new HttpEntity<>(headers);
            HttpEntity<String> response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    String.class);
            
            JSONArray array = new JSONArray(response.getBody());
            List<UserInfo> users = new ArrayList<>();
            BeansFactory<ObjectMapper> bfOM2 = BeansFactory.getInstance();
            ObjectMapper mapper = bfOM2.getBean(ObjectMapper.class);
            for(int i=0; i<array.length(); i++)
            {
                UserInfo user = mapper.readValue(array.get(i).toString(),
                        UserInfo.class);
                if(user != null & !user.getId().equals("1")){
                    users.add(user);
                }
            }
        
            return users;
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
}
