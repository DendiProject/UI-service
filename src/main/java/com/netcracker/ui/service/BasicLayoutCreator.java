/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.buttonsClickListener.component.ButtonsClickListener;
import com.netcracker.ui.service.buttonsClickListener.component.SessionStorageHelper;
import com.netcracker.ui.service.components.Properties;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.exception.menu.component.exception.MenuComponentException;
import com.netcracker.ui.service.menu.component.Menu;
import com.netcracker.ui.service.security.SecurityTokenHandler;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CustomLayout;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Используется для создания базового макета приложения
 * @author Artem
 */
public class BasicLayoutCreator{
    public ResponsiveLayout mainLayout;
    public ResponsiveLayout contentRowLayout;
    public Menu menu;
    
    BeansFactory<Properties> bfP = BeansFactory.getInstance();
    Properties p;
    
    public BasicLayoutCreator() throws MenuComponentException, NotFoundBean
    {
        p = bfP.getBean(Properties.class);
        contentRowLayout = new ResponsiveLayout();
        mainLayout = new ResponsiveLayout();
        CustomLayout mainCustomLayout = new CustomLayout("MainLayout");
        menu = new Menu();
        mainCustomLayout.addComponent(menu,"navigate_row");
        mainLayout.addComponent(mainCustomLayout);
        mainCustomLayout.addComponent(contentRowLayout,"content_row");
        Page.Styles styles = Page.getCurrent().getStyles();
        String q = "http://"+p.getUiURL()+"/images/1";
        System.out.println(q);
        styles.add(".v-app {background: url(http://"+p.getUiURL()+"/images/1);webkit-background-size: cover; \n" +
"            moz-background-size: cover; \n" +
"            o-background-size: cover; \n" +
"            background-size: 100% 100%;\n" +
"            background-repeat: no-repeat;}");
        styles.add("#slider li.firstanimation {background: url(http://"+p.getUiURL()+"/images/s3);webkit-background-size: cover; \n" +
"            moz-background-size: cover; \n" +
"            o-background-size: cover; \n" +
"            background-size: 100% 100%;\n" +
"            background-repeat: no-repeat;}}");
        styles.add("#slider li.secondanimation {background: url(http://"+p.getUiURL()+"/images/s2);webkit-background-size: cover; \n" +
"            moz-background-size: cover; \n" +
"            o-background-size: cover; \n" +
"            background-size: 100% 100%;\n" +
"            background-repeat: no-repeat;}}");
        styles.add("#slider li.thirdanimation {background: url(http://"+p.getUiURL()+"/images/s1);webkit-background-size: cover; \n" +
"            moz-background-size: cover; \n" +
"            o-background-size: cover; \n" +
"            background-size: 100% 100%;\n" +
"            background-repeat: no-repeat;}}");
        styles.add("#slider li.fourthanimation {background: url(http://"+p.getUiURL()+"/images/s3);webkit-background-size: cover; \n" +
"            moz-background-size: cover; \n" +
"            o-background-size: cover; \n" +
"            background-size: 100% 100%;\n" +
"            background-repeat: no-repeat;}}");
        styles.add("#slider li.fifthanimation {background: url(http://"+p.getUiURL()+"/images/s2);webkit-background-size: cover; \n" +
"            moz-background-size: cover; \n" +
"            o-background-size: cover; \n" +
"            background-size: 100% 100%;\n" +
"            background-repeat: no-repeat;}}");
        
        BeansFactory<ButtonsClickListener> bf = BeansFactory.getInstance();
        ButtonsClickListener clickListener = bf.getBean(ButtonsClickListener.class);
        
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        new SessionStorageHelper().setListener(clickListener, attr);
        mainCustomLayout.addComponent(clickListener);
    }
}