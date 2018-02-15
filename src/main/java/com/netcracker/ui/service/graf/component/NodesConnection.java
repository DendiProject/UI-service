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
    public int idNodesConnectedFrom;
    public int idNodesConnectedTo;
    
    public NodesConnection(int idNodesConnectedFrom, int idNodesConnectedTo)
    {
        this.idNodesConnectedFrom = idNodesConnectedFrom;
        this.idNodesConnectedTo = idNodesConnectedTo;
    }
}
