/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.deleteEdge;


/**
 *
 * @author Artem
 */
public class DeleteEdgeState{
    public int deleteEdgeFrom;
    public int deleteEdgeTo;
    public boolean stateReady = false;
    private int numberOfNonEmptyFeelds = 0;
    
    public DeleteEdgeState()
    {
        
    }
    
    public DeleteEdgeState(int _deleteEdgeFrom, int _deleteEdgeTo)
    {
        deleteEdgeFrom = _deleteEdgeFrom;
        deleteEdgeTo = _deleteEdgeTo;
    }
    
    public int getDeleteEdgeFrom()
    {
        return deleteEdgeFrom;
    }
    
    public void setDeleteEdgeFrom(int deleteEdgeFrom)
    {
        this.deleteEdgeFrom = deleteEdgeFrom;
        checkState();
    }
    
    public int getDeleteEdgeTo()
    {
        return deleteEdgeTo;
    }
    
    public void setDeleteEdgeTo(int deleteEdgeTo)
    {
        this.deleteEdgeTo = deleteEdgeTo;
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
