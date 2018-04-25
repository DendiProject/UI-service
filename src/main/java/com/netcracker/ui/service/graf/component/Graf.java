/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component;

import com.netcracker.ui.service.graf.component.eventTypes.EventType;
import com.netcracker.ui.service.graf.component.events.EventListener;
import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import elemental.json.JsonArray;
import java.util.ArrayList;
import com.netcracker.ui.service.graf.component.events.addEdge.AddEdgeEvent;
import com.netcracker.ui.service.graf.component.events.addNode.AddNodeEvent;
import com.netcracker.ui.service.graf.component.events.clickOnNode.ClickOnNodeEvent;
import com.netcracker.ui.service.graf.component.events.deleteEdge.DeleteEdgeEvent;
import com.netcracker.ui.service.graf.component.events.deleteNode.DeleteNodeEvent;
import com.netcracker.ui.service.graf.component.events.editEdge.EditEdgeEvent;

/**
 *
 * @author Artem
 */
@JavaScript({"grafsLibrary.js", "graf-connector.js",
    "https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js",
    "https://cdnjs.cloudflare.com/ajax/libs/vis/4.19.1/vis.js",
    "http://code.jquery.com/jquery-1.9.1.js"})
public class Graf extends AbstractJavaScriptComponent {
    
    //Массивы слушателей событий
    private ArrayList<EventListener> clickOnNodeListeners;
    private ArrayList<EventListener> addNodeListeners;
    private ArrayList<EventListener> addEdgeListeners;
    private ArrayList<EventListener> editEdgeListeners;
    private ArrayList<EventListener> deleteEdgeListeners;
    private ArrayList<EventListener> deleteNodeListeners;
    
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
    
    //Установка флага события, он обрабатывается на js и затем сбрасывается 
    //на строне js. Через эту функцию осуществляется манипулирование графом со 
    //стороны java
    public void setEvent(EventType event, String eventStateInJSONFormat)
    {
        getState().event = event.getType();
        getState().eventStateInJSONFormat = eventStateInJSONFormat;
    }
    public void setEvent(EventType event)
    {
        getState().event = event.getType();
    }
    
    
    public ArrayList<EventListener> getClickOnNodeListeners() {
        return clickOnNodeListeners;
    }

    public ArrayList<EventListener> getAddNodeListeners() {
        return addNodeListeners;
    }

    public ArrayList<EventListener> getAddEdgeListeners() {
        return addEdgeListeners;
    }

    public ArrayList<EventListener> getEditEdgeListeners() {
        return editEdgeListeners;
    }

    public ArrayList<EventListener> getDeleteEdgeListeners() {
        return deleteEdgeListeners;
    }

    public ArrayList<EventListener> getDeleteNodeListeners() {
        return deleteNodeListeners;
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
    
    //Удаление ноды из стейта на стороне js после ее удаления с рабочего поля 
    //на стороне js, вызывается через изменение стейта со стороны js
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
    public void addHandlerForClickingOnNode(EventListener handler) {
        clickOnNodeListeners.add(handler);
    }
    
    //Вызов соответствующих функций у слушателей события ClickOnNode
    public void notifyEventListeners(ArrayList<EventListener> listeners)
    {
        for(int i=0; i<listeners.size(); i++)
        {
            listeners.get(i).onEventDo();
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
    
    @Override
    public GrafState getState() {
        return (GrafState) super.getState();
    }
}
