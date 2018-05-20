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
public class Node implements Serializable
{
    private String nodeId;
    private String description;
    private String pictureId;
    private HandlerForClickingTheNode handlerForClickingTheNode;
    private String label;
    
    public Node()
    {
        
    }
    
    public Node(String _nodeId, String _description, String _pictureId, 
            String _label)
    {
        nodeId = _nodeId;
        description = _description;
        pictureId = _pictureId;
        label = _label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
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
    
    public Node copySelf(){
        Node copy = new Node(nodeId, description, pictureId, label);
        return copy;
    }
}
