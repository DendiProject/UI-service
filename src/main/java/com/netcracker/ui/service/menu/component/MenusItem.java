/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.menu.component;

import com.vaadin.ui.CustomLayout;
import java.util.ArrayList;

/**
 * Используется для общего описания всех компонентов меню
 * @author Artem
 */
abstract public class MenusItem extends CustomLayout{
    /** Имя компонента, чаще всего используется как надпись на компоненте*/
    public String name;
    /** id компонента, используется для связи с js*/
    public String id;
    /** обработчик события onClick*/
    private HandlerForClickingTheButton handlerForClickingTheButton;
    
    /**
     * Вызов обработчика события onClick
     * @see onEventClickDo
     */
    public void onEventClickDo(){
        handlerForClickingTheButton.onEventClickDo();
    }
    /**
     * Установка обработчика события onClick
     * @see setHandlerForClickingTheButton
     */
    public void setHandlerForClickingTheButton(HandlerForClickingTheButton handlerForClickingTheButton){
        this.handlerForClickingTheButton = handlerForClickingTheButton;
    }
}
