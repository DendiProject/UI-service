/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.components;

import com.google.common.net.MediaType;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Value;


/**
 *
 * @author ArtemShevelyukhin
 */
@Component
public class StartupHousekeeper implements ApplicationListener<ContextRefreshedEvent> {

  private boolean start = true;
  
  
  
  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
     if (start){
        try {
            String encoded = "Basic dWk6dWlwYXNz";
            HttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost("http://localhost:8182/oauth/token");
            
            
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.addHeader("Authorization", encoded);
            httppost.addHeader("Cache-Control", "no-cache");
            
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("grant_type", "client_credentials"));
            params.add(new BasicNameValuePair("scope", "read"));
            
            httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            
            HttpResponse response = httpclient.execute(httppost);            
            System.err.println(response);
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