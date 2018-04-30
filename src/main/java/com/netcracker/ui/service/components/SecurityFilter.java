/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.components;

import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.content.handler.CookieHandler;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ArtemShevelyukhin
 */
@Component
public class SecurityFilter implements Filter {

  SecurityTokenHandler tokenHandler;
  BeansFactory<SecurityTokenHandler> bfTK = BeansFactory.getInstance();
  @Autowired
  CookieHandler cookieHandler;
  @Override
  public void init(FilterConfig fc) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
    HttpServletRequest servletRequest = (HttpServletRequest) request;
    HttpServletResponse servletResponse = (HttpServletResponse) response;
    
    tokenHandler = bfTK.getBean(SecurityTokenHandler.class);
    String secureToken = tokenHandler.getToken();
    
    //Если мы отправляем на CM, то
    Pattern p = Pattern.compile("http://localhost:8082/*");                                         //DELETE_THIS УЗНАТЬ ПОЧЕМУ ТАК
    String requestURL = servletRequest.getRequestURL().toString();
    Matcher m = p.matcher(requestURL);
    if(m.matches()){
      fc.doFilter( request, response);
    }
    
    //String userCookie = cookieHandler.getCookieByName("userInfo").toString();
    
    
    fc.doFilter(request, response);
    
  }

  @Override
  public void destroy() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
