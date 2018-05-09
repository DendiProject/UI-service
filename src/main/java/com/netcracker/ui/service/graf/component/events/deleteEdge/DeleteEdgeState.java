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
    public String deleteEdgeFrom;
    public String deleteEdgeTo;
    public boolean stateReady = false;
    private int numberOfNonEmptyFeelds = 0;
    
    public DeleteEdgeState()
    {
        
    }
    
    public DeleteEdgeState(String _deleteEdgeFrom, String _deleteEdgeTo)
    {
        deleteEdgeFrom = _deleteEdgeFrom;
        deleteEdgeTo = _deleteEdgeTo;
    }
    
    public String getDeleteEdgeFrom()
    {
        return deleteEdgeFrom;
    }
    
    public void setDeleteEdgeFrom(String deleteEdgeFrom)
    {
        this.deleteEdgeFrom = deleteEdgeFrom;
        checkState();
    }
    
    public String getDeleteEdgeTo()
    {
        return deleteEdgeTo;
    }
    
    public void setDeleteEdgeTo(String deleteEdgeTo)
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
