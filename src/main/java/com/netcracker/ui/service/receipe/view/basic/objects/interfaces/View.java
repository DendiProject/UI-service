/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects.interfaces;

import com.jarektoro.responsivelayout.ResponsiveLayout;

/**
 *
 * @author Artem
 */
public interface View {
    void reload();//Запрос из view на обновление
    void setNewViewsData(Object object);//Presenter устанавливает новую data
    ResponsiveLayout drawReceipe(ResponsiveLayout contentRowLayout);//отрисовка рецепта
}
