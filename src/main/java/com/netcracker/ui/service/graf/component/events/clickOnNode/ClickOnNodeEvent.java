/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.clickOnNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.graf.component.Graf;
import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.TestClass;
import com.netcracker.ui.service.graf.component.events.BasicGrafEventHandler;
import elemental.json.JsonArray;
import java.util.ArrayList;

/**
 *
 * @author Artem
 */
public class ClickOnNodeEvent extends BasicGrafEventHandler{
    private ClickOnNodeState state;
    
    public ClickOnNodeEvent(Graf graf)
    {
        this.graf = graf;
    }
    
    public void setState(ClickOnNodeState state)
    {
        this.state = state;
    }
    
    @Override
    public void handleEvent(JsonArray arguments) {
        //Попытка распарсить данные, если не получается-отдать следующему
        try
        {
            BeansFactory<ObjectMapper> bf = BeansFactory.getInstance();
            ObjectMapper mapper = bf.getBean(ObjectMapper.class);
            state = mapper.readValue(arguments.getObject(0).toString(),ClickOnNodeState.class);
            
            ArrayList<Node> nodes = graf.getNodesCollection();
        
            //Если существует обработчик, который создан спцеиально для 
            //этой ноды, то вызов его
            for(int i=0; i<nodes.size(); i++)
            {
                if(nodes.get(i).getId() == state.nodesIdClick)
                {        
                    if(nodes.get(i).checkHandlerState())
                    {
                        nodes.get(i).onEventClickDo();
                    }
                    break;
                }
            }
            //Оповещаю всех слушателей
            graf.notifyClickOnNodeEventListeners();
        }
        catch(Exception ex)
        {
            giveNextHandlerWork(arguments);
        }
    }
}
