/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.eventTypes;

/**
 *
 * @author Artem
 */
public abstract class EventType {
    public static InitEventType init = new InitEventType();
    public static DeleteEdgeType deleteEdge = new DeleteEdgeType();
    public static AddEdgeType addEdge = new AddEdgeType();
    public static EditEdgeType editEdge = new EditEdgeType();
    public static AddNodeType addNode = new AddNodeType();
    public static DeleteNodeType deleteNode = new DeleteNodeType();
    
    public abstract String getType();
}
