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
public class AddEdgeState {
    public int newEdgesFrom;
    public int newEdgesTo;
    public boolean stateReady = false;
    private int numberOfNonEmptyFeelds = 0;
    
    public AddEdgeState()
    {
        
    }
    
    public AddEdgeState(int _newEdgesFrom, int _newEdgesTo)
    {
        newEdgesFrom = _newEdgesFrom;
        newEdgesTo = _newEdgesTo;
    }
    
    public int getNewEdgesFrom()
    {
        return newEdgesFrom;
    }
    
    public void setNewEdgesFrom(int newEdgesFrom)
    {
        this.newEdgesFrom = newEdgesFrom;
        checkState();
    }
    
    public int getNewEdgesTo()
    {
        return newEdgesTo;
    }
    
    public void setNewEdgesTo(int newEdgesTo)
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