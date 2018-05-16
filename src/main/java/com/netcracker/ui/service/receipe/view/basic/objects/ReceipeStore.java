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

/**
 *
 * @author Artem
 */
public class ReceipeStore implements StoreSubject{

    private static DataConverter converter;
    private static Object currentData;
    private static ArrayList<PresenterObserver> observers;
    private ArrayList<Resource> recipesInResourses;
    private ArrayList<Resource> recipesInIngredients;
    private ArrayList<Resource> currentRecipesInResourses;
    private ArrayList<Resource> currentRecipesInIngredients;
    
    public ReceipeStore(DataConverter converter)
    {
        this.converter = converter;
        observers = new ArrayList<>();
    }
    
    @Override
    public void updateCurrentRecipesInResourses(Resource newResource){
        for(int i=0; i<currentRecipesInResourses.size();i++){
            if(currentRecipesInResourses.get(i).getName().equals(newResource.
                    getName())){
                currentRecipesInResourses.get(i).setResourceNumber(
                        currentRecipesInResourses.get(i).getResourceNumber()+
                                newResource.getResourceNumber());
                return;
            }
        }
        currentRecipesInResourses.add(newResource);
    }
    
    @Override
    public void updateCurrentRecipesInIngredients(Resource newResource){
        for(int i=0; i<currentRecipesInIngredients.size();i++){
            if(currentRecipesInIngredients.get(i).getName().equals(newResource.
                    getName())){
                currentRecipesInIngredients.get(i).setResourceNumber(
                        currentRecipesInIngredients.get(i).getResourceNumber()+
                                newResource.getResourceNumber());
                return;
            }
        }
        currentRecipesInIngredients.add(newResource);
    }
    
    public ArrayList<Resource> getCurrentRecipesInResourses() {
        return currentRecipesInResourses;
    }

    public void setCurrentRecipesInResourses(ArrayList<Resource> currentRecipesInResourses) {
        this.currentRecipesInResourses = currentRecipesInResourses;
    }

    public ArrayList<Resource> getCurrentRecipesInIngredients() {
        return currentRecipesInIngredients;
    }

    public void setCurrentRecipesInIngredients(ArrayList<Resource> currentRecipesInIngredients) {
        this.currentRecipesInIngredients = currentRecipesInIngredients;
    }

    public ArrayList<Resource> getRecipesInIngredients() {
        return recipesInIngredients;
    }

    public void setRecipesInIngredients(ArrayList<Resource> recipesInIngredients) {
        this.recipesInIngredients = recipesInIngredients;
    }

    public ArrayList<Resource> getRecipesInResourses() {
        return recipesInResourses;
    }

    public void setRecipesInResourses(ArrayList<Resource> recipesInResourses) {
        this.recipesInResourses = recipesInResourses;
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
