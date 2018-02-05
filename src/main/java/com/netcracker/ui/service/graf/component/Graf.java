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
 * @author ivan
 */
@JavaScript({"mylibrary.js", "mycomponent-connector.js","https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js","https://cdnjs.cloudflare.com/ajax/libs/vis/4.19.1/vis.js"})
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
    public void addNode(String newNodesimageUrl, String newNodesLabel, int newNodesId) {
        Node node = new Node(newNodesimageUrl, newNodesId, newNodesLabel);
        getState().nodes.add(node);
    }
    public void addNodesConnection(int idNodesConnectedFrom, int idNodesConnectedTo) {
        NodesConnection nodeConnection = new NodesConnection(idNodesConnectedFrom, idNodesConnectedTo);
        getState().nodesConnections.add(nodeConnection);
    }

    @Override
    public GrafState getState() {
        return (GrafState) super.getState();
    }

}
