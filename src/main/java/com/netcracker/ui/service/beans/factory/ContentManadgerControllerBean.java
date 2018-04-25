/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.beans.factory;

import com.netcracker.ui.service.beans.factory.basic.objects.interfaces.Product;
import com.netcracker.ui.service.content.handler.ContentManagerController;

/**
 *
 * @author Artem
 */
public class ContentManadgerControllerBean implements Product<ContentManagerController>{
    
    private ContentManagerController content;
    
    public ContentManadgerControllerBean()
    {
        setContent();
    }
    
    @Override
    public void setContent() {
        ContentManagerController controller = new ContentManagerController("http://localhost:8082/");
        content = controller;
    }

    @Override
    public ContentManagerController getContent() {
        return content;
    }
    
}