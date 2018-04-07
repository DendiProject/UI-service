/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component;

import java.util.ArrayList;

/**
 *
 * @author Artem
 */
public class TestClass {
    public String event;
    public int nodesId;
    public ArrayList<Node> nodes;
    public ArrayList<Edge> edges;
    
    public TestClass()
    {
        
    }
    
    public TestClass(String _event, int _nodesId)
    {
        event = _event;
        nodesId = _nodesId;
    }
    
    public String getEvent()
    {
        return event;
    }
    
    public void setEvent(String  event)
    {
        this.event = event;
    }
    
    public int getNodesId()
    {
        return nodesId;
    }
    
    public void setNodesId(int  nodesId)
    {
        this.nodesId = nodesId;
    }
    
    public ArrayList<Node> getNodes()
    {
        return nodes;
    }
    
    public void setNodes(ArrayList<Node> nodes)
    {
        this.nodes = nodes;
    }
    
    public ArrayList<Edge> getEdges()
    {
        return edges;
    }
    
    public void setEdges(ArrayList<Edge> edges)
    {
        this.edges = edges;
    }
}
