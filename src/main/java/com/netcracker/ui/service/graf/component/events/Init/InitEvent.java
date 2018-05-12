/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.Init;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.graf.component.Graf;
import com.netcracker.ui.service.graf.component.eventTypes.EventType;
import com.netcracker.ui.service.graf.component.events.BasicGrafEventHandler;
import com.netcracker.ui.service.graf.component.events.addEdge.AddEdgeState;
import elemental.json.JsonArray;

/**
 *
 * @author Artem
 */
public class InitEvent extends BasicGrafEventHandler{
    private InitState state;
    
    public InitEvent(Graf graf)
    {
        this.graf = graf;
    }
    
    public void setState(InitState state)
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
            state = mapper.readValue(arguments.getObject(0).toString(),InitState.class);
            if(state.stateReady)
            {
                
                //Вначале нужно сделать запрос на GM для проверки возможности
                if(false){
                    graf.setEvent(EventType.init, arguments.toJson());
                    //Оповещаю всех слушателей
                    graf.notifyEventListeners(graf.getInitListeners());
                }
                else{
                    //Иначе уведомление пользователя о том, 
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