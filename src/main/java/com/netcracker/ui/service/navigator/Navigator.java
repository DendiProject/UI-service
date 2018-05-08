/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.navigator;


import com.netcracker.ui.service.content.handler.CookieHandler;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.navigator.InvalidQueryFormat;
import com.netcracker.ui.service.exception.navigator.NoViewAvailable;
import com.netcracker.ui.service.exception.navigator.NotFound;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;

/**
 * Используется для навигации между видами
 * @author Artem
 */
public class Navigator {
    private ArrayList<View> views;
    private String mainPage;
    //private String newCurrentPage;
    private Page currentPage;
    
    public Navigator()
    {
        
    }
    
    public Navigator(ArrayList<View> views, String mainPage, Page page) throws NoViewAvailable, 
            InvalidQueryFormat, NotFound
    {
        this.views = views;
        this.mainPage = mainPage;
        currentPage = page;
        
        if(views.isEmpty())
        {
            //ДОБАВИТЬ СЮДА ВЫЗОВ ИСКОЛЮЧЕНИЯ
            Notification.show("Пустой набор видов");
        }
    }
    
    public void load() throws NoViewAvailable, InvalidQueryFormat, NotFound{
        drawView(mainPage);
    }
    
    public void navigateTo(String pageName) throws NoViewAvailable, 
            InvalidQueryFormat, NotFound
    {
        drawView(pageName);
    }
    
    private void drawView(String path) throws NoViewAvailable, InvalidQueryFormat, NotFound
    {
        //Если пуcто, то отобразить дефолтный вид 
        if(path == null)
        {
            CookieHandler ch = new CookieHandler();
            ch.guestEnter();
            load();
            return;
        }
        if(path.equals(""))
        {
            return;
        }
        if(views.size()>0)
        {
            //Иначе поиск вида
            //Получение имени страницы и параметров(если они есть)
            String[] pageNameAndParameters = path.split("\\?");
            LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
            if(pageNameAndParameters.length == 2)
            {
                String[] parametersWithKeys = pageNameAndParameters[1].split("&");
                for(int i=0; i<parametersWithKeys.length; i++)
                {
                    String[] parameter = parametersWithKeys[i].split("=");
                    if(pageNameAndParameters.length == 2)
                    {
                        parameters.add(parameter[0], parameter[1]);
                    }
                    else
                    {
                        throw new InvalidQueryFormat("Invalid query "
                                + "format.");
                    }
                }
            }
            if(pageNameAndParameters.length > 2)
            {
                throw new InvalidQueryFormat("Invalid query format.");
            }


            for(int i=0; i<views.size(); i++)
            {
                if(views.get(i).name.equals(pageNameAndParameters[0]))
                {
                    views.get(i).draw(parameters);
                    return;
                }
            }
            throw new NotFound("Page:" + pageNameAndParameters[0]
                    + "not found");
        }
        else
        {
            throw new NoViewAvailable("No view available, add them");
        }
    }
    
    public void addView(View view)
    {
        views.add(view);
    }
}
