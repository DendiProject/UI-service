/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.buttonsClickListener.component;

import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author ArtemShevelyukhin
 */
public class SessionStorageHelper {
  
  final String listener = "clickListener";
  
  public void setListener(ButtonsClickListener clickListener, ServletRequestAttributes attr ){
        HttpSession hs = attr.getRequest().getSession(true); // true == allow create
        hs.setAttribute(listener, clickListener);
  }
  
  public ButtonsClickListener  getListener(ServletRequestAttributes attr){
    HttpSession hs = attr.getRequest().getSession(true);
    ButtonsClickListener clickListener =  (ButtonsClickListener) hs.getAttribute(listener);
    return clickListener;
  }
}
