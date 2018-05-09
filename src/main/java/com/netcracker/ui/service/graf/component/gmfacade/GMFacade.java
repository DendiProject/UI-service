/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade;

import com.netcracker.ui.service.graf.component.Edge;
import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.gmfacade.workers.EdgeWorker;
import com.netcracker.ui.service.graf.component.gmfacade.workers.GrafWorker;
import com.netcracker.ui.service.graf.component.gmfacade.workers.NodeWorker;
import org.json.JSONObject;

/**
 *
 * @author Artem
 */
public class GMFacade {
    private String connectionUrl;

    public GMFacade(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public Node addNode(Node node) throws Exception
    {
        try
        {
            NodeWorker nodeWorker = new NodeWorker(connectionUrl);
            //Cоздание новой ноды на gm
            node.setNodeId(nodeWorker.getIdForNewNode("1111","1111"));
            //Добавление к новой ноде description
            nodeWorker.addNodeDescription(node.getNodeId(), node.getDescription());
            //Добавление к новой ноде label
            nodeWorker.addNodeLabel(node.getNodeId(), node.getLabel());
            //Добавление к новой ноде picture
            nodeWorker.addNodePicture(node.getNodeId(), node.getPictureId());
            return node;
        }
        catch(Exception ex)
        {
            throw new Exception();
        }
    }
    
    public void addEdge(Edge edge) throws Exception
    {
        try
        {
            EdgeWorker edgeWorker = new EdgeWorker(connectionUrl);
            edgeWorker.addEdge(edge.getStartNodeId(), edge.getEndNodeId());
        }
        catch(Exception ex)
        {
            throw new Exception();
        }
    }
    
    public void deleteNode(Node node) throws Exception
    {
        try
        {
            NodeWorker nodeWorker = new NodeWorker(connectionUrl);
            nodeWorker.deleteNode(node.getNodeId());
        }
        catch(Exception ex)
        {
            throw new Exception();
        }
    }
    
    public void deleteEdge(Edge edge) throws Exception
    {
        try
        {
            EdgeWorker edgeWorker = new EdgeWorker(connectionUrl);
            edgeWorker.deleteEdge(edge.getStartNodeId(), edge.getEndNodeId());
        }
        catch(Exception ex)
        {
            throw new Exception();
        }
    }
    
    public JSONObject getGraph(String userId, String receipeId) throws Exception
    {
        try
        {
            GrafWorker grafWorker = new GrafWorker(connectionUrl);
            JSONObject result = grafWorker.getGraph(userId, receipeId);
            return result;
        }
        catch(Exception ex)
        {
            throw new Exception();
        }
    }
    
    public JSONObject getParallelGraph(String userId, String receipeId) throws Exception
    {
        try
        {
            GrafWorker grafWorker = new GrafWorker(connectionUrl);
            JSONObject result = grafWorker.getParallelGraph(userId, receipeId);
            return result;
        }
        catch(Exception ex)
        {
            throw new Exception();
        }
    }
    
    public JSONObject getTestGraf(String userId, String receipeId) throws Exception
    {
        try
        {
            GrafWorker grafWorker = new GrafWorker(connectionUrl);
            JSONObject result = grafWorker.getTestGraf(userId, receipeId);
            return result;
        }
        catch(Exception ex)
        {
            throw new Exception();
        }
    }
}
