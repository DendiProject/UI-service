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
 * @author ivan
 */
@Theme("mytheme")
@SpringUI
public class UiServiceMainUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) 
    {
        setSizeFull();//Пользовательский интерфейс на весь экран
        /*try {
            
            CustomLayout cl = new CustomLayout(new ByteArrayInputStream("<div /><script>alert('Hi again!');</script>".getBytes()));
        setContent(cl);
        } catch (IOException ex) {
            Logger.getLogger(UiServiceMainUI.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        //Создание объектов 1 уровня
        ResponsiveLayout main_layout = new ResponsiveLayout();
        main_layout.setSizeFull();
        main_layout.setHeight("250%");
        setContent(main_layout);
        
        
        ResponsiveRow navigate_row = main_layout.addRow();
        ResponsiveRow offset_contents_from_the_cap = main_layout.addRow();
        ResponsiveRow content_row = main_layout.addRow();
        offset_contents_from_the_cap.setHeight("6%");//Отступ контента от шапки
        navigate_row.setDefaultComponentAlignment(Alignment.BOTTOM_CENTER);
        content_row.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        //Создание объектов 2 уровня

        /*CustomLayout  menu_layout1 = new CustomLayout("menulayout1");
        navigate_row.addColumn().withDisplayRules(1, 1, 1, 1).withComponent(menu_layout1);
        navigate_row.addColumn().withDisplayRules(1, 1, 1, 1);
        
        CustomLayout  menu_layout2 = new CustomLayout("menulayout2");
        navigate_row.addColumn().withDisplayRules(1, 1, 1, 1).withComponent(menu_layout2);
        navigate_row.addColumn().withDisplayRules(3, 3, 3, 3);
        
        CustomLayout  menu_layout3 = new CustomLayout("menulayout3");
        navigate_row.addColumn().withDisplayRules(2, 2, 2, 2).withComponent(menu_layout3);
        navigate_row.addColumn().withDisplayRules(1, 1, 1, 1);
        
        CustomLayout  menu_layout4 = new CustomLayout("menulayout4");
        navigate_row.addColumn().withDisplayRules(1, 1, 1, 1).withComponent(menu_layout4);*/
        
        CustomLayout navigate_row_layout = new CustomLayout("NavigateRowLayout");
        navigate_row.addColumn().withDisplayRules(12, 12, 12, 12).withComponent(navigate_row_layout);
        ResponsiveLayout navigate_row_layout2 = new ResponsiveLayout();
        navigate_row_layout.addComponent(navigate_row_layout2,"password");
        ResponsiveRow menus_row = navigate_row_layout2.addRow();
        menus_row.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        
        CustomLayout  menu_layout1 = new CustomLayout("menulayout1");
        menus_row.addColumn().withDisplayRules(1, 1, 1, 1).withComponent(menu_layout1);
        menus_row.addColumn().withDisplayRules(1, 1, 1, 1);
        
        CustomLayout  menu_layout2 = new CustomLayout("menulayout2");
        menus_row.addColumn().withDisplayRules(1, 1, 1, 1).withComponent(menu_layout2);
        menus_row.addColumn().withDisplayRules(3, 3, 3, 3);
        
        CustomLayout  menu_layout3 = new CustomLayout("menulayout3");
        menus_row.addColumn().withDisplayRules(2, 2, 2, 2).withComponent(menu_layout3);
        menus_row.addColumn().withDisplayRules(1, 1, 1, 1);
        
        CustomLayout  menu_layout4 = new CustomLayout("menulayout4");
        menus_row.addColumn().withDisplayRules(1, 1, 1, 1).withComponent(menu_layout4);
        
      
        
        //Объекты content_row
        ResponsiveLayout content_layout = new ResponsiveLayout();
        content_row.addColumn().withDisplayRules(10, 10, 10, 10).withComponent(content_layout);
        ResponsiveRow slider_row = content_layout.addRow();
        CustomLayout  slider_layout = new CustomLayout("sliderlayout");
        //Image sliders_image = new Image();
        //sliders_image.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/slide1.png")));
        //sliders_image.setHeight("70%");
        //sliders_image.setWidth("100%");
        //slider_row.addColumn().withDisplayRules(12, 12, 12, 12).withComponent(sliders_image);
        slider_row.addColumn().withDisplayRules(12, 12, 12, 12).withComponent(slider_layout);
    
        
        
        
        
        //Здесь можно разместить добавление рецептов либо фиксированно, например, топ 5, или
        //Задать количество по какому-либо другому параметру, например, по нажатию кнопки добавлять
        //еще несколько к имеющемуся списку
        for(int i=0;i<8;i++)
        {
            ResponsiveRow recipe_row = content_layout.addRow();
            Image top_image = new Image();
            top_image.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/top1.png")));
            top_image.setHeight("70%");
            top_image.setWidth("100%");
            recipe_row.addColumn().withDisplayRules(2, 2, 2, 2).withComponent(top_image);
            recipe_row.addColumn().setWidth("3%");
            ResponsiveLayout recipe_content_layout = new ResponsiveLayout();
            recipe_row.addColumn().withDisplayRules(5, 5, 5, 5).withComponent(recipe_content_layout);
            recipe_content_layout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(new Label("Recipes name"));
            recipe_content_layout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(new Label("Author"));
            recipe_content_layout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(new Label("Recipes parts    Numbers    Job time"));
            recipe_content_layout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(new Label("Add to my list"));
        }
    }
}
