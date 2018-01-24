/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.menu.component;


import com.vaadin.ui.CustomLayout;
import java.io.IOException;


/**
 *
 * @author Artem
 */
public class Menu extends CustomLayout
{
    public EventHandlerOfTheForm com;
    public Menu() throws IOException
    {
        com = new EventHandlerOfTheForm();
        //Создал основной шаблон, пока это выравнивание по левому краю на всю ширину с равномерным распределением
        setTemplateContents("<div class='left_allign_navigate_row_components' id='left_allign_navigate_row_components'></div>");
        this.addComponent(com);
    }
       
    public void addItem(CustomLayout item) throws IOException
    {
        if(item.getTemplateContents() == null)
        {
            throw new UnsupportedOperationException("Not supported yet. Please, use CustomLayout(InputStream templateStream)");
        }
        String thisTemplate = this.getTemplateContents();
        thisTemplate = thisTemplate.substring(0, (thisTemplate.length()-6));
        String updateTemplate = thisTemplate + item.getTemplateContents();
        setTemplateContents(updateTemplate);
    }
}
