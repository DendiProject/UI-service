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
    
    @Override
    public void handleEvent(JsonArray arguments) {
        try
        {
            //Попытка распарсить данные, если не получается-отдать следующему
            BeansFactory<ObjectMapper> bf = BeansFactory.getInstance();
            ObjectMapper mapper = bf.getBean(ObjectMapper.class);
            state = mapper.readValue(arguments.getObject(0).toString(),ClickOnNodeState.class);

            if(state.stateReady)
            {
                //Оповещение слушателей клика по конкретной ноде
                graf.notifyClickOnConcreteNodeEventListeners(state.nodesIdClick);
                //Оповещаю всех слушателей
                graf.notifyClickOnNodeEventListeners();
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
