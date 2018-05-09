/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.addEdge;


/**
 *
 * @author Artem
 */
public class AddEdgeState{
    public String newEdgesFrom;
    public String newEdgesTo;
    public boolean stateReady = false;
    private int numberOfNonEmptyFeelds = 0;
    
    public AddEdgeState()
    {
        
    }
    
    public AddEdgeState(String _newEdgesFrom, String _newEdgesTo)
    {
        newEdgesFrom = _newEdgesFrom;
        newEdgesTo = _newEdgesTo;
    }
    
    public String getNewEdgesFrom()
    {
        return newEdgesFrom;
    }
    
    public void setNewEdgesFrom(String newEdgesFrom)
    {
        this.newEdgesFrom = newEdgesFrom;
        checkState();
    }
    
    public String getNewEdgesTo()
    {
        return newEdgesTo;
    }
    
    public void setNewEdgesTo(String newEdgesTo)
    {
        this.newEdgesTo = newEdgesTo;
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