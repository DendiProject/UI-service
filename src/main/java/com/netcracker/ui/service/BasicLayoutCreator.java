/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;

/**
 *
 * @author Artem
 */
public class BasicLayoutCreator extends BasicLayout
{
    public ResponsiveLayout main_layout;
    public ResponsiveLayout content_row_layout;
    
    public BasicLayoutCreator()
    {
        main_layout = create_basic_layout();
        //Создание строки для добавления контента
        ResponsiveRow content_row = main_layout.addRow();
        content_row.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        //Создание промежуточного custom макета для создания фона у контента
        CustomLayout intermediate_content_row_layout = new CustomLayout("ContentRowLayout");
        content_row.addColumn().withDisplayRules(1, 1, 10, 10).withComponent(intermediate_content_row_layout);
        //Создание адаптивного макета, для поддержки функционала Responsitive
        content_row_layout = new ResponsiveLayout();
        //Добавление адаптивного макета на промежуточный в блок content
        intermediate_content_row_layout.addComponent(content_row_layout,"content");
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