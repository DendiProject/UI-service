/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author ivan
 */
@Theme("mytheme")

@SpringUI
public class UiServiceMainUI extends UI {
 
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {

      final HorizontalLayout layout = new HorizontalLayout();      
      layout.setMargin(true);
      layout.setSpacing(true);
      setContent(layout);

    Button bModalWindow = new Button("Регистрация");
    bModalWindow.addClickListener(new Button.ClickListener() {
        public void buttonClick(ClickEvent event) {
            RegistrationForm modalWindow = new RegistrationForm();
            addWindow(modalWindow);
      }
    });
    layout.addComponent(bModalWindow);        
    }
}
