/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.exception.navigator.InternalServerError;
import com.netcracker.ui.service.exception.receipe.view.ConvertDataException;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.DataConverter;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.PresenterObserver;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.StoreSubject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artem
 */
public class ReceipeStore implements StoreSubject{

    private static DataConverter converter;
    private static Object currentData;
    private static ArrayList<PresenterObserver> observers;
    private List<Resource> currentRecipesInResourses;
    private List<Resource> currentRecipesInIngredients;
    
    public ReceipeStore(DataConverter converter)
    {
        this.converter = converter;
        observers = new ArrayList<>();
    }
    
    @Override
    public void updateCurrentRecipesInResourses(Resource newResource, 
            boolean increment){
        for(int i=0; i<currentRecipesInResourses.size();i++){
            if(currentRecipesInResourses.get(i).getName().equals(newResource.
                    getName())){
                if(increment){
                    currentRecipesInResourses.get(i).setResourceNumber(
                            currentRecipesInResourses.get(i).
                                    getResourceNumber()+newResource.
                                            getResourceNumber());
                }
                else{
                    currentRecipesInResourses.get(i).setResourceNumber(
                            currentRecipesInResourses.get(i).
                                    getResourceNumber()-newResource.
                                            getResourceNumber());
                }
                return;
            }
        }
        currentRecipesInResourses.add(newResource);
    }
    
    @Override
    public void updateCurrentRecipesInIngredients(Resource newResource, 
            boolean increment){
        for(int i=0; i<currentRecipesInIngredients.size();i++){
            if(currentRecipesInIngredients.get(i).getName().equals(newResource.
                    getName())){
                if(increment){
                    currentRecipesInIngredients.get(i).setResourceNumber(
                            currentRecipesInIngredients.get(i).
                                    getResourceNumber()+newResource.
                                            getResourceNumber());
                }
                else{
                    currentRecipesInIngredients.get(i).setResourceNumber(
                            currentRecipesInIngredients.get(i).
                                    getResourceNumber()-newResource.
                                            getResourceNumber());
                }
                return;
            }
        }
        currentRecipesInIngredients.add(newResource);
    }
    
    public List<Resource> getCurrentRecipesInResourses() {
        return currentRecipesInResourses;
    }

    public void setCurrentRecipesInResourses(List<Resource> currentRecipesInResourses) {
        this.currentRecipesInResourses = currentRecipesInResourses;
    }

    public List<Resource> getCurrentRecipesInIngredients() {
        return currentRecipesInIngredients;
    }

    public void setCurrentRecipesInIngredients(List<Resource> currentRecipesInIngredients) {
        this.currentRecipesInIngredients = currentRecipesInIngredients;
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
    public void handleNewData(Object object) throws InternalServerError, NotFoundBean{
        currentData = object;
        Receipe initReceipe = converter.convert(currentData);
        currentRecipesInResourses = initReceipe.getResources();
        currentRecipesInIngredients = initReceipe.getIndredients();
        notifyObservers();
    }

    @Override
    public void notifyObservers() throws InternalServerError, NotFoundBean{
        for(int i=0; i<observers.size();i++)
        {
            observers.get(i).updateView(converter.convert(currentData));
        }
    }
    
}
