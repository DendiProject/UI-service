/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.netcracker.ui.service.forms.BasicForm;
import com.google.gson.Gson;
import com.netcracker.ui.service.UserDto;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.content.handler.CookieHandler;
import com.netcracker.ui.service.security.SecurityTokenHandler;
import com.netcracker.ui.service.components.PostUserData;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.PasswordField;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

//import com.vaadin.data.Validator.InvalidValueException;
//import com.vaadin.data.util.ObjectProperty;
//import com.vaadin.data.validator.IntegerRangeValidator;
//import com.vaadin.data.validator.NullValidator;
/**
 *
 * @author eliza
 */
public class RegistrationForm extends BasicForm {

  @Value("${idp.url}")
  String idpURL;

  CookieHandler cookieHandler = new CookieHandler();

  TextField email = new TextField("E-mail");
  PasswordField password = new PasswordField("Пароль");
  PasswordField password2 = new PasswordField("Повторите пароль");
  Button register = new Button("Зарегистрироваться");

  BeansFactory<SecurityTokenHandler> bfTK = BeansFactory.getInstance();
  SecurityTokenHandler tokenStore;

  public RegistrationForm() {
    super();
    super.information.addComponent(email);
    super.information.addComponent(password);
    super.information.addComponent(password2);
    super.information.addComponent(register);

    register.addClickListener(e -> {
      try {

        if (password.getValue().equals(password2.getValue())) {

          tokenStore = bfTK.getBean(SecurityTokenHandler.class);
          String secureToken = tokenStore.getToken();

          UserDto userInfo = new UserDto();
          userInfo.setEmail(email.getValue());
          userInfo.setPassword(password.getValue());
          System.out.println("secureToken = " + secureToken);
          PostUserData postRequest = new PostUserData(
                  "http://localhost:8181/idpsecure/register", userInfo, secureToken);

//
//                    String postUrl = "http://"+idpURL+"/idpsecure/register";
//                    Gson gson = new Gson();
//                    HttpClient httpClient = HttpClientBuilder.create().build();
//                    HttpPost post = new HttpPost(postUrl);
//                    StringEntity postingString = new StringEntity(gson.toJson(userInfo));
//                    post.setEntity(postingString);
//                    post.setHeader("Content-type", "application/json");
//                    HttpResponse response = httpClient.execute(post);
          System.out.println(postRequest.con.getResponseCode());
          int response = postRequest.con.getResponseCode();
          switch (response) {
            case 200:
              Notification n = new Notification("Вы зарегестрированы");
              n.show(Page.getCurrent());
              RegistrationForm.this.close();

              PostUserData authRequest = new PostUserData(
                      "http://localhost:8181/idpsecure/authorization", userInfo, secureToken);

              cookieHandler.updateUserCookies(authRequest);
              break;
            case 409:
              Notification q = new Notification("Пользователь с такой почтой уже существует. " + "\n" + "Введите другую");
              q.setDelayMsec(2600);
              q.show(Page.getCurrent());
              break;
          }
          postRequest.wr.close();
          postRequest.con.disconnect();
        } else {
          Notification n = new Notification("Пароли не совпадают");
          n.setDelayMsec(1300);
          n.show(Page.getCurrent());

        }

      } catch (Exception exception) {
        ExceptionHandler.getInstance().runExceptionhandling(exception);
      }
    }
    );
  }

}

