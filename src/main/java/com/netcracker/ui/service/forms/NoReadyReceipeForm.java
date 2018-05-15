/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.forms.listeners.AddStepListener;
import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.gmfacade.GMFacade;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

/**
 *
 * @author Artem
 */
public class NoReadyReceipeForm  extends Window {
    private String stepLable = null;
    private String stepDescription = null;
    
    public NoReadyReceipeForm() 
    {         
       
        
        ResponsiveLayout mainLayout = new ResponsiveLayout();
        CustomLayout mainCustomLayout = new CustomLayout("NoReadyReceipeForm");
        mainLayout.setHeight("100%");
        mainCustomLayout.setHeight("100%");
        mainLayout.addComponent(mainCustomLayout);
        
        
        String imageName = "http://localhost:8008/images/s3";
        Image image = new Image();
        image.setSource(new ExternalResource(imageName));
        image.setHeight("100%");
        image.setWidth("100%");
        mainCustomLayout.addComponent(image,"noReadyReceipeViewPanelWithImage");
        
        Button okBtn = new Button("Да");
        okBtn.setHeight("100%");
        okBtn.setWidth("100%");
        okBtn.addClickListener(e -> {
            
        });
        mainCustomLayout.addComponent(okBtn,"noReadyReceipeViewOkBtn");
        
        Button cancelBtn = new Button("Нет");
        cancelBtn.setHeight("100%");
        cancelBtn.setWidth("100%");
        cancelBtn.addClickListener(e -> {
            
        });
        mainCustomLayout.addComponent(cancelBtn,"noReadyReceipeViewCancelBtn");
       
        mainCustomLayout.addComponent(new Label("У Вас есть незаконченный рецепт, хотите продолжить?"),"noReadyReceipeViewLable");
        Label recipeName = new Label("Какое-то длинное длинное длинное длинное длинное длинное длинное длинное длинное Название рецепта");
        recipeName.setSizeFull();
        mainCustomLayout.addComponent(recipeName,"noReadyReceipeViewReceipeNameIn2Col");
        Label recipeDescription = new Label("Какое-то длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное Описание рецепта");
        recipeDescription.setSizeFull();
        mainCustomLayout.addComponent(recipeDescription,"noReadyReceipeViewReceipeDescriptionIn2Col");
        Label recipeNameLable = new Label("Название рецепта");
        recipeNameLable.setHeight(String.valueOf(recipeName.getHeight()-5));
        mainCustomLayout.addComponent(recipeNameLable,"noReadyReceipeViewReceipeNameIn1Col");
        Label recipeDescriptionLable = new Label("Описание рецепта");
        recipeDescriptionLable.setHeight(String.valueOf(recipeDescription.getHeight()));
        mainCustomLayout.addComponent(recipeDescriptionLable,"noReadyReceipeViewReceipeDescriptionIn1Col");
        
        setContent(mainLayout);
        setPosition(20, 150);
        setResizable(false);
        setModal(true);
    }
}