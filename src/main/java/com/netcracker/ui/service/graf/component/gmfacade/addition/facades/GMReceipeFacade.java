/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.addition.facades;

import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.graf.component.gmfacade.workers.ReceipeWorker;
import com.netcracker.ui.service.graf.component.gmfacade.workers.ResourceWorker;
import com.netcracker.ui.service.receipe.view.basic.objects.intermediate.storages.ReceipeInformation;
import com.netcracker.ui.service.receipe.view.basic.objects.intermediate.storages.ShortReceipe;
import java.util.List;

/**
 *
 * @author Artem
 */
public class GMReceipeFacade {
    private String connectionUrl;

    public GMReceipeFacade(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public ShortReceipe addReceipe(String name, String descriptionId, 
            String catalogId, String userId, boolean isPublic){
        try
        {
            ReceipeWorker receipeWorker = new ReceipeWorker(connectionUrl);
            return receipeWorker.addReceipe(name, descriptionId, catalogId, 
                    userId, isPublic);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public ReceipeInformation getReceipeInfo(String receipeId){
        try
        {
            ReceipeWorker receipeWorker = new ReceipeWorker(connectionUrl);
            return receipeWorker.getReceipeInfo(receipeId);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public void setReceipeCompleted(String receipeId){
        try
        {
            ReceipeWorker receipeWorker = new ReceipeWorker(connectionUrl);
            receipeWorker.setReceipeCompleted(receipeId);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
    }
    
    public void deleteReceipe(String receipeId, String userId){
        try
        {
            ReceipeWorker receipeWorker = new ReceipeWorker(connectionUrl);
            receipeWorker.deleteReceipe(receipeId, userId);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
    }
    
    public String addReceipeResource(String receipeId, String userId, 
            String resourceId, double resourceNumber){
        try
        {
            ReceipeWorker receipeWorker = new ReceipeWorker(connectionUrl);
            return receipeWorker.addReceipeResource(receipeId, userId, 
                    resourceId, resourceNumber);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public List<ShortReceipe> getPublicAndCompletesReceipesByCatalogId(
            String catalogId, int size){
        try
        {
            ReceipeWorker receipeWorker = new ReceipeWorker(connectionUrl);
            return receipeWorker.getPublicAndCompletesReceipesByCatalogId(
                    catalogId, size);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
}
