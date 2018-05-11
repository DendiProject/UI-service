/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.addition.facades;

import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.graf.component.gmfacade.workers.ResourceWorker;
import com.netcracker.ui.service.receipe.view.basic.objects.Resource;
import com.netcracker.ui.service.receipe.view.basic.objects.ShortResource;
import java.util.List;

/**
 *
 * @author Artem
 */
public class GMResourceFacade {
    private String connectionUrl;

    public GMResourceFacade(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public String addResource(String name, String ingredientOrResource, 
            String measuring, String userId, String pictureId){
        try
        {
            ResourceWorker resourceWorker = new ResourceWorker(connectionUrl);
            return resourceWorker.addResource(name, ingredientOrResource, 
                    measuring, userId, pictureId);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public String addResource(String name, String ingredientOrResource, 
            String nodeId){
        try
        {
            ResourceWorker resourceWorker = new ResourceWorker(connectionUrl);
            return resourceWorker.addResource(name, ingredientOrResource, 
                    nodeId);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public List<ShortResource> getResourcesByLetters(String  letters, 
            String ingredientOrResource, int size){
        try
        {
            ResourceWorker resourceWorker = new ResourceWorker(connectionUrl);
            return resourceWorker.getResourcesByLetters(letters, ingredientOrResource, size);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
}
