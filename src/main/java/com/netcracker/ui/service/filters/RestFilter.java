/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.filters;

import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.security.SecurityTokenHandler;
import com.netcracker.ui.service.security.SecurityTokenHandler;
import com.netcracker.ui.service.content.handler.CookieHandler;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 *
 * @author ArtemShevelyukhin
 */
public class RestFilter implements ClientHttpRequestInterceptor {

  @Autowired
  CookieHandler cookieHandler;
  
  SecurityTokenHandler tokenHandler;
  BeansFactory<SecurityTokenHandler> bfTK = BeansFactory.getInstance();

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution chre) throws IOException {
    tokenHandler = bfTK.getBean(SecurityTokenHandler.class);
    CookieHandler cookieHandler = new CookieHandler();
    
    HttpHeaders headers = request.getHeaders();
    headers.add("secureToken", tokenHandler.getToken());
    headers.add("userCookie", cookieHandler.getCookieByName("userInfo").getValue());
    return chre.execute(request, bytes);
  }
}
