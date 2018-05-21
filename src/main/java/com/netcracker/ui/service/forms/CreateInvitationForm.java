/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author Artem
 */
public class CreateInvitationForm  extends Window {
    
    public CreateInvitationForm() 
    {         
        /*try{
            BeansFactory<GMFacade> bfOM = BeansFactory.getInstance();
            GMFacade gmFacade = bfOM.getBean(GMFacade.class);
            ReceipeInformation receipeInformation = gmFacade.
                    getGmReceipeFacade().getReceipeInfo(recipeId);
            recipeName = receipeInformation.getName();
            recipeDescription = receipeInformation.getDescription();
        }
        catch(Exception exception){
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
        this.recipeId = recipeId;*/
        ResponsiveLayout mainLayout = new ResponsiveLayout();
        CustomLayout mainCustomLayout = new CustomLayout("CreateInvitationView");
        mainLayout.setHeight("100%");
        mainCustomLayout.setHeight("100%");
        mainLayout.addComponent(mainCustomLayout);

        
        
        setContent(mainLayout);
        setPosition(20, 150);
        setResizable(false);
        setModal(true);
    }
}