/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.exception.menu.component.exception.MenuComponentException;
import com.netcracker.ui.service.menu.component.Menu;
import com.vaadin.server.Page;
import com.vaadin.ui.CustomLayout;

/**
 * Используется для создания базового макета приложения
 * @author Artem
 */
public class BasicLayoutCreator{
    public ResponsiveLayout mainLayout;
    public ResponsiveLayout contentRowLayout;
    public Menu menu;
    public BasicLayoutCreator() throws MenuComponentException
    {
        contentRowLayout = new ResponsiveLayout();
        mainLayout = new ResponsiveLayout();
        CustomLayout mainCustomLayout = new CustomLayout("MainLayout");
        menu = new Menu();
        mainCustomLayout.addComponent(menu,"navigate_row");
        mainLayout.addComponent(mainCustomLayout);
        mainCustomLayout.addComponent(contentRowLayout,"content_row");
        Page.Styles styles = Page.getCurrent().getStyles();
        styles.add(".v-app {background: url(http://localhost:8081/images/4c816099-9fa4-4280-a70f-fddfa331f143);webkit-background-size: cover; \n" +
"            moz-background-size: cover; \n" +
"            o-background-size: cover; \n" +
"            background-size: 100% 100%;\n" +
"            background-repeat: no-repeat;}");
        styles.add("#slider li.firstanimation {background: url(http://localhost:8081/images/2be975bc-7d92-4a42-b686-527a5cd6c950);webkit-background-size: cover; \n" +
"            moz-background-size: cover; \n" +
"            o-background-size: cover; \n" +
"            background-size: 100% 100%;\n" +
"            background-repeat: no-repeat;}}");
        styles.add("#slider li.secondanimation {background: url(http://localhost:8081/images/75b6f221-8adc-4464-b430-345ea7201224);webkit-background-size: cover; \n" +
"            moz-background-size: cover; \n" +
"            o-background-size: cover; \n" +
"            background-size: 100% 100%;\n" +
"            background-repeat: no-repeat;}}");
        styles.add("#slider li.thirdanimation {background: url(http://localhost:8081/images/f7ffca16-f6c9-429e-a197-8d7d5c1b3828);webkit-background-size: cover; \n" +
"            moz-background-size: cover; \n" +
"            o-background-size: cover; \n" +
"            background-size: 100% 100%;\n" +
"            background-repeat: no-repeat;}}");
        styles.add("#slider li.fourthanimation {background: url(http://localhost:8081/images/2be975bc-7d92-4a42-b686-527a5cd6c950);webkit-background-size: cover; \n" +
"            moz-background-size: cover; \n" +
"            o-background-size: cover; \n" +
"            background-size: 100% 100%;\n" +
"            background-repeat: no-repeat;}}");
        styles.add("#slider li.fifthanimation {background: url(http://localhost:8081/images/75b6f221-8adc-4464-b430-345ea7201224);webkit-background-size: cover; \n" +
"            moz-background-size: cover; \n" +
"            o-background-size: cover; \n" +
"            background-size: 100% 100%;\n" +
"            background-repeat: no-repeat;}}");
    }
}