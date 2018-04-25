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
    public String receipesName;
    public ArrayList<Node> steps;
    public ArrayList<Edge> stepsConnections;
    
    public Receipe(){
        
    }
    
    public Receipe(String _receipesName, ArrayList<Node> _steps, ArrayList<Edge> _stepsConnections){
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
    
    public ArrayList<Edge> getStepsConnections()
    {
        return stepsConnections;
    }
    
    public void setStepsConnections(ArrayList<Edge> stepsConnections)
    {
        this.stepsConnections = stepsConnections;
    }

    @Override
    public int compareTo(Receipe t) {
        
        if(t.steps.size() != steps.size() | t.stepsConnections.size() != 
                stepsConnections.size() | !t.receipesName.equals(receipesName))
        {
            return 0;
        }
        
        for(int i=0;i<t.steps.size();i++)
        {
            if(t.steps.get(i).getId() != steps.get(i).getId())
            {
                return 0;
            }
        }
        
        for(int i=0;i<t.stepsConnections.size();i++)
        {
            if(t.stepsConnections.get(i).getTo() != 
                    stepsConnections.get(i).getTo() | 
                    t.stepsConnections.get(i).getFrom() != 
                    stepsConnections.get(i).getFrom())
            {
                return 0;
            }
        }
        
        
        return 1;
    }
}
