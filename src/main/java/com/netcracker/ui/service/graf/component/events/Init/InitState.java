/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.Init;

import com.netcracker.ui.service.graf.component.Edge;
import com.netcracker.ui.service.graf.component.Node;
import java.util.ArrayList;

/**
 *
 * @author Artem
 */
public class InitState {
    public ArrayList<Node> nodes;
    public ArrayList<Edge> edges;
    public boolean stateReady = false;
    private int numberOfNonEmptyFeelds = 0;
    String receipeId;
    String userId;
    
    public InitState()
    {
        
    }
    
    public InitState(ArrayList<Node> _nodes, ArrayList<Edge> _edges)
    {
        nodes = _nodes;
        edges = _edges;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
        checkState();
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
        checkState();
    }
    
    private void checkState()
    {
        numberOfNonEmptyFeelds++;
        if(numberOfNonEmptyFeelds == 2)
        {
            stateReady = true;
        }
    }
}
