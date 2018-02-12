/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.navigator;

import java.util.ArrayList;

/**
 * Используется для навигации между видами
 * @author Artem
 */
public class Navigator {
    public ArrayList<View> Views;
    
    public Navigator()
    {
        Views = new ArrayList<View>();
    }
    
    public void drawView(String viewsName)
    {
        for(int i=0; i<Views.size(); i++)
        {
            if(Views.get(i).name.equals(viewsName))
            {
                Views.get(i).draw();
            }
        }
    }
}
