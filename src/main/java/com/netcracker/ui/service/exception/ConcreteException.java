/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.exception;

import com.netcracker.ui.service.exception.importanceTypes.BasicImportanceClass;
import com.netcracker.ui.service.exception.importanceTypes.*;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс, позволяющий определить тип exception и задать его обработчик
 * @author Artem
 */
public class ConcreteException {
    final static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    private Class type;
    private ConcreteExceptionHandler concreteExceptionHandler;
    private String exceptionMessage;
    private String moreAboutException;
    BasicImportanceClass exceptionType;
    
    public ConcreteException(ConcreteExceptionHandler concreteExceptionHandler, 
            Class type, String exceptionMessage, String moreAboutException,
            BasicImportanceClass exceptionType)
    {
        this.concreteExceptionHandler = concreteExceptionHandler;
        this.type = type;
        this.exceptionMessage = exceptionMessage;
        this.moreAboutException = moreAboutException;
        this.exceptionType = exceptionType;
    }
    
    public Class getType()
    {
        return type;
    }
    
    public void callExceptionHandler(Exception exception){
        if(exceptionType instanceof InformationMessage){
            //Обработка логером
            logger.info("Information: "+moreAboutException+"Exception message: "+exception.getMessage());
            //Обработка нотификацией
            new Notification("This is a information",
                    exceptionMessage,
                    Notification.Type.HUMANIZED_MESSAGE, true)
            .show(Page.getCurrent());
        }
        if(exceptionType instanceof WarningMessage){
            //Обработка логером
            logger.warn("Information: "+moreAboutException+"Exception message: "+exception.getMessage());
            //Обработка нотификацией
            new Notification("This is a warning",
                    exceptionMessage,
                    Notification.Type.WARNING_MESSAGE, true)
            .show(Page.getCurrent());
        }
        if(exceptionType instanceof ErrorMessage){
            //Обработка логером
            logger.error("Information: "+moreAboutException+"Exception message: "+exception.getMessage());
            //Обработка нотификацией
            new Notification("This is a error",
                    exceptionMessage,
                    Notification.Type.ERROR_MESSAGE, true)
            .show(Page.getCurrent());
        }
           
        //Обработка определенным handler - ом
        concreteExceptionHandler.handling(exception);
    }
}
