/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component;

import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import elemental.json.JsonArray;
import java.io.Serializable;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.graf.component.events.clickOnNode.ClickOnNodeEvent;
import com.netcracker.ui.service.graf.component.events.clickOnNode.ClickOnNodeEventListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Artem
 */
@JavaScript({"grafsLibrary.js", "graf-connector.js","https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js","https://cdnjs.cloudflare.com/ajax/libs/vis/4.19.1/vis.js","exampleUtil.js","http://code.jquery.com/jquery-1.9.1.js"})
public class Graf extends AbstractJavaScriptComponent {
    public interface ValueChangeListener extends Serializable {

        void valueChange();
    }
    ArrayList<ValueChangeListener> listeners
            = new ArrayList<ValueChangeListener>();
    
    //Массив слушателей события нажатия на любую ноду
    //Событие нажатия на кокретную ноду обрабатывается в этой ноде
    ArrayList<ClickOnNodeEventListener> clickOnNodeListeners;
    
    public Graf() {
        ClickOnNodeEvent cone = new ClickOnNodeEvent(this);
        
        addFunction("onClick", new JavaScriptFunction() {
            @Override
            public void call(JsonArray arguments) {                 
                cone.handleEvent(arguments);
            }
        });
    
        getState().nodes = new ArrayList<>();
        getState().edges = new ArrayList<>();
        clickOnNodeListeners = new ArrayList<>();
    }


    public void addValueChangeListener(
            ValueChangeListener listener) {
        listeners.add(listener);
    }
    
    
    public void setValue(TestClass newData) {
        getState().nodesId = newData.nodesId;
        getState().event = newData.event;
        getState().nodes = newData.getNodes();
        getState().edges = newData.edges;
    }
    
    
    public void addNode(String newNodesimageUrl, String newNodesLabel, 
            int newNodesId, HandlerForClickingTheNode handler) {
        Node node = new Node(newNodesimageUrl, newNodesId, newNodesLabel);
        node.setHandlerForClickingTheNode(handler);
        getState().nodes.add(node);
    }
    
    
    public void addNodesConnection(int idNodesConnectedFrom, 
            int idNodesConnectedTo) {
        Edge nodeConnection = new Edge(idNodesConnectedFrom, idNodesConnectedTo);
        getState().edges.add(nodeConnection);
    }
    
    
    public void setNodesCollection(ArrayList<Node> nodesCollection) {
        getState().nodes = nodesCollection;
    }
    
    
    public ArrayList<Node> getNodesCollection()
    {
        return getState().nodes;
    }
    
    
    public void setNodesConnections(ArrayList<Edge> nodesConnections) {
        getState().edges = nodesConnections;
    }
    
    
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
    
    
    public void addHandlerForClickingOnNode(ClickOnNodeEventListener handler) {
        clickOnNodeListeners.add(handler);
    }
    
    
    public void notifyClickOnNodeEventListeners()
    {
        for(int i=0; i<clickOnNodeListeners.size(); i++)
        {
            clickOnNodeListeners.get(i).onEventDo();
        }
    }
    
    
    @Override
    public GrafState getState() {
        return (GrafState) super.getState();
    }
}
