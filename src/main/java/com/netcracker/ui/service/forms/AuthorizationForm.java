/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.google.gson.Gson;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author ArtemShevelyukhin
 */
public class AuthorizationForm extends BasicForm
{        
    TextField email= new TextField ("E-mail");
    PasswordField password= new PasswordField ("Пароль");   
    PasswordField password2= new PasswordField ("Повторите пароль"); 
    Button register = new Button ("Зарегистрироваться");
       


    public AuthorizationForm() 
    {          
        super(); 
        super.information.addComponent(email);
        super.information.addComponent(password);
        super.information.addComponent(register); 
        
        register.addClickListener(e -> {
           
        });
    }
    
}       