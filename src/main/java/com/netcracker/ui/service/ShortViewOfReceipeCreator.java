/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.graf.component.Graf;
import com.netcracker.ui.service.graf.component.HandlerForClickingTheNode;
import com.netcracker.ui.service.receipe.Receipe;
import com.netcracker.ui.service.receipe.ReceipeController;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Notification;

/**
 * Используется для создания макета с short view of receipe
 * @author Artem
 */
public class ShortViewOfReceipeCreator {

    public Graf graf;
    public ResponsiveLayout create(ResponsiveLayout contentRowLayout)
    {
        CustomLayout ShortViewOfReceipeLayout = new CustomLayout("ShortViewOfRecipeLayout");
        ShortViewOfReceipeLayout.setHeight("100%");
        contentRowLayout.setHeight("100%");
        contentRowLayout.addComponent(ShortViewOfReceipeLayout);
        
        
        
        
        
        //ReceipeDataStore data= new ReceipeDataStore();
        //data.load();
        //String a = data.result();
        Receipe receipe = new Receipe();
        receipe = ReceipeController.getRecipe(1);
        
        
        
        
        graf = new Graf();
        
        ShortViewOfReceipeLayout.addComponent(graf,"panelWithGraf");
        /*graf.addNode("https://ru4.anyfad.com/items/t1@dd86b0fb-d896-4da6-80b4-7fe5c9e3e17c/samye-milye-kotyata.jpg",
                "Cat",1, new HandlerForClickingTheNode(){
                                @Override
                                public void onEventClickDo() {
                                    Notification.show("Value: " + "gdgdgdgdgdg");
                                }
                            });
        graf.addNode("https://negani.com/uploads/posts/2012-02/1330543487_husky_47.jpg",
                "Dog",2, new HandlerForClickingTheNode(){
                                @Override
                                public void onEventClickDo() {
                                    Notification.show("Value: " + "второй");
                                }
                            });
        graf.addNodesConnection(1,2);
        */
        graf.setNodesCollection(receipe.nodes);
        graf.setNodesConnections(receipe.nodesConnections);
        
        graf.setHandlerForClickingTheNode(1, new HandlerForClickingTheNode(){
                                @Override
                                public void onEventClickDo() {
                                    Notification.show("Value: " + "первый");
                                }
                            });
        /*graf.setHandlerForClickingTheNode(2, new HandlerForClickingTheNode(){
                                @Override
                                public void onEventClickDo() {
                                    Notification.show("Value: " + "второй");
                                }
                            });
        */
        
        
        
        
        return contentRowLayout;
    }
}
