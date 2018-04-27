/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.deleteEdge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.graf.component.Graf;
import com.netcracker.ui.service.graf.component.eventTypes.EventType;
import com.netcracker.ui.service.graf.component.events.BasicGrafEventHandler;
import elemental.json.JsonArray;

/**
 *
 * @author Artem
 */
public class DeleteEdgeEvent extends BasicGrafEventHandler{
    private DeleteEdgeState state;
    
    public DeleteEdgeEvent(Graf graf)
    {
        this.graf = graf;
    }
    
    public void setState(DeleteEdgeState state)
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
            state = mapper.readValue(arguments.getObject(0).toString(),DeleteEdgeState.class);
            if(state.stateReady)
            {
                //Вначале нужно сделать запрос на GM для проверки возможности удаления связи
                if(true){
                    graf.deleteEdge(state.deleteEdgeFrom, state.deleteEdgeTo);
                    graf.setEvent(EventType.deleteEdge, arguments.toJson());
                    //Оповещаю всех слушателей
                    graf.notifyEventListeners(graf.getDeleteEdgeListeners());
                }
                else{
                    //Иначе уведомление пользователя о том, что связь не может 
                    //быть удалена
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