/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.views;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.buttonsClickListener.component.ButtonsClickListener;
import com.netcracker.ui.service.buttonsClickListener.component.ClickListener;
import com.netcracker.ui.service.buttonsClickListener.component.SessionStorageHelper;
import com.netcracker.ui.service.content.handler.CookieHandler;
import com.netcracker.ui.service.content.handler.JWTHandler;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.forms.AuthorizationForm;
import com.netcracker.ui.service.forms.listeners.CreateReceipeListener;
import com.netcracker.ui.service.graf.component.gmfacade.GMFacade;
import com.netcracker.ui.service.receipe.view.basic.objects.Catalog;
import com.netcracker.ui.service.receipe.view.basic.objects.Tag;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Artem
 */
public class CreateRecipeView {
    private String catalogName = null;
    private String catalogId = null;
    private String recipeName = null;
    private String recipeId = null;
    private String userId = null;
    private String descriptionCatalog = null;
    private String descriptionRecipe = null; 
    private boolean isPublic = true;
    private boolean createNewCatalog = false;
    private List<Tag> tegs = null;
    CreateReceipeListener listener;
    private boolean noListenCatalog = false;
    private List<String> catalogsName = new ArrayList<>();
    private Grid<Tag> tagGrid;
    private LinkedList<Tag> tagList;
    
    void getTag (String s){
            tagList.add(new Tag(s));
            tagGrid.getDataProvider().refreshAll();
        }
    
    public CreateRecipeView(CreateReceipeListener listener){
        this.listener = listener;
    }
    
    public CustomLayout create(){
        //<editor-fold defaultstate="collapsed" desc="Таблица тегов">
        tagGrid = new Grid<>();
        tagList = new LinkedList<>();
        tagGrid.setSizeFull();
        
        // Set the data provider (ListDataProvider<Tags>)
        ListDataProvider<Tag> dataProvider = new ListDataProvider<Tag>(tagList);
        tagGrid.setDataProvider(dataProvider);

        // Set the selection mode
        tagGrid.setSelectionMode(Grid.SelectionMode.NONE);

//        HeaderRow topHeader = tagGrid.prependHeaderRow();

        tagGrid.addColumn(Tag::getName)
                .setId("TagsName")
                .setCaption("Name");
        
        tagGrid.addColumn(t -> "X",
                new ButtonRenderer(clickEvent -> {
                tagList.remove(clickEvent.getItem());
                tagGrid.setItems(tagList);
            }));
        // Fire a data change event to initialize the summary footer
        tagGrid.getDataProvider().refreshAll();
    //</editor-fold>
    
        HorizontalLayout h1 = new HorizontalLayout();
        HorizontalLayout h2 = new HorizontalLayout();
        HorizontalLayout h3 = new HorizontalLayout();
        VerticalLayout v1 = new VerticalLayout();
        TextField addTagField = new TextField();
        Button addTagBtn = new Button("Добавить тэг");
        addTagBtn.addClickListener(e ->{
            getTag(addTagField.getValue());
        });
        v1.addComponents(addTagField, addTagBtn);
        h2.addComponent(tagGrid);
        h2.setWidth("50%");
        h3.addComponent(v1);
        h3.setWidth("50%");
        h1.addComponents(h2,h3);
        h1.setWidth("100%");
        h1.setHeight("100%");
        
        ResponsiveLayout mainLayout = new ResponsiveLayout();
        CustomLayout mainCustomLayout = new CustomLayout("CreateReceipeView");
        mainLayout.setHeight("100%");
        mainCustomLayout.setHeight("100%");
        mainLayout.addComponent(mainCustomLayout);
        String imageName = "http://localhost:8008/images/s3";
        Image image = new Image();
        image.setSource(new ExternalResource(imageName));
        image.setHeight("100%");
        image.setWidth("100%");
        mainCustomLayout.addComponent(image, "CreateReceipesImage");
        mainCustomLayout.addComponent(new Label("Предустановленный Каталог"),"createReceipeLableCatalog");
        mainCustomLayout.addComponent(new Label("Новый Каталог"),"createReceipeLableCatalog2");
        mainCustomLayout.addComponent(new Label("Описание рецепта"),"createReceipeLableDescriptionRecipe");
        mainCustomLayout.addComponent(new Label("Название рецепта"),"createReceipeLableReceipeName");
        mainCustomLayout.addComponent(new Label("Доступ"),"createReceipeLablePublicOrPrivate");
        mainCustomLayout.addComponent(new Label("Описание"),"createReceipeLableDescription");
        mainCustomLayout.addComponent(new Label("Теги"),"createReceipeLableTags");
        tegs = new ArrayList<>();
        TextArea catalogArea = new TextArea();
        catalogArea.setHeight("100%");
        catalogArea.setWidth("100%");
        catalogArea.setWordWrap(true);
        catalogArea.addValueChangeListener((event) -> {
            if(!event.getValue().equals("")){
                descriptionCatalog = event.getValue();
            }
            else
            {
                descriptionCatalog = null;
            }
        });
        ComboBox catalogCB = new ComboBox();
        
        
        BeansFactory<GMFacade> bf = BeansFactory.getInstance();
        try{
            GMFacade gmFacade = bf.getBean(GMFacade.class);
            List<Catalog> catalogs = gmFacade.getGmCatalogFacade().getAllCatalogs();
            
            for(int i=0; i<catalogs.size(); i++){
                catalogsName.add(catalogs.get(i).getName());
            }  
        }
        catch(Exception exception){
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
        
        
        
        catalogCB.setItems(catalogsName);
        TextField receipesCatalog = new TextField();
        CheckBox useNewCatalog = new CheckBox("Создать новый");
        mainCustomLayout.addComponent(catalogCB,"createReceipeInputCatalog");
        catalogCB.addValueChangeListener((event) -> {
            if(event.getValue() != null){
                if(!event.getValue().toString().equals("")){
                    catalogName = event.getValue().toString();
                    //noListenCatalog = true;
                    receipesCatalog.setValue("");
                    catalogArea.setValue("");
                }
                else
                {
                    catalogName = null;
                }
            }
        });
        catalogCB.setWidth("100%");
        catalogCB.setHeight("100%");
        //mainCustomLayout.addComponent(catalogCB,"createReceipeInputCatalog");
        receipesCatalog.setWidth("100%");
        receipesCatalog.setHeight("100%");
        receipesCatalog.addValueChangeListener((event) -> {
            if(!event.getValue().equals("")){
                catalogName = event.getValue();
                noListenCatalog = true;
                catalogCB.clear();
            }
            else
            {
                catalogName = null;
            }
        });
        mainCustomLayout.addComponent(useNewCatalog,"createReceipeInputCatalog2");
        TextField receipesName = new TextField();
        receipesName.setWidth("100%");
        receipesName.setHeight("100%");
        receipesName.addValueChangeListener((event) -> {
            if(!event.getValue().equals("")){
                recipeName = event.getValue();
            }
            else
            {
                recipeName = null;
            }
        });
        mainCustomLayout.addComponent(receipesName,"createReceipeInputReceipeName");
        TextArea recipeArea = new TextArea();
        recipeArea.setHeight("100%");
        recipeArea.setWidth("100%");
        recipeArea.setWordWrap(true);
        recipeArea.addValueChangeListener((event) -> {
            if(!event.getValue().equals("")){
                descriptionRecipe = event.getValue();
            }
            else
            {
                descriptionRecipe = null;
            }
        });
        mainCustomLayout.addComponent(recipeArea,"createReceipeInputDescriptionRecipe");
        
        useNewCatalog.addValueChangeListener((event) -> {
            if(event.getValue()){
                mainCustomLayout.addComponent(receipesCatalog,"createReceipeInputCatalog");
                mainCustomLayout.addComponent(new Label("Описание нового каталогa"),"createReceipeLableDescriptionCatalog");
                mainCustomLayout.addComponent(catalogArea,"createReceipeInputDescriptionCatalog");
                mainCustomLayout.addComponent(new Label("Название нового каталога"),"createReceipeLableCatalog");
                createNewCatalog = event.getValue();
                catalogName = null;
            }
            else{
                mainCustomLayout.addComponent(catalogCB,"createReceipeInputCatalog");
                mainCustomLayout.addComponent(new Label("Предустановленный Каталог"),"createReceipeLableCatalog");
                mainCustomLayout.addComponent(new Label(""),"createReceipeLableDescriptionCatalog");
                mainCustomLayout.addComponent(new Label(""),"createReceipeInputDescriptionCatalog");
                createNewCatalog = event.getValue();
            }
        });
        
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
        mainCustomLayout.addComponent(tagGrid,"createReceipeInputTags1");
        mainCustomLayout.addComponent(v1,"createReceipeInputTags2");
        //Получение id пользователя
        CookieHandler ch2 = new CookieHandler();
        JWTHandler jwth2 = new JWTHandler();
        Cookie userCookie2 = ch2.getCookieByName("userInfo");
        userId = jwth2.readUserId(userCookie2.getValue(), "test");
        
        mainCustomLayout.setHeight("100%");
        
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        ButtonsClickListener clickListener = new SessionStorageHelper().getListener(attr);
        try{
          
            //Кнопка перехода к построению рецепта
            clickListener.addButtonClickListener(new ClickListener() {
                @Override
                public String getId() {
                    return "createRecipeDoneBtn";
                }

                @Override
                public void onEventDo() {
                    //Создание каталога, или, если он существует, то получение его id
                    BeansFactory<GMFacade> bf2 = BeansFactory.getInstance();
                    try {
                        GMFacade gmFacade = bf2.getBean(GMFacade.class);
                        if(catalogName != null){
                            if(createNewCatalog == false | 
                                    descriptionCatalog != null){
                                    catalogId = gmFacade.getGmCatalogFacade().
                                            createCatalog(catalogName, 
                                                    descriptionCatalog);
                                if(recipeName != null && 
                                        descriptionRecipe != null && 
                                        catalogId != null && userId!= null){
                                            recipeId = gmFacade.
                                                    getGmReceipeFacade().
                                                    addReceipe(recipeName, 
                                                            descriptionRecipe, 
                                                            catalogId,userId, 
                                                            isPublic).
                                                    getReceipeId();

                                    //Добавление всех тегов
                                    for(int i=0; i<tegs.size();i++){
                                        gmFacade.getGmTagFacade().
                                                addTagToReceipe(recipeId, 
                                                tegs.get(i).getName());
                                    }

                                    if(recipeId != null){
                                        listener.onCreate(recipeId, userId);
                                    }
                                }
                                else{
                                    new Notification("This is a error",
                                    "Пожалуйста, проверьте, возможно, Вы не "
                                            + "заполнили поля с название рецепта "
                                            + "или его описанием",
                                    Notification.Type.ERROR_MESSAGE, true)
                                    .show(Page.getCurrent());
                                }
                            }
                            else{
                                new Notification("This is a error",
                                    "Пожалуйста, введите описание нового "
                                            + "каталога",
                                    Notification.Type.ERROR_MESSAGE, true)
                                .show(Page.getCurrent());
                            }
                        }
                    }
                    catch (NotFoundBean exception) {
                        ExceptionHandler.getInstance().runExceptionhandling(exception);
                    }
                }
            });
            //Кнопка перехода отмены
            clickListener.addButtonClickListener(new ClickListener() {
                @Override
                public String getId() {
                    return "createRecipeCancelBtn";
                }

                @Override
                public void onEventDo() {
                    int gg=0;
                }
            });
            //Кнопка загрузить фото
            clickListener.addButtonClickListener(new ClickListener() {
                @Override
                public String getId() {
                    return "createReceipeLoadFotoBtn";
                }

                @Override
                public void onEventDo() {
                    int gg=0;
                }
            });
        }
        catch(Exception exception){
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }

        return mainCustomLayout;
    }
}
