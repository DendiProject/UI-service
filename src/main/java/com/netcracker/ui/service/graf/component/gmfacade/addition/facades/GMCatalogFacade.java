/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.addition.facades;

import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.graf.component.gmfacade.workers.CatalogWorker;
import com.netcracker.ui.service.receipe.view.basic.objects.Catalog;
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
    
    public String createCatalog(String catalogName, String descriptionId)
    {
        try{
            CatalogWorker catalogWorker = new CatalogWorker(connectionUrl);
            return catalogWorker.createCatalog(catalogName, descriptionId);
        }
        catch(Exception exception){
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public Catalog getCatalog(String catalogeName){
        try{
            CatalogWorker catalogWorker = new CatalogWorker(connectionUrl);
            return catalogWorker.getCatalog(catalogeName);
        }
        catch(Exception exception){
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
}
