/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.annotations.VaadinServletConfiguration;


//import com.vaadin.data.Validator.InvalidValueException;
//import com.vaadin.data.util.ObjectProperty;
//import com.vaadin.data.validator.IntegerRangeValidator;
//import com.vaadin.data.validator.NullValidator;

/**
 *
 * @author eliza
 */

public class RegistrationForm extends BasicForm{    
    
    TextField firstName = new TextField ("Имя");
    TextField lastName= new TextField ("Фамилия");
    TextField email= new TextField ("E-mail");        
    PasswordField password= new PasswordField ("Пароль");   
    PasswordField password2= new PasswordField ("Повторите пароль"); 
    Button register = new Button ("Зарегистрироваться");
       


    public RegistrationForm() {          
        super();
        super.information.addComponent(firstName);
        super.information.addComponent(lastName);
        super.information.addComponent(email);
        super.information.addComponent(password);
        super.information.addComponent(register);  
      //  register.setStyleName("mybutton");
    }
    
    private void nameValidation()
    {
        firstName.setIcon(FontAwesome.AMBULANCE);
        StringLengthValidator slv = new StringLengthValidator("The name must be 3-10 letters (was {0})",3,10);        
      //  firstName.setBuffered(true);
       // firstName.addValidator(slv);


    }

}         

    
