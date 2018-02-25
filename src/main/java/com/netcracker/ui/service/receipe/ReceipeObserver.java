/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe;

/**
 *
 * @author Artem
 */
public class ReceipeObserver implements ReceipeObserverInterface{

    private int observersId;
    private ReceipeSubjectInterface subject;
    private int drawingRecepsId;
    
    public ReceipeObserver(int idReceipe, ReceipeSubjectInterface subject)
    {
        drawingRecepsId = idReceipe;
        this.subject = subject.subscribe((ReceipeObserver)this);
    }
    
    public void UnsubscribeFromReceipeSubjectInterface()
    {
        subject.unsubscribe((ReceipeObserver)this);
        subject = null;
    }
    
    @Override
    public void updateReceipeGraf(Receipe receipe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId() {
        return observersId;
    }

    @Override
    public int getDrawingReceipesId() {
        return drawingRecepsId;
    }
    
}
