/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.content.handler.CookieHandler;
import com.netcracker.ui.service.content.handler.JWTHandler;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.forms.listeners.AddStepListener;
import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.gmfacade.GMFacade;
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
import javax.servlet.http.Cookie;
import com.netcracker.ui.service.receipe.view.basic.objects.Resource;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Artem
 */
public class AddStepForm  extends Window {
    private String stepLable = null;
    private String stepDescription = null;
    LinkedList<Resource> resourceListTableValue = new LinkedList<>();
    LinkedList<Resource> eingredientListTableValue = new LinkedList<>();
    LinkedList<Resource> oingredientListTableValue = new LinkedList<>();
    
    public AddStepForm(AddStepListener listener, String receipeid, 
            List<Resource> ingredients, List<Resource> resources) 
    {      
        //<editor-fold defaultstate="collapsed" desc="Таблица ресурсов">
            Grid<Resource> resourceGrid = new Grid<>();
            LinkedList<Resource> resourceList = new LinkedList<>();
            resourceGrid.setSizeFull();

            // Set the data provider (ListDataProvider<Resource>)
            //resourceList.add(new Resource("1", null, "alsh0415Resoruce", 1, "шт", null, "ingridient"));
            ListDataProvider<Resource> dataProvider = new ListDataProvider<Resource>(resourceList);
            resourceGrid.setDataProvider(dataProvider);

            // Set the selection mode
            resourceGrid.setSelectionMode(Grid.SelectionMode.NONE);

//            HeaderRow topHeader = resourceGrid.prependHeaderRow();

            resourceGrid.addColumn(Resource::getName)
                    .setId("ResourceName")
                    .setCaption("Название");
            resourceGrid.addColumn(Resource::getResourceNumber)
                    .setId("ResourceNumber")
                    .setCaption("Количество");
            // Fire a data change event to initialize the summary footer
            resourceGrid.getDataProvider().refreshAll();
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Таблица входных ингредиентов">
        Grid<Resource> eingredientGrid = new Grid<>();
        LinkedList<Resource> eingredientList = new LinkedList<>();
        eingredientGrid.setSizeFull();
        
        // Set the data provider (ListDataProvider<Resource>)
        //resourceList.add(new Resource("1", null, "alsh0415Resoruce", 1, "шт", null, "ingridient"));
        ListDataProvider<Resource> dataProviderEIng = new ListDataProvider<Resource>(eingredientList);
        eingredientGrid.setDataProvider(dataProviderEIng);
        
        // Set the selection mode
        eingredientGrid.setSelectionMode(Grid.SelectionMode.NONE);
        
//        HeaderRow topHeaderIng = eingredientGrid.prependHeaderRow();
        
        eingredientGrid.addColumn(Resource::getName)
                .setId("IngredientName")
                .setCaption("Название");
        
        eingredientGrid.addColumn(Resource::getResourceNumber)
                .setId("ResourceNumber")
                .setCaption("Количество");
        // Fire a data change event to initialize the summary footer
        eingredientGrid.getDataProvider().refreshAll();
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Таблица получаемых ингредиентов">
        Grid<Resource> oingredientGrid = new Grid<>();
        LinkedList<Resource> oingredientList = new LinkedList<>();
        oingredientGrid.setSizeFull();
        
        // Set the data provider (ListDataProvider<Resource>)
        //resourceList.add(new Resource("1", null, "alsh0415Resoruce", 1, "шт", null, "ingridient"));
        ListDataProvider<Resource> dataProviderOIng = new ListDataProvider<Resource>(oingredientList);
        oingredientGrid.setDataProvider(dataProviderOIng);
        
        // Set the selection mode
        oingredientGrid.setSelectionMode(Grid.SelectionMode.NONE);
        
//        HeaderRow topHeaderIng = eingredientGrid.prependHeaderRow();
        
        oingredientGrid.addColumn(Resource::getName)
                .setId("IngredientName")
                .setCaption("Название");
        
        oingredientGrid.addColumn(Resource::getResourceNumber)
                .setId("ResourceNumber")
                .setCaption("Количество");
        // Fire a data change event to initialize the summary footer
        oingredientGrid.getDataProvider().refreshAll();
        //</editor-fold>
        
        //Получение id пользователя
        CookieHandler ch2 = new CookieHandler();
        JWTHandler jwth2 = new JWTHandler();
        Cookie userCookie2 = ch2.getCookieByName("userInfo");
        String userid = jwth2.readUserId(userCookie2.getValue(), 
                "test");
        
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
                            //ДОБАВИТЬ СЮДА ЧТЕНИЕ ИСПОЛЬЗОВАННЫХ ИНГРЕДИЕНТОВ И 
                            //ЗАМЕНИТЬ В СТРОЧКЕ НИЖЕ ingredients
                            
                            //ЗНАЧЕНИЯ ИЗ СОЗДАННЫХ ТАБЛИЦ
//                            resourceListTableValue.add(resourceGrid.);
//                            eingredientListTableValue.add(eingredientGrid.);
//                            oingredientListTableValue.add(oingredientGrid.);

                            listener.onCreate(n, ingredients);
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
        
        //Заполнение combobox
        ComboBox resBox = new ComboBox();
        ComboBox eIngBox = new ComboBox();
        ComboBox oIngBox = new ComboBox();
        List<String> resStr = new LinkedList<>();
        List<String> ingrStr = new LinkedList<>();
        for(int i=0;i<resources.size();i++) resStr.add(resources.get(i).getName());
        for(int j=0;j<ingredients.size();j++) ingrStr.add(ingredients.get(j).getName());
        resBox.setItems(resStr);
        eIngBox.setItems(ingrStr);
        
        resBox.addValueChangeListener((event) -> {
            if(!event.getValue().toString().equals("")){
                String k = event.getValue().toString();
                for(int i=0;i<resources.size();i++){
                    if(k.equals(resources.get(i).getName())){
                        resourceList.add(resources.get(i));
                        resourceGrid.getDataProvider().refreshAll();     
                    }
                }
            }
        });
        
        eIngBox.addValueChangeListener((event) -> {
            if(!event.getValue().toString().equals("")){
                String k = event.getValue().toString();
                for(int i=0;i<ingredients.size();i++){
                    if(k.equals(ingredients.get(i).getName())){
                        eingredientList.add(ingredients.get(i));
                        eingredientGrid.getDataProvider().refreshAll();     
                    }
                }
            }
        });
        
        
        mainCustomLayout.addComponent(new Label("Добавление шага"),"addStepCaption");
        mainCustomLayout.addComponent(resBox,"addStepItem1Lable");
        mainCustomLayout.addComponent(eIngBox,"addStepItem2Lable");
        mainCustomLayout.addComponent(new Label("Описание шага"),"addStepItem3Lable");
        mainCustomLayout.addComponent(oIngBox,"addStepItem4Lable");
        mainCustomLayout.addComponent(resourceGrid,"addStepItem1Content");
        mainCustomLayout.addComponent(eingredientGrid,"addStepItem2Content");
        mainCustomLayout.addComponent(oingredientGrid,"addStepItem4Content");
        
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