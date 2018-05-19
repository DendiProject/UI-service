/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.netcracker.ui.service.graf.component.Edge;
import com.netcracker.ui.service.graf.component.Node;
import java.util.ArrayList;
import java.util.List;

/**
 * Представляет собой конкретный рецепт
 * @author Artem
 */
public class Receipe implements Comparable<Receipe>{
    public String receipeName;
    public ArrayList<Node> nodes;
    public ArrayList<Edge> edges;
    private List<Resource> resources;
    private List<Resource> indredients;
    private boolean isParallel;
    
    public  Receipe()
    {

    }
    
    public Receipe(String receipeName, ArrayList<Node> nodes, 
            ArrayList<Edge> edges, List<Resource> resources, 
            List<Resource> indredients, boolean isParallel)
    {
        this.receipeName = receipeName;
        this.nodes = nodes;
        this.edges = edges;
        this.resources = resources;
        this.indredients = indredients;
        this.isParallel = isParallel;
    }

    public boolean isIsParallel() {
        return isParallel;
    }

    public void setIsParallel(boolean isParallel) {
        this.isParallel = isParallel;
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
