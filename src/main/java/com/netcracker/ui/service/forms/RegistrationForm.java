/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.netcracker.ui.service.forms.BasicForm;
import com.google.gson.Gson;
import com.netcracker.ui.service.UserDto;
import com.netcracker.ui.service.components.PostUserData;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.PasswordField;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

//import com.vaadin.data.Validator.InvalidValueException;
//import com.vaadin.data.util.ObjectProperty;
//import com.vaadin.data.validator.IntegerRangeValidator;
//import com.vaadin.data.validator.NullValidator;
/**
 *
 * @author eliza
 */
public class RegistrationForm extends BasicForm {

    TextField email = new TextField("E-mail");
    PasswordField password = new PasswordField("Пароль");
    PasswordField password2 = new PasswordField("Повторите пароль");
    Button register = new Button("Зарегистрироваться");

    public RegistrationForm() {
        super();
        super.information.addComponent(email);
        super.information.addComponent(password);
        super.information.addComponent(password2);
        super.information.addComponent(register);

        register.addClickListener(e -> {
            if (password.getValue().equals(password2.getValue())) {
                try {
                    UserDto userInfo = new UserDto();
                    userInfo.setEmail(email.getValue());
                    userInfo.setPassword(password.getValue());


                    String postUrl = "http://localhost:8182/idpsecure/register";// put in your url
                    Gson gson = new Gson();
                    HttpClient httpClient = HttpClientBuilder.create().build();
                    HttpPost post = new HttpPost(postUrl);
                    StringEntity postingString = new StringEntity(gson.toJson(userInfo));
                    post.setEntity(postingString);
                    post.setHeader("Content-type", "application/json");
                    HttpResponse response = httpClient.execute(post);

                    Notification n = new Notification("Вы зарегестрированы");
                    n.show(Page.getCurrent());
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(RegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(RegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Notification n = new Notification("Пароли не совпадают");
                n.show(Page.getCurrent());
            }

        });
    }

    private void nameValidation() {
//        firstName.setIcon(FontAwesome.AMBULANCE);
//        StringLengthValidator slv = new StringLengthValidator("The name must be 3-10 letters (was {0})",3,10);        
//      //  firstName.setBuffered(true);
//       // firstName.addValidator(slv);

    }
}
