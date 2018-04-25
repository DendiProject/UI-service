/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events;

import com.netcracker.ui.service.graf.component.Graf;
import elemental.json.JsonArray;

/**
 *
 * @author Artem
 */
public abstract class BasicGrafEventHandler implements GrafsEventHandler{
    private BasicGrafEventHandler nextHandler;
    protected Graf graf;
    
    public void setNext(BasicGrafEventHandler nextHandler)
    {
        this.nextHandler = nextHandler;
    }
    
    protected void giveNextHandlerWork(JsonArray arguments)
    {
        nextHandler.handleEvent(arguments);
    }
}
