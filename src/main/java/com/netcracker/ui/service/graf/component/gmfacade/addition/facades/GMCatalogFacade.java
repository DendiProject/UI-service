/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.addition.facades;

import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.graf.component.gmfacade.workers.CatalogWorker;
import org.json.JSONObject;

/**
 *
 * @author Artem
 */
public class GMCatalogFacade {
    private String connectionUrl;

    public GMCatalogFacade(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public String createCatalog(String catalogName, String description)
    {
        try{
            CatalogWorker catalogWorker = new CatalogWorker(connectionUrl);
            return catalogWorker.createCatalog(catalogName, description);
        }
        catch(Exception exception){
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public String getCatalogsId(String catalogeName){
        try{
            CatalogWorker catalogWorker = new CatalogWorker(connectionUrl);
            JSONObject result = catalogWorker.getCatalog(catalogeName);
            return result.getString("catalogId");
        }
        catch(Exception exception){
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public String getCatalogsDescription(String catalogeName){
        try{
            CatalogWorker catalogWorker = new CatalogWorker(connectionUrl);
            JSONObject result = catalogWorker.getCatalog(catalogeName);
            return result.getString("description");
        }
        catch(Exception exception){
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
}
