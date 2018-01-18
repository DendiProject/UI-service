/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.vaadin.ui.CustomLayout;

/**
 *
 * @author Artem
 */
public class BasicLayoutCreator
{
    public ResponsiveLayout main_layout;
    public ResponsiveLayout content_row_layout;
    
    public BasicLayoutCreator()
    {
        content_row_layout = new ResponsiveLayout();
        main_layout = new ResponsiveLayout();
        CustomLayout main_custom_layout = new CustomLayout("MainLayout");
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