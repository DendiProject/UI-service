/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

/**
 *
 * @author Artem
 */
public class Resource {
    private String resourceId;
    private String previousNodeId;
    private String name;
    private double resourceNumber;
    
    public Resource()
    {
        
    }
    
    public Resource(String resourceId, String previousNodeId, String name,
            double resourceNumber){
        this.resourceId = resourceId;
        this.previousNodeId = previousNodeId;
        this.name = name;
        this.resourceNumber = resourceNumber;
    }
    
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public double getResourceNumber() {
        return resourceNumber;
    }

    public void setResourceNumber(double resourceNumber) {
        this.resourceNumber = resourceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } 

    public String getPreviousNodeId() {
        return previousNodeId;
    }

    public void setPreviousNodeId(String previousNodeId) {
        this.previousNodeId = previousNodeId;
    }
}
