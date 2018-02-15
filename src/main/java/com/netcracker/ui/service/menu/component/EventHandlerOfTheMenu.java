/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.menu.component;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import elemental.json.JsonArray;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Используется для взаимодействия с js
 * @author Artem
 */
@JavaScript({"menusLibrary.js", "menu-connector.js"})
public class EventHandlerOfTheMenu extends AbstractJavaScriptComponent{

    public interface ValueChangeListener extends Serializable{
        void valueChange();
    }

    public EventHandlerOfTheMenu(){
        addFunction("onClick", new JavaScriptFunction() 
        {
            @Override
            public void call(JsonArray arguments) 
            {
                setValue(arguments.getString(0));
                for (ValueChangeListener listener : listeners) 
                {
                    listener.valueChange();
                }
            }
        });
    }

    ArrayList<ValueChangeListener> listeners = new ArrayList<ValueChangeListener>();

    public void addValueChangeListener(ValueChangeListener listener){
        listeners.add(listener);
    }
    
    public void setValue(String value){
        getState().buttonId = value;
    }
   
    public String getValue(){
        return getState().buttonId;
    }

    @Override
    public MenusState getState(){
        return (MenusState) super.getState();
    }
}
