/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.custom.component;

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
@JavaScript({"mylibrary.js", "mycomponent-connector.js"})
public class CustomComponent extends AbstractJavaScriptComponent {

    public interface ValueChangeListener extends Serializable {

        void valueChange();
    }

    public CustomComponent() {
        addFunction("onClick", new JavaScriptFunction() {
            @Override
            public void call(JsonArray arguments) {
                setValue(arguments.getString(0));
                for (ValueChangeListener listener : listeners) {
                    listener.valueChange();
                }
            }
        });
        setValue("bla bla");
    }

    ArrayList<ValueChangeListener> listeners
            = new ArrayList<ValueChangeListener>();

    public void addValueChangeListener(
            ValueChangeListener listener) {
        listeners.add(listener);
    }
    public void setValue(String value) {
        getState().value = value;
    }

    public String getValue() {
        return getState().value;
    }

    @Override
    public CustomComponentState getState() {
        return (CustomComponentState) super.getState();
    }

}
