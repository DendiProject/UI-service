/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.addNode;


/**
 *
 * @author Artem
 */
public class AddNodeState{
    public String newNodesId;
    public String newNodesLable;
    public String newNodesDescription;
    public String newNodesImage;
    public int newNodesX;
    public int newNodesY;
    public boolean stateReady = false;
    private int numberOfNonEmptyFeelds = 0;
    public String userId;
    public String receipeId;
    
    public AddNodeState()
    {
        
    }
    
    public AddNodeState(String _newNodesId, String _newNodesLable, 
            String _newNodesImage, int _newNodesX, int _newNodesY, 
            String _userId, String _receipeId, String _newNodeDescription)
    {
        newNodesId = _newNodesId;
        newNodesLable = _newNodesLable;
        newNodesImage = _newNodesImage;
        newNodesX = _newNodesX;
        newNodesY = _newNodesY;
        stateReady = true;
        userId = _userId;
        receipeId = _receipeId;
        newNodesDescription = _newNodeDescription;
    }
    
    public String getUserId()
    {
        return newNodesId;
    }
    
    public void setUserId(String  userId)
    {
        this.userId = userId;
        checkState();
    }
    
    public String getReceipeId()
    {
        return newNodesId;
    }
    
    public void setReceipeId(String  receipeId)
    {
        this.receipeId = receipeId;
        checkState();
    }
    
    public String getNewNodesId()
    {
        return newNodesId;
    }
    
    public void setNewNodesId(String  newNodesId)
    {
        this.newNodesId = newNodesId;
        checkState();
    }
    
    public String getNewNodesLable()
    {
        return newNodesLable;
    }
    
    public void setNewNodesLable(String  newNodesLable)
    {
        this.newNodesLable = newNodesLable;
        checkState();
    }
    
    public String getNewNodesImage()
    {
        return newNodesImage;
    }
    
    public void setNewNodesImage(String  newNodesImage)
    {
        this.newNodesImage = newNodesImage;
        checkState();
    }
    
    public int getNewNodesX()
    {
        return newNodesX;
    }
    
    public void setNewNodesX(int  newNodesX)
    {
        this.newNodesX = newNodesX;
        checkState();
    }
    
    public int getNewNodesY()
    {
        return newNodesY;
    }
    
    public void setNewNodesY(int  newNodesY)
    {
        this.newNodesY = newNodesY;
        checkState();
    }
    
    private void checkState()
    {
        numberOfNonEmptyFeelds++;
        if(numberOfNonEmptyFeelds == 7)
        {
            stateReady = true;
        }
    }
}
