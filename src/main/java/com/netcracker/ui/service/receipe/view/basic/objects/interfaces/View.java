/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects.interfaces;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.exception.navigator.InternalServerError;
import com.netcracker.ui.service.exception.receipe.view.ConnectionErrorException;
import com.netcracker.ui.service.exception.receipe.view.ConvertDataException;
import com.netcracker.ui.service.forms.listeners.LoadFormListener;
import com.netcracker.ui.service.receipe.view.basic.objects.Receipe;

/**
 *
 * @author Artem
 */
public interface View<T extends Receipe> {
    void reload() throws NotFoundBean, InternalServerError;//Запрос из view на обновление
    void setNewViewsData(T object);//Presenter устанавливает новую data
    ResponsiveLayout drawReceipe(ResponsiveLayout contentRowLayout, 
            LoadFormListener listener);//отрисовка рецепта
}
