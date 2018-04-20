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
public class EditEdgeState {
    public int editableEdgesOldIdFrom;
    public int editableEdgesOldIdTo;
    public int editableEdgesNewIdFrom;
    public int editableEdgesNewIdTo;
    public boolean stateReady = false;
    private int numberOfNonEmptyFeelds = 0;
    
    public EditEdgeState()
    {
        
    }
    
    public EditEdgeState(int _editableEdgesOldIdFrom, int _editableEdgesOldIdTo, 
            int _editableEdgesNewIdFrom, int _editableEdgesNewIdTo)
    {
        editableEdgesOldIdFrom = _editableEdgesOldIdFrom;
        editableEdgesOldIdTo = _editableEdgesOldIdTo;
        editableEdgesNewIdFrom = _editableEdgesNewIdFrom;
        editableEdgesNewIdTo = _editableEdgesNewIdTo;
    }
    
    public int getEditableEdgesOldIdFrom()
    {
        return editableEdgesOldIdFrom;
    }
    
    public void setEditableEdgesOldIdFrom(int editableEdgesOldIdFrom)
    {
        this.editableEdgesOldIdFrom = editableEdgesOldIdFrom;
        checkState();
    }

    public int getEditableEdgesOldIdTo()
    {
        return editableEdgesOldIdTo;
    }
    
    public void setEditableEdgesOldIdTo(int editableEdgesOldIdTo)
    {
        this.editableEdgesOldIdTo = editableEdgesOldIdTo;
        checkState();
    }
    
    public int getEditableEdgesNewIdFrom()
    {
        return editableEdgesNewIdFrom;
    }
    
    public void setEditableEdgesNewIdFrom(int editableEdgesNewIdFrom)
    {
        this.editableEdgesNewIdFrom = editableEdgesNewIdFrom;
        checkState();
    }
    
    public int getEditableEdgesNewIdTo()
    {
        return editableEdgesNewIdTo;
    }
    
    public void setEditableEdgesNewIdTo(int editableEdgesNewIdTo)
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

