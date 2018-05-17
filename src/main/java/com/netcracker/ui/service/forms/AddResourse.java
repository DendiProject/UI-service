/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.forms.listeners.AddResourseListener;
import com.netcracker.ui.service.graf.component.gmfacade.GMFacade;
import com.netcracker.ui.service.receipe.view.basic.objects.Resource;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artem
 */
public class AddResourse  extends Add{        
    Image img = new Image();
    ComboBox autocomplite = new ComboBox();
    Label nameLabel;
    Label descriptionLabel;
    Label sizeLabel;
    TextField name = new TextField ("Name");
    TextField description = new TextField ("Description");
    ComboBox size = new ComboBox("Размерность");
    TextField count = new TextField ("Сколько");
    Button add = new Button("Добавить");
    Button add1;
    Button cancel = new Button("Отмена");
    Button plus_autocomplite = new Button("+");
    VerticalLayout vl1 = new VerticalLayout();
    HorizontalLayout h1 = new HorizontalLayout();
    
    String sizeText;
    double countVaue; 
    String nameText;
    String descriptionText;
    String pictureId="";
    String userId;
    boolean isResourseForm;
    Resource resource;
    String selectingResourceName="";
    
    List<String> autocompliteItems;
    
    public AddResourse(boolean isResourseForm, String userId, String receipeId, 
            View view, AddResourseListener listener) {           
        super();
        //Получение списка всех ресуров/ингредиентов
        BeansFactory<GMFacade> bfOM = BeansFactory.getInstance();
        try{
            GMFacade gmFacade = bfOM.getBean(GMFacade.class);
            List<Resource> allResources = gmFacade.getGmResourceFacade().
                    getResources(isResourseForm);
            autocompliteItems = new ArrayList<>();
            for(int i=0; i<allResources.size();i++){
                autocompliteItems.add(allResources.get(i).getName());
            }
        }
        catch(Exception exception){
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
        this.userId = userId;
        this.isResourseForm = isResourseForm;
        autocomplite.setItems(autocompliteItems);
        size.setItems("Шт", "Литры", "Кг");
        
        if(isResourseForm){
            add1 = new Button("Добавить новый ресурс");
        }
        else{
            add1 = new Button("Добавить новый ингредиент");
        }
        setLablesText("","","");
        
        h1.addComponent(autocomplite);
        h1.addComponent(plus_autocomplite);
        
        vl1.addComponent(new Label("Autocomplite"));
        vl1.addComponent(h1);
        vl1.addComponent(nameLabel);
        vl1.addComponent(descriptionLabel);
        vl1.addComponent(sizeLabel);
        super.leftLayout.addComponent(vl1);

        
        img.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/1.png")));      
        img.setHeight("250px");
        super.rightLayout.addComponent(img);
        super.rightLayout.addComponent(count);
        HorizontalLayout h2 = new HorizontalLayout();
        h2.addComponent(add);
        h2.addComponent(cancel);
        super.rightLayout.addComponent(h2);
        
        plus_autocomplite.addClickListener((event) -> {
            showAddNewResourseFields(true);
        });
        
        count.addValueChangeListener((event) -> {
            if(!event.getValue().equals("") & !event.getValue().equals("0")){
                try{
                    if(Double.parseDouble(event.getValue()) > 0){
                        countVaue = Double.parseDouble(event.getValue());
                    }
                    else{
                        countVaue = -1;
                    }
                }
                catch(Exception ex){
                    countVaue = -1;
                }
            }
            else{
                countVaue = -1;
            }
        });
        
        autocomplite.addValueChangeListener((event) -> {
            showAddNewResourseFields(false);
            if(!event.getValue().equals("")){
                selectingResourceName = event.getValue().toString();
                try{
                    GMFacade gmFacade = bfOM.getBean(GMFacade.class);
                    resource = gmFacade.getGmResourceFacade().getResourceByName(
                            selectingResourceName);
                    if(resource != null){
                        nameLabel.setValue("Выбрано: "+resource.getName());
                        sizeLabel.setValue("Размерность: "+resource.getMeasuring());
                    }
                }
                catch(Exception exception){
                    ExceptionHandler.getInstance().runExceptionhandling(exception);
                } 
            }
            else{
                selectingResourceName = "";
            }
        });
        
        add1.addClickListener((event) -> {
            addNewResourse();
        });
        
        name.addValueChangeListener((event) -> {
            if(!event.getValue().equals("")){
                nameText = event.getValue();
            }
            else
            {
                nameText = null;
            }
        });
        
        description.addValueChangeListener((event) -> {
            if(!event.getValue().equals("")){
                descriptionText = event.getValue();
            }
            else
            {
                descriptionText = null;
            }
        });
        
        size.addValueChangeListener((event) -> {
            if(!event.getValue().toString().equals("")){
                sizeText = event.getValue().toString();
            }
            else
            {
                sizeText = null;
            }
        });
        
        add.addClickListener((event) -> {
            //Запрос на получение данных о ресурсе по его имени
            try{
                if(!selectingResourceName.equals("") & countVaue > 0){
                    GMFacade gmFacade = bfOM.getBean(GMFacade.class);
                    resource = gmFacade.getGmResourceFacade().getResourceByName(
                            selectingResourceName);
                    if(resource != null){
                        resource.setResourceNumber(countVaue);
                        listener.onCreate(resource, receipeId, userId, view);
                        this.close();
                    }
                }
            }
            catch(Exception exception){
                ExceptionHandler.getInstance().runExceptionhandling(exception);
            } 
        });
        
        cancel.addClickListener((event) -> {
            this.close();
        });
    }
    
    private void setLablesText(String nameLabelText, 
            String descriptionLabelText, String sizeLabelText){
        nameLabel = new Label("Название");
        descriptionLabel = new Label("Описание");
        sizeLabel = new Label("Размерность");
    }
    
    private void showAddNewResourseFields(boolean show){
        if(show){
            vl1.removeAllComponents();
            
            vl1.addComponent(new Label("Autocomplite"));
            vl1.addComponent(h1);
            vl1.addComponent(name);
            vl1.addComponent(description);
            vl1.addComponent(add1);
            vl1.addComponent(size);
        }
        else
        {
            vl1.removeAllComponents();
            
            vl1.addComponent(new Label("Autocomplite"));
            vl1.addComponent(h1);
            vl1.addComponent(nameLabel);
            vl1.addComponent(descriptionLabel);
            vl1.addComponent(sizeLabel);
        }
    }
    
    private void addNewResourse(){
        if(nameText != null & sizeText != null){
            BeansFactory<GMFacade> bfOM = BeansFactory.getInstance();
            try{
                GMFacade gmFacade = bfOM.getBean(GMFacade.class);
                if(isResourseForm){
                    gmFacade.getGmResourceFacade().
                        addResource(nameText,"resource", sizeText, userId, 
                        pictureId);
                }
                else{
                    gmFacade.getGmResourceFacade().
                        addResource(nameText,"ingredient", sizeText, userId, 
                            pictureId);
                }
                autocompliteItems.add(nameText);
                autocomplite.setItems(autocompliteItems);
            }
            catch(Exception exception){
                ExceptionHandler.getInstance().runExceptionhandling(exception);
            }
        }
    }
}
