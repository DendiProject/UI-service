/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.exception.receipe.view.ConnectionErrorException;
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
public class ReceipeProxy  implements Proxy{
    private String connectionUrl;
    public MultiValueMap<String, String> parameters;
    
    private RestTemplate restTemplate;
   
    public void setConfig(String connectionUrl, MultiValueMap<String, String> parameters)
    {
        this.connectionUrl = connectionUrl;
        this.parameters = parameters;
    }
    //Проверка прав пользователя
    @Override
    public Boolean connect() throws ConnectionErrorException{
        if(true)
        {
            return true;
        }
        else
        {
            throw new ConnectionErrorException("Access error: insufficient permissions or connection loss");
        }
    }

    //Загрузка с бэкенда данных о конкретном рецепте, используя конфигарцию, определенную через функцию connect()
    @Override
    public Object load() throws ConnectionErrorException, NotFoundBean{
        if(connect())
        {
            BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
            restTemplate = bfOM.getBean(RestTemplate.class);
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
            throw new ConnectionErrorException("Access error: insufficient permissions or connection loss");
        }
    }
}
