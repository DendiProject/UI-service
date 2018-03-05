/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects.interfaces;

/**
 *
 * @author Artem
 */
public interface StoreSubject {
    void handleNewData(Object object);//преобразование данных до нужного формата при помощи DataConverter
    void notifyObservers();//уведомление об изменившихся данных
    void subscribe(PresenterObserver observer);
    void unsubscribe(PresenterObserver observer);
}
