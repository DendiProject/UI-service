/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.components;

import com.google.common.net.MediaType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import java.util.Base64;

/**
 *
 * @author ArtemShevelyukhin
 */
@Component
public class StartupHousekeeper implements ApplicationListener<ContextRefreshedEvent> {

    private boolean start = true;
    
    MyTokenStore myTokenStore;

    BeansFactory<MyTokenStore> bfTK = BeansFactory.getInstance();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        if (start){   
        try {
            String client_id = "ui";
            String client_secret = "uipass";
            String client  = client_id + ":" + client_secret;
            byte[] encodedBytes = Base64.getEncoder().encode(client.getBytes());   
            String str = new String(encodedBytes, StandardCharsets.UTF_8);
            String encoded = "Basic " + str ;
                       
            HttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost("http://localhost:8182/oauth/token");
            
            
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.addHeader("Authorization", encoded);
            httppost.addHeader("Cache-Control", "no-cache");
            
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("grant_type", "client_credentials"));
            params.add(new BasicNameValuePair("scope", "read")); 
            httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            System.out.println("TOKEN");
            HttpResponse response = httpclient.execute(httppost);            
            HttpEntity entity = response.getEntity();
            
            if (entity != null) {
                InputStream instream = entity.getContent();
                try {                  
                    String responseString = EntityUtils.toString(entity, "UTF-8");
                    JSONObject jsonObj = new JSONObject(responseString);
                    System.out.println("before get instance");
                    myTokenStore = bfTK.getBean(MyTokenStore.class);
                    System.out.println("after get instance");
                    myTokenStore.setToken(jsonObj.get("access_token").toString());
                   
                    System.out.println("json---  " + jsonObj);
                    System.out.println(responseString);
                    System.out.println("TokenStore  " + myTokenStore.getToken());
                  
                } finally {
                    instream.close();
                }
            }
            
        } 
        catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StartupHousekeeper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
             Logger.getLogger(StartupHousekeeper.class.getName()).log(Level.SEVERE, null, ex);
        }
        start = false;
     }
    }
}