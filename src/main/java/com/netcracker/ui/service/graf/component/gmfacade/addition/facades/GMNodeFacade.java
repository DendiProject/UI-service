/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.addition.facades;

import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.gmfacade.workers.NodeWorker;
import java.util.List;
import com.netcracker.ui.service.receipe.view.basic.objects.Resource;

/**
 *
 * @author Artem
 */
public class GMNodeFacade {
    private String connectionUrl;

    public GMNodeFacade(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public Node addNode(Node node, String receipeId, String userId)
    {
        try
        {
            NodeWorker nodeWorker = new NodeWorker(connectionUrl);
            //Cоздание новой ноды на gm
            node.setNodeId(nodeWorker.getIdForNewNode(receipeId,userId));
            //Добавление к новой ноде description
            nodeWorker.addNodeDescription(node.getNodeId(), node.getDescription());
            //Добавление к новой ноде label
            nodeWorker.addNodeLabel(node.getNodeId(), node.getLabel());
            //Добавление к новой ноде picture
            nodeWorker.addNodePicture(node.getNodeId(), node.getPictureId());
            return node;
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public void deleteNode(Node node)
    {
        try
        {
            NodeWorker nodeWorker = new NodeWorker(connectionUrl);
            nodeWorker.deleteNode(node.getNodeId());
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
    }
    
    public void addInputResources(Node node, List<Resource> resources)
    {
        try
        {
            NodeWorker nodeWorker = new NodeWorker(connectionUrl);
            nodeWorker.addInputResources(node.getNodeId(), resources);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
    }
    
    public void addOutputResources(Node node, List<Resource> resources)
    {
        try
        {
            NodeWorker nodeWorker = new NodeWorker(connectionUrl);
            nodeWorker.addOutputResources(node.getNodeId(), resources);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
    }
    
    public List<Resource> getInputResources(Node node, 
            String ingredientOrResource ){
        try
        {
            NodeWorker nodeWorker = new NodeWorker(connectionUrl);
            return nodeWorker.getInputResources(node.getNodeId(), 
                    ingredientOrResource);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public List<Resource> getOutputResources(Node node, 
            String ingredientOrResource ){
        try
        {
            NodeWorker nodeWorker = new NodeWorker(connectionUrl);
            return nodeWorker.getOutputResources(node.getNodeId(), 
                    ingredientOrResource);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
}
