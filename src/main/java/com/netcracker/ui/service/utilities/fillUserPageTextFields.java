/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.utilities;

import com.netcracker.ui.service.UserDto;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.components.Properties;
import com.netcracker.ui.service.content.handler.CookieHandler;
import com.netcracker.ui.service.content.handler.JWTHandler;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.forms.UserPageFields;
import com.netcracker.ui.service.security.SecurityTokenHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ArtemShevelyukhin
 */
public class fillUserPageTextFields {

  public fillUserPageTextFields(UserPageFields info) {

    try {
      BeansFactory<SecurityTokenHandler> bfSTH = BeansFactory.getInstance();
      SecurityTokenHandler tokenStore = bfSTH.getBean(SecurityTokenHandler.class);
      BeansFactory<Properties> bfP = BeansFactory.getInstance();
      Properties p = bfP.getBean(Properties.class);
      CookieHandler ch = new CookieHandler();
      BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
      RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
      HttpHeaders headers = new HttpHeaders();

      headers.add("id", new JWTHandler().readUserId(ch.getCookieByName("userInfo").getValue(), "test"));
      HttpEntity<?> entity = new HttpEntity<>(headers);
      HttpEntity<UserDto> response = restTemplate.exchange(
              "http://" + p.getIdpURL() + "/idpsecure/getUserData" + "?access_token=" + tokenStore.getToken(),
              HttpMethod.POST,
              entity,
              UserDto.class);
      UserDto userInfo = response.getBody();
      if (userInfo.getName() != null) {
        info.setName(userInfo.getName());
      }
      if (userInfo.getLastname() != null) {
        info.setSecondName(userInfo.getLastname());
      }
      if (userInfo.getEmail() != null) {
        info.setMail(userInfo.getEmail());
      }
      if (userInfo.getInfo() != null) {
        info.setArea(userInfo.getInfo());
      }
      if (userInfo.getPicture_id() != null) {
        info.setPicture_id(userInfo.getPicture_id());
      }

    } catch (Exception ex) {
      ExceptionHandler.getInstance().runExceptionhandling(ex);
    }
  }

}
