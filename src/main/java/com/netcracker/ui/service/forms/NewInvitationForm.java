/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.forms.listeners.NewInvitationFormListener;
import com.netcracker.ui.service.forms.listeners.NoReadyReceipeListener;
import com.netcracker.ui.service.graf.component.gmfacade.GMFacade;
import com.netcracker.ui.service.graf.component.ipsFacade.stores.UserInfo;
import com.netcracker.ui.service.passageReceipe.storages.InviteInformation;
import com.netcracker.ui.service.receipe.view.basic.objects.intermediate.storages.ReceipeInformation;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

/**
 *
 * @author Artem
 */
public class NewInvitationForm  extends Window {
    
    public NewInvitationForm(InviteInformation inviteInformation, 
            UserInfo userInfo, NewInvitationFormListener listener) 
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
        CustomLayout mainCustomLayout = new CustomLayout("NewInvitationView");
        mainLayout.setHeight("100%");
        mainCustomLayout.setHeight("100%");
        mainLayout.addComponent(mainCustomLayout);

        Button go = new Button("Принять");
        go.setWidth("100%");
        go.setHeight("100%");
        mainCustomLayout.addComponent(go, "NewInvitationGoBtn");
        go.addClickListener((event) -> {
            listener.onCreate(inviteInformation.getSessionId());
            this.close();
        });
        
        mainCustomLayout.addComponent(new Label("У Вас есть новые приглашения!"), "NewInvitationLabel");
        mainCustomLayout.addComponent(new Label("Имя инициатора"), "NewInvitationNamel");
        mainCustomLayout.addComponent(new Label("Фамилия инициатора"), "NewInvitationLastNamel");
        mainCustomLayout.addComponent(new Label("Название рецепта"), "NewInvitationReceipeNamel");
        Label recDescriptionl = new Label("Описание рецепта");
        mainCustomLayout.addComponent(recDescriptionl, "NewInvitationDescriptionl");
        
        mainCustomLayout.addComponent(new Label("У Вас есть новые приглашения!"), "NewInvitationLabe");
        if(userInfo.getName()!= null){
            mainCustomLayout.addComponent(new Label(userInfo.getName()), "NewInvitationName");
        }
        else
        {
            mainCustomLayout.addComponent(new Label("Имя инициатора"), "NewInvitationName");
        }
        if(userInfo.getLastname()!= null){
            mainCustomLayout.addComponent(new Label(userInfo.getLastname()), "NewInvitationLastName");
        }
        else{
            mainCustomLayout.addComponent(new Label("Фамилия инициатора"), "NewInvitationLastName");
        }
        mainCustomLayout.addComponent(new Label(inviteInformation.getReceipeInformation().getName()), "NewInvitationReceipeName");
        Label recDescription = new Label(inviteInformation.getReceipeInformation().getDescription());
        mainCustomLayout.addComponent(recDescription, "NewInvitationDescription");
        
        
        setContent(mainLayout);
        setPosition(20, 150);
        setResizable(false);
        setModal(true);
    }
}
