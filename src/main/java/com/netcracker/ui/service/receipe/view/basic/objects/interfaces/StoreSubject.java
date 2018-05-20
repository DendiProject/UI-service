/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects.interfaces;

import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.exception.navigator.InternalServerError;
import com.netcracker.ui.service.exception.receipe.view.ConvertDataException;
import com.netcracker.ui.service.receipe.view.basic.objects.Resource;
import java.util.List;

/**
 *
 * @author Artem
 */
public interface StoreSubject {
    void handleNewData(Object object) throws InternalServerError, NotFoundBean;//преобразование данных до нужного формата при помощи DataConverter
    void notifyObservers() throws InternalServerError, NotFoundBean;//уведомление об изменившихся данных
    void subscribe(PresenterObserver observer);
    void unsubscribe(PresenterObserver observer);
    void updateCurrentRecipesInResourses(Resource newResource, 
            boolean increment);
    void updateCurrentRecipesInIngredients(Resource newResource, 
            boolean increment);
    List<Resource> getCurrentRecipesInResourses();
    List<Resource> getCurrentRecipesInIngredients();
}
