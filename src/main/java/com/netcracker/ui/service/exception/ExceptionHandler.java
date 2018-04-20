/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.exception;

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
            public void doOnExceptionAlreadyExists() {
                return;
            }

            @Override
            public void doOnNoFoundException() {
                return;
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
                configuration.doOnExceptionAlreadyExists();
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
                exceptions.get(i).callExceptionHandler();
                return;
            }
        }
        configuration.doOnNoFoundException();
    }
    
    public void setConfigureation(ExceptionHandlersConfiguration configuration)
    {
        this.configuration = configuration;
    }
}
