/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.addition.facades;

import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.graf.component.gmfacade.workers.GrafWorker;
import com.netcracker.ui.service.receipe.view.basic.objects.Receipe;
import org.json.JSONObject;

/**
 *
 * @author Artem
 */
public class GMGrafFacade {
    private String connectionUrl;

    public GMGrafFacade(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public void addReceipe(Receipe receipe)
    {
        
    }
    
    public JSONObject getGraph(String userId, String receipeId)
    {
        try
        {
            GrafWorker grafWorker = new GrafWorker(connectionUrl);
            JSONObject result = grafWorker.getGraph(userId, receipeId);
            return result;
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public JSONObject getParallelGraph(String userId, String receipeId)
    {
        try
        {
            GrafWorker grafWorker = new GrafWorker(connectionUrl);
            JSONObject result = grafWorker.getParallelGraph(userId, receipeId);
            return result;
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public JSONObject getTestGraf(String userId, String receipeId)
    {
        try
        {
            GrafWorker grafWorker = new GrafWorker(connectionUrl);
            JSONObject result = grafWorker.getTestGraf(userId, receipeId);
            return result;
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public String getNotCompletedGraph(String userId){
        try
        {
            GrafWorker grafWorker = new GrafWorker(connectionUrl);
            String id = grafWorker.getNotCompletedGraph(userId);
            return id;
        }
        catch(Exception exception)
        {
            return "";
        }
    }
}
