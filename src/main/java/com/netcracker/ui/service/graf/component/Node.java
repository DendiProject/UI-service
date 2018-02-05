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
    public String newNodesimageUrl;
    public int newNodesId;
    public String newNodesLabel;
    
    public Node(String newNodesimageUrl, int newNodesId, String newNodesLabel)
    {
        this.newNodesimageUrl = newNodesimageUrl;
        this.newNodesId = newNodesId;
        this.newNodesLabel = newNodesLabel;
    }
}
