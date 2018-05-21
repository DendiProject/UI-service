/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.utilities;

import com.netcracker.ui.service.UserDto;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.buttonsClickListener.component.SessionStorageHelper;
import com.netcracker.ui.service.components.PostUserData;
import com.netcracker.ui.service.components.Properties;
import com.netcracker.ui.service.content.handler.CookieHandler;
import com.netcracker.ui.service.content.handler.JWTHandler;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.forms.UserPageFields;
import com.netcracker.ui.service.security.SecurityTokenHandler;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author ArtemShevelyukhin
 */
public class SaveUserImage {

  public SaveUserImage(UserPageFields info) {
    
    try {
          BeansFactory<Properties> bfP = BeansFactory.getInstance();
          Properties p = bfP.getBean(Properties.class);
          BeansFactory<SecurityTokenHandler> bfSTH = BeansFactory.getInstance();
          SecurityTokenHandler tokenStore = bfSTH.getBean(SecurityTokenHandler.class);
          UserDto userInfo = new UserDto();
          CookieHandler ch = new CookieHandler();      
          
          userInfo.setName(info.getNameValue());
          userInfo.setLastname(info.getSecondNameValue());
          userInfo.setEmail(info.getEmailValue());
          userInfo.setInfo(info.getUserInfoValue());
          userInfo.setPicture_id(info.getPicture_id());
          userInfo.setId(new JWTHandler().readUserId(ch.getCookieByName("userInfo").getValue(), "test"));
          PostUserData post = new PostUserData("http://" + p.getIdpURL() + "/idpsecure/saveUserData", userInfo, tokenStore.getToken());
          int response = post.con.getResponseCode();

          switch (response) {
            case 200:
              break;
            case 415:   
              break;
          }
        } catch (Exception ex) {
          ExceptionHandler.getInstance().runExceptionhandling(ex);
        }
    
  }
  
  
}
