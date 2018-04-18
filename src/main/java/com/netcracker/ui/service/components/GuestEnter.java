
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.components;

import com.netcracker.ui.service.UserDto;
import com.netcracker.ui.service.components.PostUserData;
import com.netcracker.ui.service.forms.RegistrationForm;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Notification;
import io.jsonwebtoken.*;
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

/**
 *
 * @author ArtemShevelyukhin
 */
public class GuestEnter {

    public GuestEnter() {

        try {
            Cookie myUserCookie = getCookieByName("userInfo");
            if (myUserCookie == null) {
                UserDto userInfo = new UserDto();
                userInfo.setEmail("guest");
                userInfo.setPassword("guestpass");
                PostUserData postRequest = new PostUserData(
                        "http://localhost:8182/authorization/", userInfo, "change");

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
