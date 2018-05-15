/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.addition.facades;

import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.graf.component.Edge;
import com.netcracker.ui.service.graf.component.gmfacade.workers.EdgeWorker;

/**
 *
 * @author Artem
 */
public class GMEdgeFacade {
    private String connectionUrl;

    public GMEdgeFacade(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public void addEdge(Edge edge) throws Exception
    {
        EdgeWorker edgeWorker = new EdgeWorker(connectionUrl);
        edgeWorker.addEdge(edge.getStartNodeId(), edge.getEndNodeId());
    }
    
    public void deleteEdge(Edge edge) throws Exception
    {
        EdgeWorker edgeWorker = new EdgeWorker(connectionUrl);
        edgeWorker.deleteEdge(edge.getStartNodeId(), edge.getEndNodeId());
    }
}
