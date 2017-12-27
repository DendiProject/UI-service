/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.jarektoro.responsivelayout.ResponsiveColumn;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author ivan
 */
@Theme("mytheme")
@SpringUI
public class UiServiceMainUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) 
    {
        /*final VerticalLayout layout = new VerticalLayout();//Это просто тренировался вставлять компоненты
        layout.setMargin(true);
        setContent(layout);
        //setContent(new Label("Hello! I'm the root UI!"));
        Button sample = new Button("Click");
        //setContent(sample);
        sample.addClickListener(event -> Notification.show("The button was clicked", Type.TRAY_NOTIFICATION));
        layout.addComponent(new Label("Hello! I'm the root UI!"));
        layout.addComponent(sample);*/
        
        
        
        
        
        
        
        /*setSizeFull(); // set the size of the UI to fill the screen//Пытался сделать так, чтобы при просмотре 
                                                                     //с большого экрана кнопки занимали первую строку
                                                                     //а с экрана телефона каждая кнопка на своей строке
        
        ResponsiveLayout responsiveLayout = new ResponsiveLayout();
        responsiveLayout.setSizeFull();
        setContent(responsiveLayout);
        ResponsiveRow row = responsiveLayout.addRow();
        row.setHeight("100%");

        Button deleteButton = new Button("",FontAwesome.TRASH);
        deleteButton.addStyleName(ValoTheme.BUTTON_DANGER);
        deleteButton.setSizeFull();

        Button commentButton = new Button("",FontAwesome.COMMENT);
        commentButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        commentButton.setSizeFull();

        Button editButton = new Button("",FontAwesome.EDIT);
        editButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        editButton.setSizeFull();


        row.addColumn().withDisplayRules(12,6,4,4).withComponent(deleteButton);
        row.addColumn().withDisplayRules(12,6,4,4).withComponent(commentButton);
        row.addColumn().withDisplayRules(12,6,4,4).withComponent(editButton);
        //ResponsiveColumn mainSectionCol = new ResponsiveColumn();
        //mainSectionCol.setWidth("100%");
        //mainSectionCol2.setWidth("100%");
        //mainSectionCol3.setWidth("100%");*/
        
        
        
        //тоже, что и предыдущий блок, но вместо кнопок группа компонентов
        ResponsiveLayout responsiveLayout = new ResponsiveLayout();
        setContent(responsiveLayout);
        ResponsiveRow row = responsiveLayout.addRow();

        row.addColumn().withDisplayRules(12, 6, 4, 4).withComponent(createNestedLayout());
        row.addColumn().withDisplayRules(12, 6, 4, 4).withComponent(createNestedLayout());
        row.addColumn().withDisplayRules(12, 6, 4, 4).withComponent(createNestedLayout());
    }
    
    public ResponsiveLayout createNestedLayout() 
    {
        ResponsiveLayout nestedLayout = new ResponsiveLayout();
        ResponsiveRow nestedLayoutRow = nestedLayout.addRow();

        Label label = new Label("Title!");
        label.setSizeFull();

        Button button = new Button("", FontAwesome.ANCHOR);
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.setSizeFull();

        TextField field = new TextField();
        field.setSizeFull();


        nestedLayoutRow.addColumn().withDisplayRules(12, 6, 4, 4).withComponent(label);
        nestedLayoutRow.addColumn().withDisplayRules(12, 6, 4, 4).withComponent(button);
        nestedLayoutRow.addColumn().withDisplayRules(12, 6, 4, 4).withComponent(field);


        return nestedLayout;
    }
}
