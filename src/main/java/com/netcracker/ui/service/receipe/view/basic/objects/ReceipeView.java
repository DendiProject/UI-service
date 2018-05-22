/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.AddIngredient;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.buttonsClickListener.component.ButtonsClickListener;
import com.netcracker.ui.service.buttonsClickListener.component.ClickListener;
import com.netcracker.ui.service.buttonsClickListener.component.SessionStorageHelper;
import com.netcracker.ui.service.content.handler.ContentManagerController;
import com.netcracker.ui.service.content.handler.CookieHandler;
import com.netcracker.ui.service.content.handler.JWTHandler;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.exception.navigator.InternalServerError;
import com.netcracker.ui.service.exception.receipe.view.ConnectionErrorException;
import com.netcracker.ui.service.exception.receipe.view.ConvertDataException;
import com.netcracker.ui.service.forms.AddResourse;
import com.netcracker.ui.service.forms.AddStepForm;
import com.netcracker.ui.service.forms.NoReadyReceipeForm;
import com.netcracker.ui.service.forms.listeners.LoadFormListener;
import com.netcracker.ui.service.graf.component.Graf;
import com.netcracker.ui.service.graf.component.eventTypes.EventType;
import com.netcracker.ui.service.graf.component.gmfacade.GMFacade;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.PresenterObserver;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.Proxy;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.StoreSubject;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.View;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Grid;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Artem
 */
public class ReceipeView implements View{
    
    private Graf graf;
    private final PresenterObserver presenter;
    public Receipe initReceipe;
    private Proxy proxy;
    private View curentView;
    
    
    public ReceipeView(Proxy proxy, StoreSubject store)
    {
        this.proxy = proxy;
        this.curentView = this;
        presenter = new ReceipePresenter(proxy, store, (ReceipeView)this);
    }
    
    @Override
    public void reload() throws NotFoundBean,InternalServerError{
        //Вызов функции load PresenterObserver
        presenter.load();
    }

    @Override
    public void setNewViewsData(Receipe object) {
        initReceipe = (Receipe)object;
    }

    @Override
    public void updateCurrentRecipesInResourses(Resource resource, 
            boolean increment){
        presenter.updateCurrentRecipesInResourses(resource, increment);
    }
    
    @Override
    public ResponsiveLayout drawReceipe(ResponsiveLayout contentRowLayout, 
            LoadFormListener listener) {
        
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
        //Поправить на класс с ингредиентами?
        Grid<Resource> eingredientGrid = new Grid<>();
        LinkedList<Resource> eingredientList = new LinkedList<>();
        eingredientGrid.setSizeFull();
        
        // Set the data provider (ListDataProvider<Resource>)
        //resourceList.add(new Resource("1", null, "alsh0415Resoruce", 1, "шт", null, "ingridient"));
        ListDataProvider<Resource> dataProviderIng = new ListDataProvider<Resource>(eingredientList);
        eingredientGrid.setDataProvider(dataProviderIng);
        
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
        
        CustomLayout ShortViewOfReceipeLayout = new CustomLayout("ShortViewOfRecipeLayout");
        ShortViewOfReceipeLayout.setHeight("100%");
        contentRowLayout.setHeight("100%");
        contentRowLayout.addComponent(ShortViewOfReceipeLayout);
        
        graf = new Graf();
        graf.setInitCollections(initReceipe.nodes, initReceipe.edges, proxy.getUserId(),
                proxy.getReceipeId());
        ShortViewOfReceipeLayout.addComponent(graf,"panelWithGraf");
        ShortViewOfReceipeLayout.addComponent(eingredientGrid,"panelreceipePartsTable");
        ShortViewOfReceipeLayout.addComponent(resourceGrid,"panelreceipeResoursesTable");
        
        //Синхронизация ресурсов и ингредиентов
        for(int i=0; i<initReceipe.getIndredients().size(); i++){
            presenter.updateCurrentRecipesInResourses(initReceipe.getIndredients().
                    get(i), false);
        }
        for(int i=0; i<initReceipe.getResources().size(); i++){
            presenter.updateCurrentRecipesInResourses(initReceipe.getResources().
                    get(i), false);
        }
        
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        ButtonsClickListener clickListener = new SessionStorageHelper().getListener(attr);    
        if (clickListener.listeners.size() >6){
          int size = clickListener.listeners.size();
          for(int i=6;i<size;i++){
             clickListener.listeners.remove(6);
          } 
        }
       
        try{
           
            clickListener.addButtonClickListener(new ClickListener() {
                @Override
                public String getId() {
                    return "networkAddNodeBtn";
                }

                @Override
                public void onEventDo() {
                    AddStepForm addStepForm = new AddStepForm((node, ingredients) -> {
                        //Декремент оставшихся ингредиентов
                        for(int i=0; i<ingredients.size();i++){
                           presenter.updateCurrentRecipesInResourses(
                                   ingredients.get(i), false); 
                        }
                        
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("newNodesId", node.getNodeId());
                        jsonObject.put("newNodesLable",node.getLabel());
                        jsonObject.put("newNodesImage", node.getPictureId().
                                split("/")[node.getPictureId().split("/").
                                        length -1]);
                        jsonObject.put("newNodesX","");
                        jsonObject.put("newNodesY","");
                        jsonObject.put("userId","");
                        jsonObject.put("receipeId","");
                        jsonObject.put("newNodesDescription",node.getDescription());
                        graf.getAddNodeEvent().handleEvent(jsonObject);
                    }, proxy.getReceipeId(), presenter.getCurrentResources(true), 
                    presenter.getCurrentResources(false));
                    listener.onCreate(addStepForm);
                    //addWindow(addStepForm);
                }
            });
            clickListener.addButtonClickListener(new ClickListener() {
                @Override
                public String getId() {
                    return "networkCreateReceipeBtn";
                }

                @Override
                public void onEventDo() {
                    graf.getGmFacade().getGmReceipeFacade().setReceipeCompleted(
                            proxy.getReceipeId());
                }
            });
            clickListener.addButtonClickListener(new ClickListener() {
                @Override
                public String getId() {
                    return "addReceipePartsBtn";
                }

                @Override
                public void onEventDo() {
                    AddResourse addIngredient = new AddResourse(false, 
                            proxy.getUserId(), proxy.getReceipeId(),curentView ,
                            (resource, receipeId, userId, view) -> {
                                //presenter.updateCurrentRecipesInResourses(
                                        //resource,  true);
                                //Обновление входных ресурсов
                                try{
                                    //Добавили(обновили) входные ресурсы для 
                                    //рецепта на gm
                                    BeansFactory<GMFacade> bf = BeansFactory.
                                            getInstance();
                                    GMFacade gmFacade = bf.
                                            getBean(GMFacade.class);
                                    gmFacade.getGmReceipeFacade().
                                            addReceipeResource(receipeId, 
                                                    userId,
                                                    resource.getResourceId(), 
                                                    resource.getResourceNumber()
                                            );
                                    //Обновили стейт доступных ресурсов на вью
                                    view.updateCurrentRecipesInResourses(
                                            resource, true);
                                    //ДОБАВИТЬ СЮДА ВЫВОД В СООТВЕСТВУЮЩУЮ ТАБЛИЦУ
                                    eingredientList.add(resource);
                                    eingredientGrid.getDataProvider().refreshAll();
                                }
                                catch(Exception exception){
                                    ExceptionHandler.getInstance().
                                            runExceptionhandling(exception);
                                }
                            });
                    listener.onCreate(addIngredient);
                }
            });
            
            clickListener.addButtonClickListener(new ClickListener() {
                @Override
                public String getId() {
                    return "addReceipeResoursesBtn";
                }

                @Override
                public void onEventDo() {
                    AddResourse addIngredient = new AddResourse(true, 
                            proxy.getUserId(), proxy.getReceipeId(),curentView ,
                            (resource, receipeId, userId, view) -> {
                                //presenter.updateCurrentRecipesInResourses(
                                        //resource,  true);
                                //Обновление входных ресурсов
                                try{
                                    //Добавили(обновили) входные ресурсы для 
                                    //рецепта на gm
                                    BeansFactory<GMFacade> bf = BeansFactory.
                                            getInstance();
                                    GMFacade gmFacade = bf.
                                            getBean(GMFacade.class);
                                    gmFacade.getGmReceipeFacade().
                                            addReceipeResource(receipeId, 
                                                    userId,
                                                    resource.getResourceId(), 
                                                    resource.getResourceNumber()
                                            );
                                    //Обновили стейт доступных ресурсов на вью
                                    view.updateCurrentRecipesInResourses(
                                            resource, true);
                                    //ДОБАВИТЬ СЮДА ВЫВОД В СООТВЕСТВУЮЩУЮ ТАБЛИЦУ
                                    resourceList.add(resource);
                                    resourceGrid.getDataProvider().refreshAll();
                                }
                                catch(Exception exception){
                                    ExceptionHandler.getInstance().
                                            runExceptionhandling(exception);
                                }
                            });
                    listener.onCreate(addIngredient);
                }
            });
        }
        catch(Exception exception){
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
        
        
        
        //Пример добавления слушателя на клик по ЛЮБОЙ ноде
        /*graf.addHandlerForClickingOnNode(new ClickOnNodeEventListener() {
            @Override
            public void onEventDo() {
                Notification.show("Все получилось!!!!!!");
            }
        });*/
        //Пример добавления слушателя на клик по КОНКРЕТНОЙ ноде
        /*
        graf.setHandlerForClickingTheNode(1, new HandlerForClickingTheNode(){
            @Override
            public void onEventClickDo() {
                Notification.show("Value: " + "первый");
            }
        });*/
        return contentRowLayout;
    }
}
