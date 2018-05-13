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
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.exception.navigator.InternalServerError;
import com.netcracker.ui.service.exception.receipe.view.ConnectionErrorException;
import com.netcracker.ui.service.exception.receipe.view.ConvertDataException;
import com.netcracker.ui.service.graf.component.Graf;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.PresenterObserver;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.Proxy;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.StoreSubject;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.View;
import com.vaadin.ui.CustomLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Artem
 */
public class ReceipeView implements View{
    
    public Graf graf;
    private final PresenterObserver presenter;
    public Receipe receipe;
    private Proxy proxy;
    
    public ReceipeView(Proxy proxy, StoreSubject store)
    {
        this.proxy = proxy;
        presenter = new ReceipePresenter(proxy, store, (ReceipeView)this);
    }
    
    @Override
    public void reload() throws NotFoundBean,InternalServerError{
        //Вызов функции load PresenterObserver
        presenter.load();
    }

    @Override
    public void setNewViewsData(Receipe object) {
        receipe = (Receipe)object;
    }

    @Override
    public ResponsiveLayout drawReceipe(ResponsiveLayout contentRowLayout) {
        CustomLayout ShortViewOfReceipeLayout = new CustomLayout("ShortViewOfRecipeLayout");
        ShortViewOfReceipeLayout.setHeight("100%");
        contentRowLayout.setHeight("100%");
        contentRowLayout.addComponent(ShortViewOfReceipeLayout);
        
        graf = new Graf();
        graf.setInitCollections(receipe.nodes, receipe.edges, proxy.getUserId(),
                proxy.getReceipeId());
        ShortViewOfReceipeLayout.addComponent(graf,"panelWithGraf");
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
