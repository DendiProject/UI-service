/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.clickOnNode;

/**
 *
 * @author Artem
 */
public class ClickOnNodeState {
    public int nodesIdClick;
    
    public ClickOnNodeState()
    {
        
    }
    
    public ClickOnNodeState(int _nodesIdClick)
    {
        nodesIdClick = _nodesIdClick;
    }
    
    public int getNodesId()
    {
        return nodesIdClick;
    }
    
    public void setNodesId(int  nodesId)
    {
        this.nodesIdClick = nodesId;
    }
}
