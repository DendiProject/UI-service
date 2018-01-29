/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.exception.menu.component.exception.MenuComponentException;
import com.netcracker.ui.service.menu.component.HandlerForClickingTheButton;
import com.netcracker.ui.service.menu.component.Menu;
import com.netcracker.ui.service.menu.component.MenusButton;
import com.netcracker.ui.service.menu.component.MenusSearchBar;
import com.vaadin.ui.CustomLayout;
import java.util.ArrayList;

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
    }
}