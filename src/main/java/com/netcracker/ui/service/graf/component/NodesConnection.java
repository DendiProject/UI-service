/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component;

import java.io.Serializable;

/**
 *
 * @author Artem
 */
public class NodesConnection  implements Serializable
{
    private int idNodesConnectedFrom;
    private int idNodesConnectedTo;
    
    public NodesConnection()
    {
        
    }
    
    public NodesConnection(int idNodesConnectedFrom, int idNodesConnectedTo)
    {
        this.idNodesConnectedFrom = idNodesConnectedFrom;
        this.idNodesConnectedTo = idNodesConnectedTo;
    }
    
    public int getIdNodesConnectedFrom()
    {
        return idNodesConnectedFrom;
    }
    
    public void setIdNodesConnectedFrom(int idNodesConnectedFrom)
    {
        this.idNodesConnectedFrom = idNodesConnectedFrom;
    }
    
    public int getIdNodesConnectedTo()
    {
        return idNodesConnectedTo;
    }
    
    public void setIdNodesConnectedTo(int idNodesConnectedTo)
    {
        this.idNodesConnectedTo = idNodesConnectedTo;
    }
}
