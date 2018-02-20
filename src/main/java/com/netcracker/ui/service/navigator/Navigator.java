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
    
    public Navigator(Page newcurrentPage, ArrayList<View> newViews)
    {
        views = newViews;
        currentPage = newcurrentPage;
        
        String currentPath = currentPage.getUriFragment();
        if(views.size()>0)
        {
            if(currentPath != null && !currentPath.equals(""))
            {
                drawView(currentPath);
            }
            else
            {
                drawView(views.get(0).name);
            }
        }
        else
        {
            Notification.show("Пустой набор видов");
        }
        
        currentPage.addUriFragmentChangedListener(
            new Page.UriFragmentChangedListener() {
                public void uriFragmentChanged(
                    Page.UriFragmentChangedEvent source) {
                        if(views.size()>0)
                        {
                            if(source.getUriFragment() != null && !source.getUriFragment().equals(""))
                            {
                                drawView(source.getUriFragment());
                            }
                            else
                            {
                                drawView(views.get(0).name);
                            }
                        }
                        else
                        {
                            Notification.show("Пустой набор видов");
                        }
                }
            });
    }
    
    private void drawView(String viewsName)
    {
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
        currentPage.setUriFragment(path);
    }
}
