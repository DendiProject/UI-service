/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.graf.component.Graf;
import com.netcracker.ui.service.graf.component.HandlerForClickingTheNode;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Notification;

/**
 * Используется для создания макета с short view of receipe
 * @author Artem
 */
public class ShortViewOfReceipeCreator {

    public Graf graf;
    public ResponsiveLayout create(ResponsiveLayout contentRowLayout){
        CustomLayout ShortViewOfReceipeLayout = new CustomLayout("ShortViewOfRecipeLayout");
        ShortViewOfReceipeLayout.setHeight("100%");
        contentRowLayout.setHeight("100%");
        contentRowLayout.addComponent(ShortViewOfReceipeLayout);

        Receipe receipe = new Receipe();
        receipe = ReceipeController.getRecipe(1);
        
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
