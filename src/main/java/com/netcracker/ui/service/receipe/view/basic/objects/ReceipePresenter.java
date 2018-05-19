/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.exception.navigator.InternalServerError;
import com.netcracker.ui.service.exception.receipe.view.ConnectionErrorException;
import com.netcracker.ui.service.exception.receipe.view.ConvertDataException;
import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.View;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.PresenterObserver;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.Proxy;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.StoreSubject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artem
 */
public class ReceipePresenter implements PresenterObserver{

    private int observersId;
    private View view;
    private Proxy proxi;//Для 1 presenter может существовать только 1 proxi
    private StoreSubject storeSubject;//Presenter может подписываться на несколько subject(Пока для
                                      //данной задачи не увидел смысла подписываться сразу на 
                                      //несколько subject)
    public ReceipePresenter(Proxy proxi, StoreSubject store, View view)
    {
        this.proxi = proxi;
        this.storeSubject = store;
        this.view = view;
        store.subscribe((ReceipePresenter)this);
    }
    
    @Override
    public void updateCurrentRecipesInResourses(Resource resource, 
            boolean increment){
       if(resource.getIngredientOrResource().equals("resource")){
           storeSubject.updateCurrentRecipesInResourses(resource, increment);
       } 
       else{
           storeSubject.updateCurrentRecipesInIngredients(resource, increment);
       }
    }
    
    @Override
    public void load() throws NotFoundBean, InternalServerError{
        updateStore(proxi.load());
    }

    @Override
    public void updateStore(Object result) throws InternalServerError, NotFoundBean{
        storeSubject.handleNewData(result);
    }

    @Override
    public int getId() {
        return observersId;
    }

    @Override
    public void updateView(Receipe newData) {
        view.setNewViewsData(newData);
    }

    @Override
    public List<Resource> getCurrentResources(boolean getIngredients) {
        if(getIngredients){
            return storeSubject.getCurrentRecipesInIngredients();
        }
        else
        {
            return storeSubject.getCurrentRecipesInResourses();
        }
    }
}
