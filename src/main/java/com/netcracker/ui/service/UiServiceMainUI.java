/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.netcracker.ui.service.menu.component.EventHandlerOfTheForm;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Artem
 */
@Theme("centralViewTheme")
@SpringUI
public class UiServiceMainUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setSizeFull();//Пользовательский интерфейс на весь экран
        //Базового макета
        BasicLayoutCreator main_layer;
        try {
            main_layer = new BasicLayoutCreator();
            ResponsiveLayout main_layout = main_layer.main_layout;
            main_layout.setSizeFull();
            main_layout.setHeight("330%");
            setContent(main_layout);
            //Создание строки, для добавления конкретного контента на даную страницу
        
            //Добавление обработчика событий нажатия на клавиши меню
            main_layer.menu.com.addValueChangeListener(new EventHandlerOfTheForm.ValueChangeListener() 
            {
                @Override
                public void valueChange() 
                {
                    //Здесь по id можем понять, какая кнопка была нажата и сделать соответствующее действие
                    //Например, запустить форму
                    RegistrationForm modalWindow = new RegistrationForm();
                    addWindow(modalWindow);
                }
            });



            ResponsiveRow recipe_title = main_layer.content_row_layout.addRow();
            CustomLayout  top_recipe_title_layout = new CustomLayout("TopRecipeTitle");
            recipe_title.addColumn().withDisplayRules(12, 12, 12, 12).withComponent(top_recipe_title_layout);

            //Задание отступа до коцна страницы
            ResponsiveRow the_distance_between_bottom_and_recipes = main_layer.content_row_layout.addRow();
            the_distance_between_bottom_and_recipes.setHeight("60px");
            the_distance_between_bottom_and_recipes.addColumn().withDisplayRules(12, 12, 12, 12);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(UiServiceMainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
