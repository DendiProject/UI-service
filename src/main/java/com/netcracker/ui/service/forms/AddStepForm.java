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
import com.netcracker.ui.service.receipe.view.basic.objects.Tag;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import java.io.File;
import java.util.List;

/**
 *
 * @author Artem
 */
public class AddStepForm  extends Window {
    private String stepLable = null;
    private String stepDescription = null;
    
    public AddStepForm(AddStepListener listener, String receipeid, String userid) 
    {         
       
        
        ResponsiveLayout mainLayout = new ResponsiveLayout();
        CustomLayout mainCustomLayout = new CustomLayout("AddStepView");
        mainLayout.setHeight("100%");
        mainCustomLayout.setHeight("100%");
        mainLayout.addComponent(mainCustomLayout);
        
        
        String imageName = "http://localhost:8008/images/s3";
        Image image = new Image();
        image.setSource(new ExternalResource(imageName));
        image.setHeight("100%");
        image.setWidth("100%");
        mainCustomLayout.addComponent(image,"addStepImage");
        
        Button addImageBtn = new Button("Создать шаг");
        addImageBtn.setHeight("100%");
        addImageBtn.setWidth("100%");
        addImageBtn.addClickListener(e -> {
             BeansFactory<GMFacade> bf = BeansFactory.getInstance();
                    try {
                        if(stepDescription != null && imageName != null &&
                                stepLable != null){
                            GMFacade gmFacade = bf.getBean(GMFacade.class);
                            Node n = new Node("", stepDescription, imageName, stepLable);
                            Node node = gmFacade.getGmNodeFacade().addNode(n,receipeid, userid);
                            listener.onCreate(node);
                            this.close();
                        }
                    }
                    catch(Exception exception){
                        ExceptionHandler.getInstance().runExceptionhandling(exception);
                    }
        });
        mainCustomLayout.addComponent(addImageBtn,"addStepCancelBtn");
        
        Button cancelBtn = new Button("Отмена");
        cancelBtn.setHeight("100%");
        cancelBtn.setWidth("100%");
        cancelBtn.addClickListener(e -> {
            this.close();
        });
        mainCustomLayout.addComponent(cancelBtn,"addStepDoneBtn");
        
        Button addStepBtn = new Button("Добавить фотографию");
        addStepBtn.setHeight("100%");
        addStepBtn.setWidth("100%");
        addStepBtn.addClickListener(e -> {

        });
        mainCustomLayout.addComponent(addStepBtn,"addStepButtonAddImage");
        
        mainCustomLayout.addComponent(new Label("Добавление шага"),"addStepCaption");
        mainCustomLayout.addComponent(new Label("Ресурсы"),"addStepItem1Lable");
        mainCustomLayout.addComponent(new Label("Входящие ингредиенты"),"addStepItem2Lable");
        mainCustomLayout.addComponent(new Label("Описание шага"),"addStepItem3Lable");
        mainCustomLayout.addComponent(new Label("Получаеме ингредиенты"),"addStepItem4Lable");
        
        /*TextField stepsName = new TextField();
        stepsName.setWidth("100%");
        stepsName.addValueChangeListener((event) -> {
            if(!event.getValue().equals("")){
                //recipeName = event.toString();
            }
            else
            {
                //recipeName = null;
            }
        });
        stepsName.setPlaceholder("Название рецепта");
        mainCustomLayout.addComponent(stepsName,"addStepName");*/
        
        TextField stepsLable = new TextField();
        stepsLable.setWidth("100%");
        stepsLable.addValueChangeListener((event) -> {
            if(!event.getValue().equals("")){
                stepLable = event.getValue();
            }
            else
            {
                stepLable = null;
            }
        });
        stepsLable.setPlaceholder("Подпись шага");
        mainCustomLayout.addComponent(stepsLable,"addStepNameLable");
        
        TextArea area = new TextArea();
        area.setHeight("100%");
        area.setWidth("100%");
        area.setWordWrap(true);
        area.addValueChangeListener((event) -> {
            if(!event.getValue().equals("")){
                stepDescription = event.getValue();
            }
            else
            {
                stepDescription = null;
            }
        });
        mainCustomLayout.addComponent(area,"addStepItem3Content");
        
        
        setContent(mainLayout);
        setPosition(20, 150);
        setResizable(false);
        setModal(true);
    }
}