/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe;

import java.util.ArrayList;

/**
 *
 * @author Artem
 */
public class ReceipeSubject implements ReceipeSubjectInterface{

    private ArrayList<ReceipeObserverInterface> observers;
    private Receipe receipe;
    
    public ReceipeSubject()
    {
        observers = new ArrayList<>();
        receipe = ReceipeController.getRecipe(1);
    }
    
    @Override
    public ReceipeSubjectInterface subscribe(ReceipeObserverInterface observer) {
        observers.add(observer);
        return (ReceipeSubjectInterface)this;
    }

    @Override
    public void unsubscribe(ReceipeObserverInterface observer) {
        for(int i=0;i<observers.size();i++)
        {
            if(observers.get(i).getId() == observer.getId())
            {
                observers.remove(i);
                break;
            }
        }
    }
    
    public void getUpdatesForReceipes()
    {
        Receipe newReceipe = ReceipeController.getRecipe(1);
        newReceipe.stepsConnections.get(0).setIdNodesConnectedFrom(8);
        if(newReceipe.compareTo(receipe) == 0)
        {
            for(int i=0;i<observers.size();i++)
            {
                observers.get(i).updateReceipeGraf(newReceipe);
            }
        }
    }
    
}
