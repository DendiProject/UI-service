/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import elemental.json.JsonArray;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Artem
 */
@JavaScript({"grafsLibrary.js", "graf-connector.js","https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js","https://cdnjs.cloudflare.com/ajax/libs/vis/4.19.1/vis.js","exampleUtil.js"})
public class Graf extends AbstractJavaScriptComponent {

    public int clickedNodeIs;
    
    public interface ValueChangeListener extends Serializable {

        void valueChange();
    }

    public Graf() {
        addFunction("onClick", new JavaScriptFunction() {
            @Override
            public void call(JsonArray arguments) {
                setValue(arguments.getArray(0).getNumber(0));
                for (ValueChangeListener listener : listeners) {
                    listener.valueChange();
                }
            }
        });
        getState().nodes = new ArrayList<>();
        getState().nodesConnections = new ArrayList<>();
        this.addValueChangeListener(new Graf.ValueChangeListener() 
        {
            @Override
            public void valueChange() {
                for(int i=0;i<getState().nodes.size();i++)
                {
                    if(getState().nodes.get(i).getNewNodesId() == clickedNodeIs)
                    {
                        if(getState().nodes.get(i).checkHandlerState())
                        {
                            getState().nodes.get(i).onEventClickDo();
                        }
                        break;
                    }
                }
            }
        });
        //setValue("bla bla");
    }

    ArrayList<ValueChangeListener> listeners
            = new ArrayList<ValueChangeListener>();

    public void addValueChangeListener(
            ValueChangeListener listener) {
        listeners.add(listener);
    }
    public void setValue(double value) {
        clickedNodeIs = (int)value;
    }
    public void addNode(String newNodesimageUrl, String newNodesLabel, int newNodesId, HandlerForClickingTheNode handler) {
        Node node = new Node(newNodesimageUrl, newNodesId, newNodesLabel);
        node.setHandlerForClickingTheNode(handler);
        getState().nodes.add(node);
    }
    public void addNodesConnection(int idNodesConnectedFrom, int idNodesConnectedTo) {
        NodesConnection nodeConnection = new NodesConnection(idNodesConnectedFrom, idNodesConnectedTo);
        getState().nodesConnections.add(nodeConnection);
    }
    public void setNodesCollection(ArrayList<Node> nodesCollection)
    {
        getState().nodes = nodesCollection;
    }
    public void setNodesConnections(ArrayList<NodesConnection> nodesConnections)
    {
        getState().nodesConnections = nodesConnections;
    }
    public void setHandlerForClickingTheNode(int nodesId,HandlerForClickingTheNode handler)
    {
        for(int i=0; i<getState().nodes.size(); i++)
        {
            if(getState().nodes.get(i).getNewNodesId() == nodesId)
            {
                getState().nodes.get(i).setHandlerForClickingTheNode(handler);
                break;
            }
            if(i == getState().nodes.size()-1)
            {
                throw new UnsupportedOperationException("Graf havent recipe with id= "+nodesId); 
            }
        }
    }
    
    @Override
    public GrafState getState() {
        return (GrafState) super.getState();
    }

}
