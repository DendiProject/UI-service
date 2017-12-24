/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author ivan
 */
@Theme("mytheme")
@SpringUI
public class UiServiceMainUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        //setContent(new Label("Hello! I'm the root UI!"));
        Button sample = new Button("Click");
        //setContent(sample);
        sample.addClickListener(event -> Notification.show("The button was clicked", Type.TRAY_NOTIFICATION));
        layout.addComponent(new Label("Hello! I'm the root UI!"));
        layout.addComponent(sample);
    }
}
