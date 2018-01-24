/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.menu.component;

import com.vaadin.ui.CustomLayout;
import java.util.ArrayList;

/**
 *
 * @author Artem
 * Класс формирует Custom
 */
public class MenusButton extends CustomLayout
{
    public MenusButton(String name, String id, ArrayList<String> subMenus)//name-название кнопки, id-идентификатор кнопки для обработки js, subMenus-подпункты меню
    {
        //В этом конструкторе нажатия выполняются только на подменю(из-за его наличия), нет смысла обрабатывать нажатия просто на кнопку
        String template = "<div><nav id='colorNav'><ul><li><a class='icon-home'>"+name+"</a><ul>";
        for(int i=0;i<subMenus.size();i++)
        {
            template+="<li><a   id='"+id+subMenus.get(i)+"Btn'>"+subMenus.get(i)+"</a></li>";
        }
        template+="</ul></li></ul></nav></div></div>";
        setTemplateContents(template);
    }
    
    public MenusButton(String name, String id)//name-название кнопки, id-идентификатор кнопки для обработки js
    {
        //К id дописываю "Btn", для идентификации в js
        String template = "<div id='"+id+"'><nav id='colorNav'><ul><li><a href='#' id='"+id+"Btn' class='icon-home'>"+name+"</a></li></ul></nav></div></div>";
        setTemplateContents(template);
    }
}
