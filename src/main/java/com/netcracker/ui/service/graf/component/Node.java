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
    private String image;
    private int id;
    private String label;
    private HandlerForClickingTheNode handlerForClickingTheNode;
    
    public Node()
    {
        
    }
    
    public Node(String _image, int _id, String _label)
    {
        image = _image;
        id = _id;
        label = _label;
    }
    
    
    public String getImage()
    {
        return image;
    }
    
    public void setImage(String image)
    {
        this.image = image;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getLabel()
    {
        return label;
    }
    
    public void setLabel(String label)
    {
        this.label = label;
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

