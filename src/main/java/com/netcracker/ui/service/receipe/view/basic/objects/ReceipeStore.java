/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.DataConverter;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.PresenterObserver;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.StoreSubject;
import java.util.ArrayList;

/**
 *
 * @author Artem
 */
public class ReceipeStore implements StoreSubject{

    private DataConverter converter;
    private Object currentData;
    private ArrayList<PresenterObserver> observers;
    
    public ReceipeStore(DataConverter converter)
    {
        this.converter = converter;
        observers = new ArrayList<>();
    }
    
    @Override
    public void subscribe(PresenterObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(PresenterObserver observer) {
        for(int i=0;i<observers.size();i++)
        {
            if(observers.get(i).getId() == observer.getId())
            {
                observers.remove(i);
                break;
            }
        }
    }
    
    @Override
    public void handleNewData(Object object) {
        currentData = object;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for(int i=0; i<observers.size();i++)
        {
            observers.get(i).updateView(converter.convert(currentData));
        }
    }
    
}
