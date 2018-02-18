/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe;

import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.NodesConnection;
import java.util.ArrayList;

/**
 * Представляет собой конкретный рецепт
 * @author Artem
 */
public class Receipe {
    public String receipesName;
    public ArrayList<Node> steps;
    public ArrayList<NodesConnection> stepsConnections;
    
    public Receipe(){
        
    }
    
    public Receipe(String _receipesName, ArrayList<Node> _steps, ArrayList<NodesConnection> _stepsConnections){
        receipesName = _receipesName;
        steps = _steps;
        stepsConnections = _stepsConnections;
    }
    
    public String getReceipesName()
    {
        return receipesName;
    }
    
    public void setReceipesName(String receipesName)
    {
        this.receipesName = receipesName;
    }
    
    public ArrayList<Node> getSteps()
    {
        return steps;
    }
    
    public void setNodes(ArrayList<Node> nodes)
    {
        this.steps = nodes;
    }
    
    public ArrayList<NodesConnection> getStepsConnections()
    {
        return stepsConnections;
    }
    
    public void setStepsConnections(ArrayList<NodesConnection> stepsConnections)
    {
        this.stepsConnections = stepsConnections;
    }
}
