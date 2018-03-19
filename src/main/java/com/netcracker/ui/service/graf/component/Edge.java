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
public class Edge  implements Serializable
{
    private int from;
    private int to;
    
    public Edge()
    {
        
    }
    
    public Edge(int from, int to)
    {
        this.from = from;
        this.to = to;
    }
    
    public int getFrom()
    {
        return from;
    }
    
    public void setFrom(int from)
    {
        this.from = from;
    }
    
    public int getTo()
    {
        return to;
    }
    
    public void setTo(int to)
    {
        this.to = to;
    }
}
