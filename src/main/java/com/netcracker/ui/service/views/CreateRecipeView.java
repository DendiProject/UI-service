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
import com.netcracker.ui.service.content.handler.CookieHandler;
import com.netcracker.ui.service.content.handler.JWTHandler;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.forms.AuthorizationForm;
import com.netcracker.ui.service.forms.listeners.CreateReceipeListener;
import com.netcracker.ui.service.graf.component.gmfacade.GMFacade;
import com.netcracker.ui.service.receipe.view.basic.objects.Tag;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.Cookie;

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
    private List<Tag> tegs = null;
    CreateReceipeListener listener;
    private boolean noListenCatalog = false;
    
    public CreateRecipeView(CreateReceipeListener listener){
        this.listener = listener;
    }
    
    public CustomLayout create(){
        ResponsiveLayout mainLayout = new ResponsiveLayout();
        CustomLayout mainCustomLayout = new CustomLayout("CreateReceipeView");
        mainLayout.setHeight("100%");
        mainCustomLayout.setHeight("100%");
        mainLayout.addComponent(mainCustomLayout);
        Image image = new Image();
        image.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/AddReceipeImage.jpg")));
        image.setHeight("100%");
        image.setWidth("100%");
        mainCustomLayout.addComponent(image, "CreateReceipesImage");
        mainCustomLayout.addComponent(new Label("Предустановленный Каталог"),"createReceipeLableCatalog");
        mainCustomLayout.addComponent(new Label("Новый Каталог"),"createReceipeLableCatalog2");
        mainCustomLayout.addComponent(new Label("Описание каталогa"),"createReceipeLableDescriptionCatalog");
        mainCustomLayout.addComponent(new Label("Описание рецепта"),"createReceipeLableDescriptionRecipe");
        mainCustomLayout.addComponent(new Label("Название"),"createReceipeLableReceipeName");
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
        mainCustomLayout.addComponent(catalogArea,"createReceipeInputDescriptionCatalog");
        ComboBox catalogCB = new ComboBox();
        
        
        //ДОБАВИТЬ СЮДА ЗАГРУЗКУ КАТАЛОГОВ
        
        
        
        catalogCB.setItems("Тест1", "Тест2", "Тест3", "Тест4");
        TextField receipesCatalog = new TextField();
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
        mainCustomLayout.addComponent(catalogCB,"createReceipeInputCatalog");
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
        mainCustomLayout.addComponent(receipesCatalog,"createReceipeInputCatalog2");
        TextField receipesName = new TextField();
        receipesName.setWidth("100%");
        receipesName.setHeight("100%");
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
        //Получение id пользователя
        CookieHandler ch2 = new CookieHandler();
        JWTHandler jwth2 = new JWTHandler();
        Cookie userCookie2 = ch2.getCookieByName("userInfo");
        userId = jwth2.readUserId(userCookie2.getValue(), "test");
        
        mainCustomLayout.setHeight("100%");
        
        BeansFactory<ButtonsClickListener> bf = BeansFactory.getInstance();
        ButtonsClickListener clickListener;
        try{
            clickListener = bf.getBean(ButtonsClickListener.class);
            //Кнопка перехода к построению рецепта
            clickListener.addButtonClickListener(new ClickListener() {
                @Override
                public String getId() {
                    return "createRecipeDoneBtn";
                }

                @Override
                public void onEventDo() {
                    //Создание каталога, или, если он существует, то получение его id
                    GMFacade gm = new GMFacade("http://localhost:8083/");
                    if(catalogName != null && descriptionCatalog != null){
                        
                        catalogId = gm.getGmCatalogFacade().createCatalog(
                                catalogName, descriptionCatalog);
                        if(recipeName != null && descriptionRecipe != null && 
                                catalogId != null && userId!= null){
                            
                            recipeId = gm.getGmReceipeFacade().addReceipe(
                                    recipeName, descriptionRecipe, catalogId, 
                                    userId, isPublic).getReceipeId();

                            //Добавление всех тегов
                            for(int i=0; i<tegs.size();i++){
                                gm.getGmTagFacade().addTagToReceipe(recipeId, 
                                        tegs.get(i).getName());
                            }
                            
                            if(recipeId != null){
                                listener.onCreate(recipeId, userId);
                            }
                        }
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
