/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.deleteNode;


/**
 *
 * @author Artem
 */
public class DeleteNodeState{
    public String deleteNodesId;
    public boolean stateReady = false;
    private int numberOfNonEmptyFeelds = 0;
    
    public DeleteNodeState()
    {
        
    }
    
    public DeleteNodeState(String _deleteNodesId)
    {
        deleteNodesId = _deleteNodesId;
    }
    
    public String getDeleteNodesId()
    {
        return deleteNodesId;
    }
    
    public void setDeleteNodesId(String deleteNodesId)
    {
        this.deleteNodesId = deleteNodesId;
        checkState();
    }
    
    private void checkState()
    {
        numberOfNonEmptyFeelds++;
        if(numberOfNonEmptyFeelds == 1)
        {
            stateReady = true;
        }
    }
}
