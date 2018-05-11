/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.addition.facades;

import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.graf.component.gmfacade.workers.ResourceWorker;
import com.netcracker.ui.service.graf.component.gmfacade.workers.TagWorker;
import com.netcracker.ui.service.receipe.view.basic.objects.intermediate.storages.ShortReceipe;
import com.netcracker.ui.service.receipe.view.basic.objects.Tag;
import java.util.List;

/**
 *
 * @author Artem
 */
public class GMTagFacade {
    private String connectionUrl;

    public GMTagFacade(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public void addTagToReceipe(String receipeId,String tagName){
        try
        {
            TagWorker tagWorker = new TagWorker(connectionUrl);
            tagWorker.addTagToReceipe(receipeId, tagName);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
    }
    
    public List<ShortReceipe> getReceipesByTag(String  tagName, int size ){
        try
        {
            TagWorker tagWorker = new TagWorker(connectionUrl);
            return tagWorker.getReceipesByTag(tagName, size);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public List<Tag> getTagsByLetters(String  letters, int size ){
        try
        {
            TagWorker tagWorker = new TagWorker(connectionUrl);
            return tagWorker.getTagsByLetters(letters, size);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
}
