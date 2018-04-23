/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component;

import com.netcracker.ui.service.graf.component.eventTypes.EventType;
import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import elemental.json.JsonArray;
import java.io.Serializable;
import java.util.ArrayList;
import com.netcracker.ui.service.graf.component.events.addEdge.AddEdgeEvent;
import com.netcracker.ui.service.graf.component.events.addEdge.AddEdgeEventListener;
import com.netcracker.ui.service.graf.component.events.addNode.AddNodeEvent;
import com.netcracker.ui.service.graf.component.events.addNode.AddNodeEventListener;
import com.netcracker.ui.service.graf.component.events.clickOnNode.ClickOnNodeEvent;
import com.netcracker.ui.service.graf.component.events.clickOnNode.ClickOnNodeEventListener;
import com.netcracker.ui.service.graf.component.events.deleteEdge.DeleteEdgeEvent;
import com.netcracker.ui.service.graf.component.events.deleteEdge.DeleteEdgeEventListener;
import com.netcracker.ui.service.graf.component.events.deleteNode.DeleteNodeEvent;
import com.netcracker.ui.service.graf.component.events.deleteNode.DeleteNodeEventListener;
import com.netcracker.ui.service.graf.component.events.editEdge.EditEdgeEvent;
import com.netcracker.ui.service.graf.component.events.editEdge.EditEdgeEventListener;

/**
 *
 * @author Artem
 */
@JavaScript({"grafsLibrary.js", "graf-connector.js","https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js","https://cdnjs.cloudflare.com/ajax/libs/vis/4.19.1/vis.js","http://code.jquery.com/jquery-1.9.1.js"})
public class Graf extends AbstractJavaScriptComponent {
    
    //Массивы слушателей событий
    ArrayList<ClickOnNodeEventListener> clickOnNodeListeners;
    ArrayList<AddNodeEventListener> addNodeListeners;
    ArrayList<AddEdgeEventListener> addEdgeListeners;
    ArrayList<EditEdgeEventListener> editEdgeListeners;
    ArrayList<DeleteEdgeEventListener> deleteEdgeListeners;
    ArrayList<DeleteNodeEventListener> deleteNodeListeners;
    
    public Graf() {
        //Конфигурирование цепочки обработчиков событий
        DeleteNodeEvent deleteNodeEvent = new DeleteNodeEvent(this);
        DeleteEdgeEvent deleteEdgeEvent = new DeleteEdgeEvent(this);
        deleteEdgeEvent.setNext(deleteNodeEvent);
        EditEdgeEvent editEdgeEvent = new EditEdgeEvent(this);
        editEdgeEvent.setNext(deleteEdgeEvent);
        AddEdgeEvent addEdgeEvent = new AddEdgeEvent(this);
        addEdgeEvent.setNext(editEdgeEvent);
        AddNodeEvent addNodeEvent = new AddNodeEvent(this);
        addNodeEvent.setNext(addEdgeEvent);
        ClickOnNodeEvent firstPartCheinOfHandlersEvent = new ClickOnNodeEvent(this);
        firstPartCheinOfHandlersEvent.setNext(addNodeEvent);
        
        addFunction("onClick", new JavaScriptFunction() {
            @Override
            public void call(JsonArray arguments) {                 
                firstPartCheinOfHandlersEvent.handleEvent(arguments);
            }
        });
    
        getState().nodes = new ArrayList<>();
        getState().edges = new ArrayList<>();
        setEvent(EventType.init);
        clickOnNodeListeners = new ArrayList<>();
    }
    
    //Установка флага события, он обрабатывается и затем сбрасывается 
    //на строне js
    public void setEvent(EventType event)
    {
        getState().event = event.getType();
    }
    
    //Добавление ноды с обработчиком клика, вызывается
    //через изменение стейта со стороны js
    public void addNode(String newNodesimageUrl, String newNodesLabel, 
            int newNodesId, HandlerForClickingTheNode handler) {
        Node node = new Node(newNodesimageUrl, newNodesId, newNodesLabel);
        node.setHandlerForClickingTheNode(handler);
        getState().nodes.add(node);
    }
    
    //Добавление ноды, вызывается через изменение стейта со стороны js
    public void addNode(String newNodesimageUrl, String newNodesLabel, 
            int newNodesId) {
        Node node = new Node(newNodesimageUrl, newNodesId, newNodesLabel);
        getState().nodes.add(node);
    }
    
    //Добавление связи, вызывается через изменение стейта со стороны js
    public void addEdge(int idNodesConnectedFrom, 
            int idNodesConnectedTo) {
        Edge nodeConnection = new Edge(idNodesConnectedFrom, idNodesConnectedTo);
        getState().edges.add(nodeConnection);
    }
    
    //Редактирование связи, вызывается через изменение стейта со стороны js
    public void editEdge(int editableEdgesOldIdFrom, int editableEdgesOldIdTo, 
            int editableEdgesNewIdFrom, int editableEdgesNewIdTo){
        for(int i = 0; i< getState().edges.size();i++)
        {
            if(getState().edges.get(i).getFrom() == editableEdgesOldIdFrom & 
                    getState().edges.get(i).getTo() == editableEdgesOldIdTo)
            {
                getState().edges.get(i).setFrom(editableEdgesNewIdFrom);
                getState().edges.get(i).setTo(editableEdgesNewIdTo);
                break;
            }
        }
    }
    
    //Удаление связи, вызывается через изменение стейта со стороны js
    public void deleteEdge(int deleteEdgeFrom, int deleteEdgeTo){
        for(int i = 0; i< getState().edges.size();i++)
        {
            if(getState().edges.get(i).getFrom() == deleteEdgeFrom & 
                    getState().edges.get(i).getTo() == deleteEdgeTo)
            {
                getState().edges.remove(i);
                setEvent(EventType.deleteEdge);
                break;
            }
        }
    }
    
    //Удаление ноды, вызывается через изменение стейта со стороны js
    public void deleteNode(int deleteNodesId){
        for(int i = 0; i< getState().nodes.size();i++)
        {
            if(getState().nodes.get(i).getId() == deleteNodesId)
            {
                //Удаление связей этой ноды
                for(int j=0; j<getState().edges.size();j++)
                {
                    if(getState().edges.get(j).getFrom() == deleteNodesId | 
                            getState().edges.get(j).getTo() == deleteNodesId){
                        getState().edges.remove(j);
                    }
                }
                getState().nodes.remove(i);
                break;
            }         
        }
    }
    
    public void setNodesCollection(ArrayList<Node> nodesCollection) {
        getState().nodes = nodesCollection;
    }
    
    public void setEdgesCollection(ArrayList<Edge> nodesConnections) {
        getState().edges = nodesConnections;
    }
    
    //Установка обработчика события клика по ноде на созданную ранее ноду
    public void setHandlerForClickingTheNode(int nodesId,
            HandlerForClickingTheNode handler) {
        for(int i=0; i<getState().nodes.size(); i++)
        {
            if(getState().nodes.get(i).getId() == nodesId)
            {
                getState().nodes.get(i).setHandlerForClickingTheNode(handler);
                break;
            }
            if(i == getState().nodes.size()-1)
            {
                throw new UnsupportedOperationException(
                        "Graf havent recipe with id= "+nodesId); 
            }
        }
    }
    
    //Добавление обработчика события клика по любой ноде
    public void addHandlerForClickingOnNode(ClickOnNodeEventListener handler) {
        clickOnNodeListeners.add(handler);
    }
    
    //Вызов соответствующих функций у слушателей события ClickOnNode
    public void notifyClickOnNodeEventListeners()
    {
        for(int i=0; i<clickOnNodeListeners.size(); i++)
        {
            clickOnNodeListeners.get(i).onEventDo();
        }
    }
    
    //Вызов соответствующих функций у слушателей события ClickOnNode по 
    //конкретной ноде
    public void notifyClickOnConcreteNodeEventListeners(int nodesIdClick)
    {
        //Если существует обработчик, который создан для 
        //этой ноды, то вызов его
        for(int i=0; i<getState().nodes.size(); i++)
        {
            if(getState().nodes.get(i).getId() == nodesIdClick)
            {        
                if(getState().nodes.get(i).checkHandlerState())
                {
                    getState().nodes.get(i).onEventClickDo();
                }
                break;
            }
        }
    }
    
    //Вызов соответствующих функций у слушателей события AddNode
    public void notifyAddNodeEventListeners()
    { 
        for(int i=0; i<addNodeListeners.size(); i++)
        {
            addNodeListeners.get(i).onEventDo();
        }
    }
    
    //Вызов соответствующих функций у слушателей события AddEdge
    public void notifyAddEdgeEventListeners()
    { 
        for(int i=0; i<addEdgeListeners.size(); i++)
        {
            addEdgeListeners.get(i).onEventDo();
        }
    }
    
    //Вызов соответствующих функций у слушателей события EditEdge
    public void notifyEditEdgeEventListeners()
    { 
        for(int i=0; i<editEdgeListeners.size(); i++)
        {
            editEdgeListeners.get(i).onEventDo();
        }
    }
    
    //Вызов соответствующих функций у слушателей события DeleteEdge
    public void notifyDeleteEdgeEventListeners()
    { 
        for(int i=0; i<deleteEdgeListeners.size(); i++)
        {
            deleteEdgeListeners.get(i).onEventDo();
        }
    }
    
    //Вызов соответствующих функций у слушателей события DeleteNode
    public void notifyDeleteNodeEventListeners()
    { 
        for(int i=0; i<deleteNodeListeners.size(); i++)
        {
            deleteNodeListeners.get(i).onEventDo();
        }
    }
    
    @Override
    public GrafState getState() {
        return (GrafState) super.getState();
    }
}
