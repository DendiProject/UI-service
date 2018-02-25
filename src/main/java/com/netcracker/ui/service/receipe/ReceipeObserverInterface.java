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
public interface ReceipeObserverInterface {
    public void updateReceipeGraf(Receipe receipe);
    public int getId();
    public int getDrawingReceipesId();
}
