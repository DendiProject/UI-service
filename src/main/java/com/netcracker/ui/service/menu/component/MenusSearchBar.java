/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.menu.component;

import com.vaadin.ui.CustomLayout;

/**
 * Используется для создания в меню строки поиска
 * @author Artem
 */
public class MenusSearchBar extends MenusItem
{
    public MenusSearchBar(String id, HandlerForClickingTheButton handler){
        setHandlerForClickingTheButton(handler);
        this.id = id;
        String template = "<div><nav id='colorNav'><ul><li><form><input type='text' placeholder='Искать здесь...'><button type='submit' id='"+id+"Btn'></button></form></li></ul></nav></div></div>";
        setTemplateContents(template);
    }
}
