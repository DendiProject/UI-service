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
public class ClickOnNodeState{
    public String nodesIdClick;
    public boolean stateReady = false;
    
    public ClickOnNodeState()
    {

    }
    
    public ClickOnNodeState(String nodesIdClick)
    {
        this.nodesIdClick = nodesIdClick;
    }
    
    public String getNodesIdClick()
    {
        return nodesIdClick;
    }
    
    public void setNodesIdClick(String nodesIdClick)
    {
        this.nodesIdClick = nodesIdClick;
        stateReady = true;
    }
}
