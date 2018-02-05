/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.netcracker.ui.service.graf.component.Graf;
import com.vaadin.annotations.Theme;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import java.io.File;

/**
 *
 * @author Artem
 */
@Theme("centralViewTheme")
@SpringUI
public class UiServiceMainUI extends UI {
    
    public Graf com;
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setSizeFull();//Пользовательский интерфейс на весь экран
        //Базового макета
        BasicLayoutCreator main_layer = new BasicLayoutCreator();
        ResponsiveLayout main_layout = main_layer.main_layout;
        main_layout.setSizeFull();
        main_layout.setHeight("330%");
        setContent(main_layout);
        
        
        
        Button btn = new Button("fdgdg");
        main_layer.content_row_layout.addComponent(btn);
        
        
        com = new Graf();
        com.addValueChangeListener(
                new Graf.ValueChangeListener() {
            @Override
            public void valueChange() {
                Notification.show("Value: " + com.clickedNodeIs);
            }
        });
        TextField sample = new TextField();
        sample.setPlaceholder("Write something");
        main_layer.content_row_layout.addComponent(sample);
        //main_layout.addComponent(com);
        main_layer.content_row_layout.setHeight("700px");
        main_layer.content_row_layout.addComponent(com);
        com.addNode("https://ru4.anyfad.com/items/t1@dd86b0fb-d896-4da6-80b4-7fe5c9e3e17c/samye-milye-kotyata.jpg","Cat",1);
        com.addNode("https://negani.com/uploads/posts/2012-02/1330543487_husky_47.jpg","Dog",2);
        com.addNodesConnection(1,2);


        
        //Задание отступа до коцна страницы
        ResponsiveRow the_distance_between_bottom_and_recipes = main_layer.content_row_layout.addRow();
        the_distance_between_bottom_and_recipes.setHeight("60px");
        the_distance_between_bottom_and_recipes.addColumn().withDisplayRules(12, 12, 12, 12);
    }
}
