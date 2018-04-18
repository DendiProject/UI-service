/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.google.gson.Gson;
import com.netcracker.ui.service.UserDto;
import com.netcracker.ui.service.components.JWTHandler;
import com.netcracker.ui.service.components.PostUserData;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author ArtemShevelyukhin
 */
@Component
public class AuthorizationForm extends BasicForm {

    TextField email = new TextField("E-mail");
    PasswordField password = new PasswordField("Пароль");
    Button enter = new Button("Вход");

    private JWTHandler handler = new JWTHandler();

    //  CookieStore cookieStore = new BasicCookieStore();
    // HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(cookieStore);
    public AuthorizationForm() {
        super();
        super.information.addComponent(email);
        super.information.addComponent(password);
        super.information.addComponent(password);
        super.information.addComponent(password);
        super.information.addComponent(password);
       // super.information.addComponent(new Button("Вход"), this::enter);
        //CookieStore cookieStore = myCookieStore.getInstance();

    }

    public void enter() {
        try {
            UserDto userInfo = new UserDto();
            userInfo.setEmail(email.getValue());
            userInfo.setPassword(password.getValue());

            Cookie myUserCookie = getCookieByName("userInfo");
            PostUserData postRequest = new PostUserData(
                    "http://localhost:8182/authorization/", userInfo, "we");

//                    Gson gson = new Gson(); 
//                    URL url = new URL("http://localhost:8182/authorization/");
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    con.setRequestMethod("POST");
//                    con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//                    con.setRequestProperty("Accept", "application/json");
//                    con.setDoOutput(true);
//                    
//                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
//                    wr.write(gson.toJson(userInfo));
//                    wr.flush();
            if (postRequest.con.getResponseCode() == HttpURLConnection.HTTP_OK) {

                CookieManager msCookieManager = new java.net.CookieManager();
                Map<String, List<String>> headerFields = postRequest.con.getHeaderFields();
                List<String> cookiesHeader = headerFields.get("Set-Cookie");

                if (cookiesHeader != null) {
                    for (String cookie : cookiesHeader) {
                        String name = HttpCookie.parse(cookie).get(0).getName();
                        if (name.equals("userInfo")) {
                            String value = HttpCookie.parse(cookie).get(0).getValue();
                            myUserCookie.setValue(value);
                        }
                    }
                }

                myUserCookie.setPath("/");
                VaadinService.getCurrentResponse().addCookie(myUserCookie);

                String end = handler.parseJWT(myUserCookie.getValue(), "test");

                System.out.println(myUserCookie.getName() + " " + myUserCookie.getValue());     //<--- DELETE
                System.out.println("Токен USER");                                               //<--- DELETE
                Notification n = new Notification("Вы вошли");                                  //<--- DELETE
                n.show(Page.getCurrent());                                                      //<--- DELETE
            }
            postRequest.wr.close();
            postRequest.con.disconnect();

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(RegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Cookie getCookieByName(String name) {
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }

        return null;
    }

}
