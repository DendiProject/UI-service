/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
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
public class Add extends Window 
{

    VerticalLayout leftLayout = new VerticalLayout();
    VerticalLayout rightLayout = new VerticalLayout();
     

    public Add() 
    {         
        
        leftLayout.setWidth("300px");
        leftLayout.setMargin(false);
        rightLayout.setMargin(true);
        HorizontalLayout windowContent = new HorizontalLayout(leftLayout, rightLayout);       
        setContent(windowContent);
        setPosition(20, 150);
        windowContent.setMargin(false);
        setResizeLazy(false);
        setDraggable(false);
        setResizable(false);
        setModal(true);   
    }
}

