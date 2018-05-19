/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.buttonsClickListener.component.ButtonsClickListener;
import com.netcracker.ui.service.buttonsClickListener.component.ClickListener;
import com.netcracker.ui.service.buttonsClickListener.component.SessionStorageHelper;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.exception.navigator.InternalServerError;
import com.netcracker.ui.service.forms.AddResourse;
import com.netcracker.ui.service.forms.AddStepForm;
import com.netcracker.ui.service.forms.listeners.LoadFormListener;
import com.netcracker.ui.service.graf.component.Graf;
import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.gmfacade.GMFacade;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.PresenterObserver;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.Proxy;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.StoreSubject;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import org.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Artem
 */
public class ShowReceipeView implements View{
    
    public Graf graf;
    private final PresenterObserver presenter;
    public Receipe initReceipe;
    private Proxy proxy;
    private View curentView;
    private Image image;
    private Label nodeDescription;
    ResponsiveLayout contentRowLayout;
    LoadFormListener listener;
    
    public ShowReceipeView(Proxy proxy, StoreSubject store)
    {
        this.proxy = proxy;
        this.curentView = this;
        presenter = new ReceipePresenter(proxy, store, (ShowReceipeView)this);
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
        this.contentRowLayout = contentRowLayout;
        this.listener = listener;
        CustomLayout ShortViewOfReceipeLayout = new CustomLayout("ShowReceipeView");
        ShortViewOfReceipeLayout.setHeight("100%");
        contentRowLayout.setHeight("100%");
        contentRowLayout.addComponent(ShortViewOfReceipeLayout);
        image = new Image();
        String imageName = "http://localhost:8008/images/s3";
        image.setSource(new ExternalResource(imageName));
        image.setHeight("100%");
        image.setWidth("100%");
        ShortViewOfReceipeLayout.addComponent(image, "ReceipeViewerReceipeImage");
        
        nodeDescription = new Label("Пожалуйста, нажмите на любой шаг и здесь "
                + "вы увидите его описание!");
        nodeDescription.setHeight("100%");
        nodeDescription.setWidth("100%");
        ShortViewOfReceipeLayout.addComponent(nodeDescription, "ReceipeViewerReceipeDescription");
        
        graf = new Graf();
        graf.setInitCollections(initReceipe.nodes, initReceipe.edges, proxy.getUserId(),
                proxy.getReceipeId());
        ShortViewOfReceipeLayout.addComponent(graf,"panelWithGraf");
        /*
        //Синхронизация ресурсов и ингредиентов
        for(int i=0; i<initReceipe.getIndredients().size(); i++){
            presenter.updateCurrentRecipesInResourses(initReceipe.getIndredients().
                    get(i), false);
        }
        for(int i=0; i<initReceipe.getResources().size(); i++){
            presenter.updateCurrentRecipesInResourses(initReceipe.getResources().
                    get(i), false);
        }
        */
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        ButtonsClickListener clickListener = new SessionStorageHelper().getListener(attr);
        
        //Добавление слушателя на клик по ЛЮБОЙ ноде
        graf.addHandlerForClickingOnNode((Node node) -> {
            nodeDescription.setValue(node.getDescription());
            image.setSource(new ExternalResource(node.getPictureId()));
        });
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