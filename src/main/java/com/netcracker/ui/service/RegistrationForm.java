/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.io.File;

/**
 *
 * @author eliza
 */
public class RegistrationForm extends FormLayout{
     Label registration = new Label ("Регистрация");     
     Label label1 = new Label (" или ");
     TextField firstName = new TextField ("Имя");
     TextField lastName= new TextField ("Фамилия");
     TextField email= new TextField ("E-mail");    
    Button register = new Button ("Зарегистрироваться");
    Button vk_integration = new Button (" ");
    Button ok_integration = new Button (" ");
    Button fb_integration = new Button (" ");
        
//private UiServiceMainUI myUI;

public RegistrationForm() {   
    setSizeUndefined();  
   // Image pic = new Image("C:\\Users\\eliza\\111.jpg");
    //VerticalLayout picture = new VerticalLayout();   
   // picture.addComponent(pic);
    HorizontalLayout buttons = new HorizontalLayout(vk_integration,ok_integration,fb_integration);
    HorizontalLayout label = new HorizontalLayout(registration);  
    VerticalLayout information = new VerticalLayout(label , buttons,label1, firstName, lastName, email, register);
    addComponents(information);
}
    
}
