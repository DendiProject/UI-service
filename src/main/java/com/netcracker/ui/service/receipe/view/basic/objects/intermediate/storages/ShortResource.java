/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects.intermediate.storages;

/**
 *
 * @author Artem
 */
public class ShortResource {
    private String resourceId;
    private String name;
    
    public ShortResource()
    {
        
    }
    
    public ShortResource(String resourceId, String name){
        this.resourceId = resourceId;
        this.name = name;
    }
    
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } 
}
