/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.addition.facades;

import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.graf.component.gmfacade.workers.ResourceWorker;

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
}
