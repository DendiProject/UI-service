/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.content.handler;

import com.netcracker.ui.service.UserDto;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.components.SecurityTokenHandler;
import com.netcracker.ui.service.components.PostUserData;
import com.netcracker.ui.service.forms.RegistrationForm;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringComponent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import org.springframework.stereotype.Component;

/**
 *
 * @author ArtemShevelyukhin
 */

public class CookieHandler {

    private JWTHandler handler = new JWTHandler();
    
    BeansFactory<SecurityTokenHandler> bfTK = BeansFactory.getInstance();
    SecurityTokenHandler tokenStore;
    
    public void guestEnter() {
        try {
            Cookie myUserCookie = getCookieByName("userInfo");
            if (myUserCookie == null) {
                tokenStore = bfTK.getBean(SecurityTokenHandler.class);
                String secureToken = tokenStore.getToken();
                UserDto userInfo = new UserDto();
                userInfo.setEmail("guest");
                userInfo.setPassword("guestpass");
                PostUserData postRequest = new PostUserData(
                        "http://localhost:8182/idpsecure/authorization", userInfo, secureToken);
                System.out.println(postRequest.con.getResponseCode());
                if (postRequest.con.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    Map<String, List<String>> headerFields = postRequest.con.getHeaderFields();
                    List<String> cookiesHeader = headerFields.get("Set-Cookie");

                    if (cookiesHeader != null) {
                        for (String cookie : cookiesHeader) {
                            String name = HttpCookie.parse(cookie).get(0).getName();
                            String value = HttpCookie.parse(cookie).get(0).getValue();
                            myUserCookie = new Cookie(name, value);

                        }
                    }
                    myUserCookie.setPath("/");
                    VaadinService.getCurrentResponse().addCookie(myUserCookie);
                    System.out.println(myUserCookie.getName() + " " + myUserCookie.getValue());      //<----DELETE
                }
                postRequest.wr.close();
                postRequest.con.disconnect();
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(RegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public boolean updateUserCookies(PostUserData postRequest) {
        boolean result = false;
        try {
            
            System.out.println(postRequest.con.getResponseCode());
            if (postRequest.con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                result = true;
                Cookie myUserCookie = getCookieByName("userInfo");
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
                System.out.println("Токен USER"); 
                
            }
        } catch (IOException ex) {
            Logger.getLogger(CookieHandler.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return result;
    }

    public Cookie getCookieByName(String name) {
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }

        return null;
    }
}
