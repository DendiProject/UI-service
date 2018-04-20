/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.deleteNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.graf.component.Graf;
import com.netcracker.ui.service.graf.component.events.BasicGrafEventHandler;
import com.netcracker.ui.service.graf.component.events.deleteEdge.DeleteEdgeState;
import elemental.json.JsonArray;

/**
 *
 * @author Artem
 */
public class DeleteNodeEvent extends BasicGrafEventHandler{
    private DeleteNodeState state;
    
    public DeleteNodeEvent(Graf graf)
    {
        this.graf = graf;
    }
    
    public void setState(DeleteNodeState state)
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
            state = mapper.readValue(arguments.getObject(0).toString(),DeleteNodeState.class);
            if(state.stateReady)
            {
                graf.deleteNode(state.deleteNodesId);
                //Оповещаю всех слушателей
                graf.notifyDeleteNodeEventListeners();
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
