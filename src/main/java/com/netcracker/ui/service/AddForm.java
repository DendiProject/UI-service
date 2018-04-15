/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.File;

/**
 *
 * @author Tsits
 */

//Тестовый

public class AddForm extends Window 
{
    TextField text = new TextField();
    Image img = new Image();
    VerticalLayout image = new VerticalLayout(img, text);
    VerticalLayout information = new VerticalLayout();
     

    public AddForm() 
    {         
        img.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/1.png")));      
        img.setHeight("400px");
        image.setWidth("400px");
        image.setMargin(false);
        information.setMargin(true);
        HorizontalLayout windowContent = new HorizontalLayout(information, image);       
        setContent(windowContent);
        setPosition(20, 150);
        windowContent.setMargin(false);
         setResizeLazy(false);
        setDraggable(false);
        setResizable(false);
        setModal(true);   
    }
}
