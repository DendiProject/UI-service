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

public abstract class BasicLayout
{
    protected ResponsiveLayout create_basic_layout()
    {
        //Создание объектов 1 уровня
        ResponsiveLayout main_layout = new ResponsiveLayout();//Базовый макет
        
        ResponsiveRow navigate_row = main_layout.addRow();//Строка для создания меню
        navigate_row.setDefaultComponentAlignment(Alignment.TOP_CENTER);

        //Добавление меню
        //Создание кастомного промежуточного layout для закрепления меню свверху страницы
        CustomLayout intermediate_navigate_row_layout = new CustomLayout("NavigateRowLayout");
        //Добавление кастомного промежуточного layout на страницу
        navigate_row.addColumn().withDisplayRules(1, 1, 10, 10).withComponent(intermediate_navigate_row_layout);
        //Создание адаптивного макета, для поддержки функционала Responsitive
        ResponsiveLayout navigate_row_layout = new ResponsiveLayout();
        //Добавление адаптивного макета на промежуточный в блок menu
        intermediate_navigate_row_layout.addComponent(navigate_row_layout,"menu");
        //Добавление строки на макет
        ResponsiveRow menus_row = navigate_row_layout.addRow();
        menus_row.setDefaultComponentAlignment(Alignment.TOP_LEFT);
        
        //Добавление колонок в строку menus_row для размещения пунктов меню и сроки поиска
        //Фактически меню состоит из 4 меню, которые включают по 1 компоненту, так как
        //исходный дизайн подразумевает половину пунктов разместить слева, а другую справа
        //Так же при такой компоновке появляется возможность использовать функционал Responsive
        //для адаптации меню под разную ширину экрана
        CustomLayout  menu_layout1 = new CustomLayout("PartOfTheMenuOnTheMain");
        menus_row.addColumn().withDisplayRules(1, 1, 1, 1);
        menus_row.addColumn().withDisplayRules(1, 1, 1, 1).withComponent(menu_layout1);
        menus_row.addColumn().withDisplayRules(1, 1, 1, 1);
        
        CustomLayout  menu_layout2 = new CustomLayout("PartOfTheMenuOnTheRecipes");
        menus_row.addColumn().withDisplayRules(1, 1, 1, 1).withComponent(menu_layout2);
        menus_row.addColumn().withDisplayRules(3, 3, 3, 3);
        
        CustomLayout  menu_layout3 = new CustomLayout("PartOfTheMenuWithSearchBar");
        menus_row.addColumn().withDisplayRules(2, 2, 2, 2).withComponent(menu_layout3);
        menus_row.addColumn().withDisplayRules(1, 1, 1, 1);
        
        CustomLayout  menu_layout4 = new CustomLayout("PartOfTheMenuOnTheEnter");
        menus_row.addColumn().withDisplayRules(1, 1, 1, 1).withComponent(menu_layout4);
        menus_row.addColumn().withDisplayRules(1, 1, 1, 1);
        
        return main_layout;
    }
}
/*Класс для создания основного макета, включает меню, фон, слой для добавления 
контента страницы*/