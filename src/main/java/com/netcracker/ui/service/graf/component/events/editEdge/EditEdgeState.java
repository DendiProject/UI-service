/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.editEdge;


/**
 *
 * @author Artem
 */
public class EditEdgeState{
    public String editableEdgesOldIdFrom;
    public String editableEdgesOldIdTo;
    public String editableEdgesNewIdFrom;
    public String editableEdgesNewIdTo;
    public boolean stateReady = false;
    private int numberOfNonEmptyFeelds = 0;
    
    public EditEdgeState()
    {
        
    }
    
    public EditEdgeState(String _editableEdgesOldIdFrom, String _editableEdgesOldIdTo, 
            String _editableEdgesNewIdFrom, String _editableEdgesNewIdTo)
    {
        editableEdgesOldIdFrom = _editableEdgesOldIdFrom;
        editableEdgesOldIdTo = _editableEdgesOldIdTo;
        editableEdgesNewIdFrom = _editableEdgesNewIdFrom;
        editableEdgesNewIdTo = _editableEdgesNewIdTo;
    }
    
    public String getEditableEdgesOldIdFrom()
    {
        return editableEdgesOldIdFrom;
    }
    
    public void setEditableEdgesOldIdFrom(String editableEdgesOldIdFrom)
    {
        this.editableEdgesOldIdFrom = editableEdgesOldIdFrom;
        checkState();
    }

    public String getEditableEdgesOldIdTo()
    {
        return editableEdgesOldIdTo;
    }
    
    public void setEditableEdgesOldIdTo(String editableEdgesOldIdTo)
    {
        this.editableEdgesOldIdTo = editableEdgesOldIdTo;
        checkState();
    }
    
    public String getEditableEdgesNewIdFrom()
    {
        return editableEdgesNewIdFrom;
    }
    
    public void setEditableEdgesNewIdFrom(String editableEdgesNewIdFrom)
    {
        this.editableEdgesNewIdFrom = editableEdgesNewIdFrom;
        checkState();
    }
    
    public String getEditableEdgesNewIdTo()
    {
        return editableEdgesNewIdTo;
    }
    
    public void setEditableEdgesNewIdTo(String editableEdgesNewIdTo)
    {
        this.editableEdgesNewIdTo = editableEdgesNewIdTo;
        checkState();
    }
    
    private void checkState()
    {
        numberOfNonEmptyFeelds++;
        if(numberOfNonEmptyFeelds == 4)
        {
            stateReady = true;
        }
    }
}

