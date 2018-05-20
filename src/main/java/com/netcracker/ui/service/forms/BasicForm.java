/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.content.handler.ContentManagerController;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;


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
      try {
        BeansFactory<ContentManagerController> bfCMC = BeansFactory.getInstance();
        ContentManagerController controller = bfCMC.getBean(ContentManagerController.class);
        img.setSource(new ExternalResource(controller.getImage("dog")));      
        img.setHeight("400");
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
      } catch (Exception ex) {
        ExceptionHandler.getInstance().runExceptionhandling(ex);
      }
    }
}
