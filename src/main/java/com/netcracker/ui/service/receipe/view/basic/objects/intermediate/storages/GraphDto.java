/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects.intermediate.storages;

import com.netcracker.ui.service.graf.component.Edge;
import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.receipe.view.basic.objects.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Artem
 */
public class GraphDto {
    private List<Resource> resources;
    private List<Resource> indredients;
    private List<Node> nodes;
    private List<Edge> edges;
    private String receipeName;

    public GraphDto() {
        this.edges=new ArrayList<>();
        this.nodes=new ArrayList<>();
        this.resources=new ArrayList<>();
        this.indredients=new ArrayList<>();
    }

    public GraphDto(List<Resource> _resources, List<Resource> _indredients, 
            List<Node> _nodes, List<Edge> _edges, String _receipeName){
        this.resources = _resources;
        this.indredients = _indredients;
        this.nodes = _nodes;
        this.edges = _edges;
        this.receipeName = _receipeName;
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

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public String getReceipeName() {
        return receipeName;
    }

    public void setReceipeName(String receipeName) {
        this.receipeName = receipeName;
    }
}

