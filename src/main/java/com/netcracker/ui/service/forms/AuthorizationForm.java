/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.google.gson.Gson;
import com.netcracker.ui.service.UserDto;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.beans.factory.TokenStoreBean;
import com.netcracker.ui.service.content.handler.CookieHandler;
import com.netcracker.ui.service.content.handler.JWTHandler;
import com.netcracker.ui.service.components.PostUserData;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.receipe.view.basic.objects.ReceipeProxy;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.netcracker.ui.service.security.SecurityTokenHandler;
import com.vaadin.ui.Window;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 *
 * @author ArtemShevelyukhin
 */
public class AuthorizationForm extends BasicForm {

  @Value("${idp.url}")
  String idpURL;

  CookieHandler cookieHandler = new CookieHandler();

  TextField email = new TextField("E-mail");
  PasswordField password = new PasswordField("Пароль");
  Button enter = new Button("Вход");

  private JWTHandler handler = new JWTHandler();

  BeansFactory<SecurityTokenHandler> bfTK = BeansFactory.getInstance();
  SecurityTokenHandler tokenStore;

  // HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(cookieStore);
  public AuthorizationForm() {
    super();
    super.information.addComponent(email);
    super.information.addComponent(password);
    super.information.addComponent(enter);

    enter.addClickListener(e -> {
      try {
        tokenStore = bfTK.getBean(SecurityTokenHandler.class);
        UserDto userInfo = new UserDto();
        userInfo.setEmail(email.getValue());
        userInfo.setPassword(password.getValue());

        String secureToken = tokenStore.getToken();

        PostUserData postRequest = new PostUserData(
                "http://localhost:8181/idpsecure/authorization", userInfo, secureToken);

        int cookieupdate = cookieHandler.updateUserCookies(postRequest);

        switch (cookieupdate) {
          case 200:
            Notification n = new Notification("Вы вошли");
            n.setDelayMsec(1300);//<--- DELETE
            n.show(Page.getCurrent());
            Thread.sleep(1300);
            AuthorizationForm.this.close();
            //Page.getCurrent().reload();
          case 409:
            Notification q = new Notification("Вы ввели неверную почту или пароль");
            q.setDelayMsec(1300);
            q.show(Page.getCurrent());
        }   

        postRequest.wr.close();
        postRequest.con.disconnect();

      } catch (Exception exception) {
        ExceptionHandler.getInstance().runExceptionhandling(exception);
      }

    });
  }

  private Cookie getCookieByName(String name) {
    Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();

    for (Cookie cookie : cookies) {
      if (name.equals(cookie.getName())) {
        return cookie;
      }
    }

    return null;
  }

}
