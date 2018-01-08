/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author eliza
 */
//@Theme("vaadinwindow")
public class BasicForm extends Window {

    Button vk_integration = new Button(" ");
    Button ok_integration = new Button(" ");
    Button fb_integration = new Button(" ");
    HorizontalLayout buttons = new HorizontalLayout(vk_integration, ok_integration, fb_integration);
    Image img = new Image();
    VerticalLayout image = new VerticalLayout(img);
    VerticalLayout information = new VerticalLayout(buttons);

    public BasicForm() {  
        
        
        img.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/Images/1.png")));        
        img.setWidth("350px");   
        img.setHeight("400px");
        HorizontalLayout windowContent = new HorizontalLayout(image, information);
       // CustomLayout content = new CustomLayout ("windowLayout");
       // content.addComponent(windowContent);
        setContent(windowContent);
        setWidth("700px");
        setHeight("500px");
        setPosition(20, 150);
        windowContent.setMargin(false);
        setModal(true);
    }
    

}
