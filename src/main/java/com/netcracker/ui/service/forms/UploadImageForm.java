/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;


import com.netcracker.ui.service.content.handler.ImageReceiver;
import com.vaadin.event.ActionManager;
import com.vaadin.server.FileResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author ArtemShevelyukhin
 */
public class UploadImageForm extends Window {
  
  Image img = new Image();
  VerticalLayout layout = new VerticalLayout(img);
  
  
  public UploadImageForm(Upload upload, Image image) {
    layout.addComponent(upload);
    layout.addComponent(image);
    
    HorizontalLayout windowContent = new HorizontalLayout(layout);       
    setContent(windowContent);
    setPosition(20, 150);
    windowContent.setMargin(false);
    setResizable(false);
    setModal(true); 
  }
}
