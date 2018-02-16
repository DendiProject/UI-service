/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe;

import com.netcracker.ui.service.DataStore;
import java.util.List;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Используется для получения данных для short view of receipe
 * @author Artem
 */
public class ReceipeDataStore implements DataStore{
    @Autowired
    //private RestTemplate restTemplate;
    //String httpResult;
    //String receipeName=new String();
    private JSONObject resultReceipe;
    private int receipeId;
    private String url;

    @Override
    public void load() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder
            .fromHttpUrl(url);
        if(url.equals("http://localhost:8082/v1/Receipe"))//Для поддержки запросов на разные сервисы
        {
            builder = UriComponentsBuilder
            .fromHttpUrl(url).queryParam("receipe_id", receipeId);
        }

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
            builder.build().encode().toUri(), 
            HttpMethod.GET, 
            entity, 
            String.class);       
       
        //JSONObject obj = new JSONObject(response.getBody());
        resultReceipe = new JSONObject(response.getBody());
        //receipeName = obj.getJSONObject("Receipe").getString("receipe_name");
        // httpResult =restTemplate.getForObject("http://localhost:8082/v1/Receipe", String.class);
        
    }
    
    public JSONObject getResult(int ReceipeId, String url)
    {
        this.url = url;
        this.receipeId = ReceipeId;
        load();
        return this.resultReceipe;
    }

    @Override
    public void callBack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
