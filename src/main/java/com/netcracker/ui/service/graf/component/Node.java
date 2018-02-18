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
public class Node  implements Serializable
{
    private String newNodesimageUrl;
    private int newNodesId;
    private String newNodesLabel;
    private HandlerForClickingTheNode handlerForClickingTheNode;
    
    public Node()
    {
        
    }
    
    public Node(String _newNodesimageUrl, int _newNodesId, String _newNodesLabel)
    {
        newNodesimageUrl = _newNodesimageUrl;
        newNodesId = _newNodesId;
        newNodesLabel = _newNodesLabel;
    }
    
    
    public String getNewNodesimageUrl()
    {
        return newNodesimageUrl;
    }
    
    public void setNewNodesimageUrl(String newNodesimageUrl)
    {
        this.newNodesimageUrl = newNodesimageUrl;
    }
    
    public int getNewNodesId()
    {
        return newNodesId;
    }
    
    public void setNewNodesId(int newNodesId)
    {
        this.newNodesId = newNodesId;
    }
    
    public String getNewNodesLabel()
    {
        return newNodesLabel;
    }
    
    public void setNewNodesLabel(String newNodesLabel)
    {
        this.newNodesLabel = newNodesLabel;
    }
    
    
    
    
    
    /**
     * Вызов обработчика события onClick
     * @see onEventClickDo
     */
    public void onEventClickDo(){
        handlerForClickingTheNode.onEventClickDo();
    }
            
    public boolean checkHandlerState()//true, если он существует
    {
        if(this.handlerForClickingTheNode != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void setHandlerForClickingTheNode(HandlerForClickingTheNode handler)
    {
        this.handlerForClickingTheNode = handler;
    }
}

