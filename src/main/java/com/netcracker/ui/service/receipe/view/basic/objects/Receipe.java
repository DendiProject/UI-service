/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.netcracker.ui.service.graf.component.Edge;
import com.netcracker.ui.service.graf.component.Node;
import java.util.ArrayList;

/**
 * Представляет собой конкретный рецепт
 * @author Artem
 */
public class Receipe implements Comparable<Receipe>{
    public String receipeName;
    public ArrayList<Node> nodes;
    public ArrayList<Edge> edges;
    
    public  Receipe()
    {

    }
    
    public Receipe(String receipeName, ArrayList<Node> nodes,
            ArrayList<Edge> edges)
    {
        this.receipeName = receipeName;
        this.nodes = nodes;
        this.edges = edges;
    }
    
    public String getReceipeName() {
        return receipeName;
    }

    public void setReceipeName(String receipeName) {
        this.receipeName = receipeName;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public int compareTo(Receipe t) {
        
        if(t.nodes.size() != nodes.size() | t.edges.size() != 
                edges.size() | !t.receipeName.equals(receipeName))
        {
            return 0;
        }
        
        for(int i=0;i<t.nodes.size();i++)
        {
            if(t.nodes.get(i).getNodeId() != nodes.get(i).getNodeId())
            {
                return 0;
            }
        }
        
        for(int i=0;i<t.edges.size();i++)
        {
            if(t.edges.get(i).getEndNodeId()!= 
                    edges.get(i).getEndNodeId()| 
                    t.edges.get(i).getStartNodeId() != 
                    edges.get(i).getStartNodeId())
            {
                return 0;
            }
        }
        
        
        return 1;
    }
}
