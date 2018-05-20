/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.components;

import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.security.SecurityTokenHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 * @author ArtemShevelyukhin
 */
@Component
public class Properties {
  
  private String uiURL;
  private String idpURL;
  private String serverIP; 

  public Properties() {
  }
  
  @Bean
  public String init( @Value("${ui.url}") String uiURL, 
          @Value("${idp.url}") String idpURL, 
          @Value("${server.ip}")String serverIP) throws NotFoundBean {
    BeansFactory<Properties> bfP = BeansFactory.getInstance();
    Properties p = bfP.getBean(Properties.class);
    p.setUiURL(uiURL);
    p.setServerIP(serverIP);
    p.setIdpURL(idpURL);
   
    return null;
  }

  public String getUiURL() {

    return uiURL;
  }

  public String getIdpURL() {
    return idpURL;
  }

  public String getServerIP() {
    return serverIP;
  }

  public void setUiURL(String uiURL) {
    this.uiURL = uiURL;
  }

  public void setIdpURL(String idpURL) {
    this.idpURL = idpURL;
  }

  public void setServerIP(String serverIP) {
    this.serverIP = serverIP;
  }
  
  

  
 
 
  
  
  
  
  
}
