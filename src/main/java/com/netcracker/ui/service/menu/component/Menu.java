/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.menu.component;


import com.netcracker.ui.service.exception.menu.component.exception.MenuComponentException;
import com.vaadin.ui.CustomLayout;
import java.util.ArrayList;


/**
 * Используется для создания компоненты меню
 * @author Artem
 */
public class Menu extends CustomLayout{
    /** Список всех компонент меню*/
    public ArrayList<MenusItem> menusItems;
    
    /** 
     * Инициализация компоненты
     * @see Menu
     */
    public Menu(){
        EventHandlerOfTheMenu eventHandlerOfTheMenu = new EventHandlerOfTheMenu();
        menusItems = new ArrayList<MenusItem>();
        //Создал основной шаблон
        setTemplateContents("<div class='allign_navigate_row_components' id='allign_navigate_row_components'></div>");
        this.addComponent(eventHandlerOfTheMenu);
        
        //Добавление обработчика событий нажатия на клавиши меню
        eventHandlerOfTheMenu.addValueChangeListener(new EventHandlerOfTheMenu.ValueChangeListener() 
        {
            @Override
            public void valueChange() 
            {
                //Notification.show("Value: " + eventHandlerOfTheMenu.getValue());
                for(int i=0;i<menusItems.size();i++)
                {
                    if(menusItems.get(i).id.equals(eventHandlerOfTheMenu.getValue()))
                    {
                        menusItems.get(i).onEventClickDo();
                        break;
                    }
                    if(menusItems.get(i) instanceof MenusButton)
                    {
                        MenusButton intermediate = (MenusButton) menusItems.get(i);
                        if(intermediate.itemsSub != null)
                        {
                            for(int j=0;j<intermediate.itemsSub.size();j++)
                            {
                                if(intermediate.itemsSub.get(j).id.equals(eventHandlerOfTheMenu.getValue()))
                                {
                                    intermediate.itemsSub.get(j).onEventClickDo();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        });
    }
    
    /**
     * Добавление элементов в меню
     * @see addItem
     */
    public void addItem(MenusItem item) throws MenuComponentException{
        menusItems.add(item);
        if(item.getTemplateContents() == null)
        {
            throw new MenuComponentException("Please, use CustomLayout(InputStream templateStream)");
        }
        String thisTemplate = this.getTemplateContents();
        thisTemplate = thisTemplate.substring(0, (thisTemplate.length()-6));
        String updateTemplate = thisTemplate + item.getTemplateContents();
        setTemplateContents(updateTemplate);
    }
}
