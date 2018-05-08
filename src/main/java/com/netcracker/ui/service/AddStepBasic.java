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
 
/**
 *
 * @author Tsits
 */

//Тестовый

public class AddStepBasic extends Window 
{ 
    VerticalLayout leftLayout = new VerticalLayout();
    VerticalLayout centralLayout = new VerticalLayout();
    VerticalLayout rightLayout = new VerticalLayout();
     

    public AddStepBasic() 
    {         
//        leftLayout.setWidth("150px");
        leftLayout.setMargin(false);
//        centralLayout.setWidth("400px");
        centralLayout.setMargin(false);
        rightLayout.setMargin(true);
        //HorizontalLayout hl = new HorizontalLayout(centralLayout, rightLayout);
        HorizontalLayout windowContent = new HorizontalLayout(leftLayout, centralLayout);       
        setContent(windowContent);
        setPosition(20, 150);
        windowContent.setMargin(false);
        setResizeLazy(false);
        setDraggable(false);
        setResizable(false);
        setModal(true);   
    }
}
