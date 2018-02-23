/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.navigator;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import java.util.ArrayList;

/**
 * Используется для навигации между видами
 * @author Artem
 */
public class Navigator {
    private ArrayList<View> views;
    Page currentPage;
    String newCurrentPage;
    public Navigator()
    {
        
    }
    public Navigator(Page newcurrentPage, ArrayList<View> newViews)
    {
        views = newViews;
        currentPage = newcurrentPage;
        
        String currentPath = currentPage.getUriFragment();
        if(views.size()>0)
        {
            drawView(currentPath);
        }
        else
        {
            Notification.show("Пустой набор видов");
        }
        
        currentPage.addUriFragmentChangedListener(
            new Page.UriFragmentChangedListener() {
                public void uriFragmentChanged(
                    Page.UriFragmentChangedEvent source) {
                        drawView(newCurrentPage);
                }
            });
    }
    
    private void drawView(String viewsName)
    {
        if(views.size()>0)
        {
            //Если путо, то отобразить дефолтный вид (считаю дефолтным первый вид)
            if(viewsName == null || viewsName.equals(""))
            {
                viewsName = views.get(0).name;
            }
        }
        
        for(int i=0; i<views.size(); i++)
        {
            if(views.get(i).name.equals(viewsName))
            {
                views.get(i).draw();
                break;
            }
        }
    }
    
    public void addView(View view)
    {
        views.add(view);
    }
    
    public void navigateTo(String path)
    {
        newCurrentPage = path;
        currentPage.setUriFragment(path);
    }
}
