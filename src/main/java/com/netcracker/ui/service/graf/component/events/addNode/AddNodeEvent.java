/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.addNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.graf.component.Graf;
import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.events.BasicGrafEventHandler;
import com.netcracker.ui.service.graf.component.events.clickOnNode.ClickOnNodeState;
import elemental.json.JsonArray;
import java.util.ArrayList;

/**
 *
 * @author Artem
 */
public class AddNodeEvent extends BasicGrafEventHandler{
    private AddNodeState state;
    
    public AddNodeEvent(Graf graf)
    {
        this.graf = graf;
    }
    
    public void setState(AddNodeState state)
    {
        this.state = state;
    }
    
    @Override
    public void handleEvent(JsonArray arguments) {
        try
        {
            //Попытка распарсить данные, если не получается-отдать следующему
            BeansFactory<ObjectMapper> bf = BeansFactory.getInstance();
            ObjectMapper mapper = bf.getBean(ObjectMapper.class);
            state = mapper.readValue(arguments.getObject(0).toString(),AddNodeState.class);
            if(state.stateReady)
            {
                graf.addNode(state.newNodesImage, state.newNodesLable, state.newNodesId);
                //Оповещаю всех слушателей
                graf.notifyAddNodeEventListeners();
            }
            else
            {
                giveNextHandlerWork(arguments);
            }
        }
        catch(Exception ex)
        {

        }
    }
}
