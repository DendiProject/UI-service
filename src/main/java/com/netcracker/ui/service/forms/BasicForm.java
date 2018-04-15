/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.File;

/**
 *
 * @author eliza
 */
public class BasicForm extends Window 
{
  
    Image img = new Image();
    VerticalLayout image = new VerticalLayout(img);
    VerticalLayout information = new VerticalLayout();
     

    public BasicForm() 
    {         
        img.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/Images/1.png")));      
        img.setHeight("400px");
        image.setWidth("400px");
        //image.setHeight("480px");
        image.setMargin(false);
        information.setMargin(true);
        // information.setWidth("280px");
        // information.setHeight("480px");
        HorizontalLayout windowContent = new HorizontalLayout(image, information);       
        setContent(windowContent);
        setPosition(20, 150);
        windowContent.setMargin(false);
        setResizable(false);
        setModal(true);   
    }
}
