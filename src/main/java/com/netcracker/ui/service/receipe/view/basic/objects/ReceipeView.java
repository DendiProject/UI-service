/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.graf.component.Graf;
import com.netcracker.ui.service.graf.component.HandlerForClickingTheNode;
import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.NodesConnection;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.PresenterObserver;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.Proxy;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.StoreSubject;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.View;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Notification;
import java.util.ArrayList;

/**
 *
 * @author Artem
 */
public class ReceipeView implements View{
    
    public Graf graf;
    private final PresenterObserver presenter;
    public Receipe receipe;
    
    public ReceipeView(Proxy proxi, StoreSubject store)
    {
        presenter = new ReceipePresenter(proxi, store, (ReceipeView)this);
    }
    
    @Override
    public void reload() {
        //Вызов функции load PresenterObserver
        presenter.load();
    }

    @Override
    public void setNewViewsData(Receipe object) {
        if(object instanceof Receipe)
        {
            receipe = (Receipe)object;
        }
        else
        {
            //Добавить код исключения
        }
    }

    @Override
    public ResponsiveLayout drawReceipe(ResponsiveLayout contentRowLayout) {
        CustomLayout ShortViewOfReceipeLayout = new CustomLayout("ShortViewOfRecipeLayout");
        ShortViewOfReceipeLayout.setHeight("100%");
        contentRowLayout.setHeight("100%");
        contentRowLayout.addComponent(ShortViewOfReceipeLayout);
        
        graf = new Graf();
        graf.setNodesCollection(receipe.steps);
        graf.setNodesConnections(receipe.stepsConnections);
        ShortViewOfReceipeLayout.addComponent(graf,"panelWithGraf");
        
        graf.setHandlerForClickingTheNode(1, new HandlerForClickingTheNode(){
                                @Override
                                public void onEventClickDo() {
                                    Notification.show("Value: " + "первый");
                                }
                            });
        return contentRowLayout;
    }
    
}
