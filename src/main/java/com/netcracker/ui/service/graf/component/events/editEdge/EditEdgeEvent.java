/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.editEdge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.graf.component.Edge;
import com.netcracker.ui.service.graf.component.Graf;
import com.netcracker.ui.service.graf.component.eventTypes.EventType;
import com.netcracker.ui.service.graf.component.events.BasicGrafEventHandler;
import elemental.json.JsonArray;

/**
 *
 * @author Artem
 */
public class EditEdgeEvent extends BasicGrafEventHandler{
    private EditEdgeState state;
    
    public EditEdgeEvent(Graf graf)
    {
        this.graf = graf;
    }
    
    public void setState(EditEdgeState state)
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
            state = mapper.readValue(arguments.getObject(0).toString(),EditEdgeState.class);
            if(state.stateReady)
            {
                //Вначале нужно сделать запрос на GM для проверки возможности 
                //редактирования связи
                Edge newEdge = new Edge(state.editableEdgesNewIdFrom, 
                        state.editableEdgesNewIdTo);
                Edge oldEdge = new Edge(state.editableEdgesOldIdFrom, 
                        state.editableEdgesOldIdTo);
                try{
                    graf.getGmFacade().getGmEdgeFacade().deleteEdge(oldEdge);
                    graf.getGmFacade().getGmEdgeFacade().addEdge(newEdge);
                    graf.editEdge(state.editableEdgesOldIdFrom, 
                            state.editableEdgesOldIdTo, 
                            state.editableEdgesNewIdFrom, 
                            state.editableEdgesNewIdTo);
                    graf.setEvent(EventType.editEdge, arguments.toJson());
                    //Оповещаю всех слушателей
                    graf.notifyEventListeners(graf.getEditEdgeListeners());
                }
                catch(Exception exception){
                    //Иначе уведомление пользователя о том, что связь не может 
                    //быть таким образом отредактированна
                    ExceptionHandler.getInstance().runExceptionhandling(exception);
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
