/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.menu.component.EventHandlerOfTheForm;
import com.netcracker.ui.service.menu.component.Menu;
import com.netcracker.ui.service.menu.component.MenusButton;
import com.netcracker.ui.service.menu.component.MenusSearchBar;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Notification;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Artem
 */
public class BasicLayoutCreator
{
    public ResponsiveLayout main_layout;
    public ResponsiveLayout content_row_layout;
    public Menu menu;
    public BasicLayoutCreator() throws IOException
    {
        content_row_layout = new ResponsiveLayout();
        main_layout = new ResponsiveLayout();
        CustomLayout main_custom_layout = new CustomLayout("MainLayout");
        
        
        
        
        //Блок демонстрации работы с меню
        //Создаем экземпляр класса
        menu = new Menu();
        //Создаем пункты меню
        ArrayList<String> mainSubMenus = new ArrayList<>();
        mainSubMenus.add("Главная1");
        mainSubMenus.add("Главная2");
        MenusButton mainBtn = new MenusButton("Главная","idMain",mainSubMenus);
        
        MenusButton recepsBtn = new MenusButton("Рецепты","idRecept");
        MenusButton inBtn = new MenusButton("Вход","idIn");
        MenusSearchBar search = new MenusSearchBar("idSearch");
        
        //Добавляем пункты меню в меню
        menu.addItem(mainBtn);
        menu.addItem(recepsBtn);
        menu.addItem(search);
        menu.addItem(inBtn);
        
        //Добавляем меню на макет
        main_custom_layout.addComponent(menu,"navigate_row");
        //Добавление обработчика событий нажатия на клавиши меню
        menu.com.addValueChangeListener(new EventHandlerOfTheForm.ValueChangeListener() 
        {
            @Override
            public void valueChange() 
            {
                //Здесь по id можем понять, какая кнопка была нажата и сделать соответствующее действие
                Notification.show("Value: " + menu.com.getValue());
                //Например, вывести на экран id кнопки, запустить форму отсюда не выйдет
            }
        });
        
        
        
        
        
        
        
        
        
        main_layout.addComponent(main_custom_layout);
        main_custom_layout.addComponent(content_row_layout,"content_row");  
    }
}
/*Реализация BasicLayout, создает main_layout  и добавляет в него
content_row, далее используется именно content_row для добавления контента
Пример использования:
    BasicLayoutCreator main_layer = new BasicLayoutCreator();
    ResponsiveLayout main_layout = main_layer.main_layout;
    main_layout.setSizeFull();
    main_layout.setHeight("300%");
    setContent(main_layout);
    ResponsiveRow slider_row = main_layer.content_row_layout.addRow();*/