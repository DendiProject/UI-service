/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.buttonsClickListener.component;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import elemental.json.JsonArray;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Компонент, позволяющий задавать действия на клики по div из блока с контентом
 * @author Artem
 */
@JavaScript({"buttonsClickListenerLibrary.js", "buttonsClickListener-connector.js"})
public class ButtonsClickListener  extends AbstractJavaScriptComponent {

    ArrayList<ClickListener> listeners = new ArrayList<ClickListener>();
    
    public interface ValueChangeListener extends Serializable {

        void valueChange();
    }

    public ButtonsClickListener() {
        addFunction("onClick", new JavaScriptFunction() {
            @Override
            public void call(JsonArray arguments) {
                //Если существует слушатель div(кнопки) с 
                //id=arguments.getString(0), то вызов onEventDo()
                for(int i=0; i<listeners.size(); i++)
                {
                    if(listeners.get(i).getId().equals(arguments.getString(0)))
                    {
                        listeners.get(i).onEventDo();
                    }
                }
            }
        });
    }
    
    public void addButtonClickListener(ClickListener clickListener)
    {
        listeners.add(clickListener);
    }
    
    public String getValue(){
        return getState().buttonId;
    }

    @Override
    public ButtonsClickListenerState getState() {
        return (ButtonsClickListenerState) super.getState();
    }

}