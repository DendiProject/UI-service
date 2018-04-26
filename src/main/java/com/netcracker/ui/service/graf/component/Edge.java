/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component;

import java.io.Serializable;

/**
 *
 * @author Artem
 */
public class Edge  implements Serializable
{
    private String startNodeId;
    private String endNodeId;
    
    public Edge()
    {
        
    }
    
    public Edge(String _startNodeId, String _endNodeId)
    {
        startNodeId = _startNodeId;
        endNodeId = _endNodeId;
    }

    public String getStartNodeId() {
        return startNodeId;
    }

    public void setStartNodeId(String startNodeId) {
        this.startNodeId = startNodeId;
    }

    public String getEndNodeId() {
        return endNodeId;
    }

    public void setEndNodeId(String endNodeId) {
        this.endNodeId = endNodeId;
    }
}
