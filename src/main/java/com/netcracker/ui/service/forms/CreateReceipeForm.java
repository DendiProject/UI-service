/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.buttonsClickListener.component.ButtonsClickListener;
import com.netcracker.ui.service.buttonsClickListener.component.ClickListener;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.forms.listeners.CreateReceipeListener;
import com.netcracker.ui.service.graf.component.Graf;
import com.netcracker.ui.service.menu.component.Menu;
import com.netcracker.ui.service.receipe.view.basic.objects.Tag;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Artem
 */
public class CreateReceipeForm  extends Window {
    
    private String catalog = null;
    private String recipeName = null;
    private String description = null; 
    private boolean isPublic = true;
    private List<Tag> tags = null;
    
    public CreateReceipeForm(CreateReceipeListener listener) 
    {         
        ResponsiveLayout mainLayout = new ResponsiveLayout();
        CustomLayout mainCustomLayout = new CustomLayout("CreateReceipeView");
        mainLayout.setHeight("100%");
        mainCustomLayout.setHeight("100%");
        mainLayout.addComponent(mainCustomLayout);
        Image image = new Image();
        image.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/AddReceipeImage.jpg")));
        image.setHeight("100%");
        image.setWidth("100%");
        mainCustomLayout.addComponent(image,"createReceipeImage");
        mainCustomLayout.addComponent(new Label("Каталог"),"createReceipeLableCatalog");
        mainCustomLayout.addComponent(new Label("Название"),"createReceipeLableReceipeName");
        mainCustomLayout.addComponent(new Label("Описание"),"createReceipeLableDescription");
        mainCustomLayout.addComponent(new Label("Теги"),"createReceipeInputTags");
        ComboBox catalogCB = new ComboBox();
        
        
        //ДОБАВИТЬ СЮДА ЗАГРУЗКУ КАТАЛОГОВ
        
        
        
        catalogCB.setItems(Arrays.asList("Тестовый"));
        catalogCB.addValueChangeListener((event) -> {
            if(!event.getValue().toString().equals("")){
                catalog = event.getValue().toString();
            }
            else
            {
                catalog = null;
            }
        });
        catalogCB.setWidth("100%");
        mainCustomLayout.addComponent(catalogCB,"createReceipeInputCatalog");
        TextField receipesName = new TextField();
        receipesName.setWidth("100%");
        receipesName.addValueChangeListener((event) -> {
            if(!event.getValue().equals("")){
                recipeName = event.toString();
            }
            else
            {
                recipeName = null;
            }
        });
        mainCustomLayout.addComponent(receipesName,"createReceipeInputReceipeName");
        TextArea area = new TextArea();
        area.setHeight("100%");
        area.setWidth("100%");
        area.setWordWrap(true);
        area.addValueChangeListener((event) -> {
            if(!event.getValue().equals("")){
                description = event.toString();
            }
            else
            {
                description = null;
            }
        });
        mainCustomLayout.addComponent(area,"createReceipeInputDescription");
        ComboBox publicOrPrivateBox = new ComboBox();
        publicOrPrivateBox.addValueChangeListener((event) -> {
            if(event.getValue().equals("Публичный")){
                isPublic = true;
            }
            else{
                isPublic = false;
            }
        });
        publicOrPrivateBox.setWidth("100%");
        publicOrPrivateBox.setItems(Arrays.asList("Публичный","Приватный"));
        publicOrPrivateBox.setEmptySelectionAllowed(false);
        mainCustomLayout.addComponent(publicOrPrivateBox,"createReceipeInputPublicOrPrivate");
        mainCustomLayout.addComponent(new Label("Денис, добавь сюда таблицу!"),"createReceipeInputTags");
        Button create = new Button("Создать рецепт");
        create.setHeight("100%");
        create.setWidth("100%");
        create.addClickListener(e -> {
            if(catalog != null & recipeName != null & description !=null){
                listener.onCreate(catalog, recipeName, description, isPublic, 
                        tags);
                this.close();
            }
        });
        mainCustomLayout.addComponent(create,"createReceipeBtn");
        setContent(mainLayout);
        setPosition(20, 150);
        setResizable(false);
        setModal(true);
    }
}
