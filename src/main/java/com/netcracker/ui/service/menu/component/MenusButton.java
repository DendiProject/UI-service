/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.menu.component;

import com.vaadin.ui.CustomLayout;
import java.util.ArrayList;

/**
 * Используется для создания кнопки меню
 * @author Artem
 */
public class MenusButton extends MenusItem{
    /** Список подпунктов текущей кнопки, если кнопка 1-уровневая, то он равен null*/
    public ArrayList<MenusButton> itemsSub;
    
    /**
     * Добавление многоуровневой кнопки в меню
     * @see MenusButton
     */
    public MenusButton(String name, String id, HandlerForClickingTheButton handler, ArrayList<MenusButton> itemsSub){
        setHandlerForClickingTheButton(handler);
        this.id = id;
        this.name = name;
        this.itemsSub = itemsSub;
        String template = "<div><nav id='colorNav'><ul><li><a  id='"+id+"Btn' class='icon-home'>"+name+"</a><ul>";
        for(int i=0;i<itemsSub.size();i++)
        {
            template+="<li><a   id='"+itemsSub.get(i).id+"Btn'>"+itemsSub.get(i).name+"</a></li>";
        }
        template+="</ul></li></ul></nav></div></div>";
        setTemplateContents(template);
    }
    
    /**
     * Добавление одноуровенвой кнопки в меню
     * @see MenusButton
     */
    public MenusButton(String name, String id, HandlerForClickingTheButton handler){
        setHandlerForClickingTheButton(handler);
        this.id = id;
        this.name = name;
        //К id дописываю "Btn", для идентификации в js
        String template = "<div id='"+id+"'><nav id='colorNav'><ul><li><a href='#' id='"+id+"Btn' class='icon-home'>"+name+"</a></li></ul></nav></div></div>";
        setTemplateContents(template);
    }
}
