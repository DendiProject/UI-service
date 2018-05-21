/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.content.handler.CookieHandler;
import com.netcracker.ui.service.content.handler.JWTHandler;
import com.netcracker.ui.service.forms.listeners.CreateReceipeListener;
import com.netcracker.ui.service.graf.component.gmfacade.GMFacade;
import com.netcracker.ui.service.receipe.view.basic.objects.Tag;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.HeaderRow;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.Cookie;

/**
 *
 * @author Artem
 */
public class CreateReceipeForm  extends Window {
    
    private String catalog = null;
    private String recipeName = null;
    private String recipeId = null;
    private String userId = null;
    private String description = null; 
    private boolean isPublic = true;
//    private List<Tag> tags = null;
    private Grid<Tag> tagGrid;
    private LinkedList<Tag> tagList;
    
    void getTag (String s){
            tagList.add(new Tag(s));
            tagGrid.getDataProvider().refreshAll();
        }
    
    public CreateReceipeForm(CreateReceipeListener listener) 
    {    
        //<editor-fold defaultstate="collapsed" desc="Таблица тегов">
        tagGrid = new Grid<>();
        tagList = new LinkedList<>();

        tagGrid.setSizeFull();

            // Set the data provider (ListDataProvider<Tags>)
//        tagList.add(new Tag("1"));
        ListDataProvider<Tag> dataProvider = new ListDataProvider<Tag>(tagList);
        tagGrid.setDataProvider(dataProvider);

        // Set the selection mode
        tagGrid.setSelectionMode(Grid.SelectionMode.NONE);

        HeaderRow topHeader = tagGrid.prependHeaderRow();

        tagGrid.addColumn(Tag::getName)
                .setId("TagsName")
                .setCaption("Name");
        // Fire a data change event to initialize the summary footer
        tagGrid.getDataProvider().refreshAll();
    //</editor-fold>
        
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
//        mainCustomLayout.addComponent(publicOrPrivateBox,"createReceipeInputPublicOrPrivate");
        mainCustomLayout.addComponent(tagGrid,"createReceipeInputTags");
        //кнопка для теста добавления в таблицу информации
        Button test = new Button("test");
        test.addClickListener(e ->{
            getTag("Tag");
        });
        mainCustomLayout.addComponent(test);
        
        //Получение id пользователя
        CookieHandler ch2 = new CookieHandler();
        JWTHandler jwth2 = new JWTHandler();
        Cookie userCookie2 = ch2.getCookieByName("userInfo");
        userId = jwth2.readUserId(userCookie2.getValue(), "test");
        
        Button create = new Button("Создать рецепт");
        create.setHeight("100%");
        create.setWidth("100%");
        create.addClickListener(e -> {
            //Создание каталога, или, если он существует, то получение его id
            GMFacade gm = new GMFacade("http://localhost:8083/");
            
            if(catalog != null & recipeId != null & description !=null & 
                    userId != null){
                //listener.onCreate(catalog, recipeId, userId, description, 
                  //      isPublic, tags);
                this.close();
            }
        });
        mainCustomLayout.addComponent(create,"createRecipeDoneBtn");
        setContent(mainLayout);
        setPosition(20, 150);
        setResizable(false);
        setModal(true);
    }
}
