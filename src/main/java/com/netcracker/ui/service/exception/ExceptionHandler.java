/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.exception;

import static com.netcracker.ui.service.exception.ConcreteException.logger;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Класс для реализации стратегии обработки exceptions
 * @author Artem
 */
public class ExceptionHandler{
    private ExceptionHandlersConfiguration configuration;
    private ArrayList<ConcreteException> exceptions;
    private static ExceptionHandler instance = null;
    
    private ExceptionHandler()
    {
        exceptions = new ArrayList<>();
        configuration = new ExceptionHandlersConfiguration() {
            @Override
            public void doOnExceptionAlreadyExists(String type) {
                //Обработка логером
                logger.error("Information: Нельзя добавить два обработчика "
                        + "ошибок одного типа: "+type+" Exception message: "
                                + "You cannot add two error handlers of the "
                                + "same type: "+type);
                //Обработка нотификацией
                new Notification("This is a error",
                        "Нельзя добавить два обработчика "
                        + "ошибок одного типа: "+type,
                        Notification.Type.ERROR_MESSAGE, true)
                .show(Page.getCurrent());
            }

            @Override
            public void doOnNoFoundException(String type) {
                //Обработка логером
                logger.error("Information: Неизвестное исключение типа: "+type+
                        " Exception message: Unknown exception: "+type);
                //Обработка нотификацией
                new Notification("This is a error",
                        "Неизвестное исключение типа: "+type,
                        Notification.Type.ERROR_MESSAGE, true)
                .show(Page.getCurrent());
            }
        };
    }
    
    public static synchronized ExceptionHandler getInstance()
    {
        if(instance == null)
        {
            instance = new ExceptionHandler();
        }
        
        return instance;
    }
    
    public void addException(ConcreteException exception)
    {
        if(exceptions.isEmpty())
        {
            exceptions.add(exception);
            return;
        }
        for(int i=0;i<exceptions.size();i++)
        {
            if(exception.getType().equals(exceptions.get(i).getType()))
            {
                configuration.doOnExceptionAlreadyExists(exception.
                        getType().toString());
                return;
            }
        }
        exceptions.add(exception);
    }
    
    public void runExceptionhandling(Exception exception)
    {
        for(int i=0;i<exceptions.size();i++)
        {
            if(exception.getClass().equals(exceptions.get(i).getType()))
            { 
                exceptions.get(i).callExceptionHandler(exception);
                return;
            }
        }
        configuration.doOnNoFoundException(exception.getClass().toString());
    }
    
    public void setConfigureation(ExceptionHandlersConfiguration configuration)
    {
        this.configuration = configuration;
    }
}
