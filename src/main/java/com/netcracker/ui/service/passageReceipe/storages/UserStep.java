/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.passageReceipe.storages;

import com.netcracker.ui.service.receipe.view.basic.objects.Resource;
import java.util.List;

/**
 *
 * @author Artem
 */
public class UserStep {
    private List<Resource> resources;
    private List<Resource> indredients;
    
    private String nodeId;
    private String description;
    private String pictureId;
    
    private boolean isLastNode;
    private boolean is404;

    public boolean isIs404() {
        return is404;
    }

    public void setIs404(boolean is404) {
        this.is404 = is404;
    }

    public boolean isIsLastNode() {
        return isLastNode;
    }

    public void setIsLastNode(boolean isLastNode) {
        this.isLastNode = isLastNode;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<Resource> getIndredients() {
        return indredients;
    }

    public void setIndredients(List<Resource> indredients) {
        this.indredients = indredients;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    } 
}
