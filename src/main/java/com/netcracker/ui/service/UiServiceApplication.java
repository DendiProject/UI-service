package com.netcracker.ui.service;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SpringUI
@Theme("mytheme")
//@Widgetset("com.mycompany.myvaadin.MyAppWidgetset")
public class UiServiceApplication extends UI
{
	public static void main(String[] args) 
        {
		SpringApplication.run(UiServiceApplication.class, args);
	}
        @Override
        protected void init(VaadinRequest vaadinRequest) 
        {
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
