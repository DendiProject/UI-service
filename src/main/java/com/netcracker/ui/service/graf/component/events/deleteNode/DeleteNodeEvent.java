/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.deleteNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.graf.component.Graf;
import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.eventTypes.EventType;
import com.netcracker.ui.service.graf.component.events.BasicGrafEventHandler;
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
                //Вначале нужно сделать запрос на GM для проверки возможности удаления ноды
                Node oldNode = new Node(state.deleteNodesId,"", "", "");
                try{
                    graf.getGmFacade().getGmNodeFacade().deleteNode(oldNode);
                    graf.deleteNode(state.deleteNodesId);
                    graf.setEvent(EventType.deleteNode, arguments.toJson());
                    //Оповещаю всех слушателей
                    graf.notifyEventListeners(graf.getDeleteNodeListeners());
                }
                catch(Exception exception){
                    //Иначе уведомление пользователя о том, что нода не может 
                    //быть удалена
                    ExceptionHandler.getInstance().runExceptionhandling(exception);
                }
                if(true){
                    
                }
                else{
                    
                }
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
