/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.exception;

/**
 * Класс, позволяющий определить тип exception и задать его обработчик
 * @author Artem
 */
public class ConcreteException {
    private Class type;
    private ConcreteExceptionHandler concreteExceptionHandler;
    
    public ConcreteException(ConcreteExceptionHandler concreteExceptionHandler, Class type)
    {
        this.concreteExceptionHandler = concreteExceptionHandler;
        this.type = type;
    }
    
    public Class getType()
    {
        return type;
    }
    
    public void callExceptionHandler(){
        concreteExceptionHandler.handling();
    }
}
