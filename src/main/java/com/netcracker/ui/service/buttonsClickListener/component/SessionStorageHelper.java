/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.buttonsClickListener.component;

import com.netcracker.ui.service.forms.UserPageFields;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author ArtemShevelyukhin
 */
public class SessionStorageHelper {
  
  final String listener = "clickListener";  
  final String userPageFields = "userPageFields";
  
  public void setListener(ButtonsClickListener clickListener, ServletRequestAttributes attr ){
        HttpSession hs = attr.getRequest().getSession(true); // true == allow create
        hs.setAttribute(listener, clickListener);
  }
  
  public ButtonsClickListener  getListener(ServletRequestAttributes attr){
    HttpSession hs = attr.getRequest().getSession(true);
    ButtonsClickListener clickListener =  (ButtonsClickListener) hs.getAttribute(listener);
    return clickListener;
  }
  
  public void setUserPageFields(UserPageFields pageFields, ServletRequestAttributes attr ){
        HttpSession hs = attr.getRequest().getSession(true); // true == allow create
        hs.setAttribute(userPageFields, pageFields);
  }
  
   public UserPageFields  getUserPageFields(ServletRequestAttributes attr){
    HttpSession hs = attr.getRequest().getSession(true);
    UserPageFields pageFields = (UserPageFields) hs.getAttribute(userPageFields);
    return pageFields;
  }
}
