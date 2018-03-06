/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.Proxy;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Artem
 */
public class ReceipeProxi implements Proxy{

    private String connectionUrl;
    MultiValueMap<String, String> parameters;
    
    public ReceipeProxi(String connectionUrl, MultiValueMap<String, String> parameters)
    {
        this.connectionUrl = connectionUrl;
        this.parameters = parameters;
    }
   
    //Проверка прав пользователя
    private Boolean connect() {
        
        return true;
    }

    //Загрузка с бэкенда данных о конкретном рецепте, используя конфигарцию, определенную через функцию connect()
    @Override
    public Object load() {
        if(connect())
        {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(connectionUrl).queryParams(parameters);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(), 
                HttpMethod.GET, 
                entity, 
                String.class);       

            JSONObject resultReceipe = new JSONObject(response.getBody());

            return resultReceipe;
        }
        else
        {
            return null;
        }
    }
    
}
