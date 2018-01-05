/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.awt.FlowLayout;
import java.io.File;

/**
 *
 * @author eliza
 */
@Theme("vaadinwindow")
public class RegistrationForm extends Window{    
    Label label1 = new Label (" или ");
    TextField firstName = new TextField ("Имя");
    TextField lastName= new TextField ("Фамилия");
    TextField email= new TextField ("E-mail");        
    PasswordField password= new PasswordField ("Пароль");   
    PasswordField password2= new PasswordField ("Повторите пароль"); 
    Button register = new Button ("Зарегистрироваться");
    Button vk_integration = new Button (" ");
    Button ok_integration = new Button (" ");
    Button fb_integration = new Button (" ");        


    public RegistrationForm() {       
        super("Регистрация");    
        HorizontalLayout buttons = new HorizontalLayout(vk_integration,ok_integration,fb_integration);      
        VerticalLayout information = new VerticalLayout( buttons,label1, firstName, lastName, email,password, register);   

        Image img=new Image();
        img.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/Images/1.png")));
        img.setHeight("90%");
        img.setWidth("80%");

        HorizontalLayout windowContent = new HorizontalLayout(img,information);

        
        windowContent.setMargin(false);
        setContent(windowContent);      
        setPosition(20, 150);
        setWidth("650px");
        setHeight("550px");
        //addStyleName("mywindowstyle");
        setModal(true);
    }

    private void validation ()
    {

    }
    
}
