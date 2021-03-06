/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects.interfaces;

import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.exception.navigator.InternalServerError;
import com.netcracker.ui.service.receipe.view.basic.objects.Receipe;
import com.netcracker.ui.service.receipe.view.basic.objects.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artem
 */
public interface PresenterObserver<T extends Receipe> {
    void load() throws NotFoundBean, InternalServerError;//Вызов функции load Proxi
    //Вызов функции обновления у хранилища
    void updateStore(Object newData) throws InternalServerError, NotFoundBean;
    int getId();//Возвращает id текущего presenter
    void updateView(T newData);
    void updateCurrentRecipesInResourses(Resource resource, boolean increment);
    List<Resource> getCurrentResources(boolean getIngredients);
}
