/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.jarektoro.responsivelayout.ResponsiveColumn;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.annotations.Theme;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Artem
 */
@Theme("centralViewTheme")
@SpringUI
public class UiServiceMainUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) 
    {
        setSizeFull();//Пользовательский интерфейс на весь экран
        //Базового макета
        BasicLayoutCreator main_layer = new BasicLayoutCreator();
        ResponsiveLayout main_layout = main_layer.main_layout;
        main_layout.setSizeFull();
        main_layout.setHeight("300%");
        setContent(main_layout);
        //Создание строки, для добавления конкретного контента на даную страницу
        ResponsiveRow slider_row = main_layer.content_row_layout.addRow();
        
        
        //Сосзадние custom слоя для добавления слайдера
        CustomLayout  slider_layout = new CustomLayout("SliderLayout");
        slider_row.addColumn().withDisplayRules(12, 12, 12, 12).withComponent(slider_layout);
    
        //Здесь можно разместить добавление рецептов либо фиксированно, например, топ 5, или
        //Задать количество по какому-либо другому параметру, например, по нажатию кнопки добавлять
        //еще несколько к имеющемуся списку
        for(int i=0;i<8;i++)
        {
            //Эту часть кода доделаю позже
            ResponsiveRow recipe_row = main_layer.content_row_layout.addRow();
            Image top_image = new Image();
            top_image.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/top1.png")));
            top_image.setHeight("70%");
            top_image.setWidth("100%");
            recipe_row.addColumn().withDisplayRules(2, 2, 2, 2).withComponent(top_image);
            recipe_row.addColumn().setWidth("3%");
            ResponsiveLayout recipe_content_layout = new ResponsiveLayout();
            recipe_row.addColumn().withDisplayRules(5, 5, 5, 5).withComponent(recipe_content_layout);
            recipe_content_layout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(new Label("Название блюда"));
            recipe_content_layout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(new Label("Автор: <Автор>"));
            ResponsiveLayout layout_with_informmation_of_recipes = new ResponsiveLayout();
            recipe_content_layout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(layout_with_informmation_of_recipes);
            ResponsiveRow information_row_about_recipes = layout_with_informmation_of_recipes.addRow();
            Button recepies_parts = new Button("Ингридиенты");
            information_row_about_recipes.addColumn().withDisplayRules(2, 2, 2, 2).withComponent(recepies_parts);
            information_row_about_recipes.addColumn().withDisplayRules(2, 2, 2, 2);
            information_row_about_recipes.addColumn().withDisplayRules(2, 2, 2, 2).withComponent(new Label("H 2"));
            information_row_about_recipes.addColumn().withDisplayRules(1, 1, 1, 1);
            information_row_about_recipes.addColumn().withDisplayRules(2, 2, 2, 2).withComponent(new Label("T 25"));
            Button add_to_my_list = new Button("Добавить в избранное");
            recipe_content_layout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(add_to_my_list);
        }
    }
}
