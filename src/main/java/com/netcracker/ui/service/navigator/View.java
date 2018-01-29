/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.navigator;

/**
 * Используется для работы с видами класса Navigator
 * @author Artem
 */
abstract public class View {
    public String name;
    
    public View(String name)
    {
        this.name = name;
    }
    abstract public void draw();
}
