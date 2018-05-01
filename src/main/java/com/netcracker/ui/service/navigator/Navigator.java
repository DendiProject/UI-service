/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.navigator;


import com.netcracker.ui.service.content.handler.CookieHandler;
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
    private Page currentPage;
    //private String newCurrentPage;
    private RecipientOfTheCurrentPage recipientOfTheCurrentPage;
    
    public Navigator()
    {
        
    }
    public Navigator(RecipientOfTheCurrentPage recipientOfTheCurrentPage, ArrayList<View> newViews)
    {
        views = newViews;
        this.recipientOfTheCurrentPage = recipientOfTheCurrentPage;
        currentPage = recipientOfTheCurrentPage.getCurrentPath();
        
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
                        currentPage = recipientOfTheCurrentPage.
                                getCurrentPath();
                        drawView(currentPage.getUriFragment());
                }
            });
    }
    
    private void drawView(String path)
    {
        if(views.size()>0)
        {
            //Если пуcто, то отобразить дефолтный вид (считаю дефолтным первый вид)
            if(path == null || path.equals(""))
            {
                path = views.get(0).name;
                CookieHandler ch = new CookieHandler();
                ch.guestEnter();
                return;
            }
            //Иначе поиск вида
            else
            {
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
                            //ВЫЗОВ ИСКЛЮЧЕНИЯ - НЕВЕРНЫЙ ФОРМАТ ЗАПРОСА
                        }
                    }
                }
                if(pageNameAndParameters.length > 2)
                {
                    //ВЫЗОВ ИСКЛЮЧЕНИЯ - НЕВЕРНЫЙ ФОРМАТ ЗАПРОСА
                }

                
                for(int i=0; i<views.size(); i++)
                {
                    if(views.get(i).name.equals(pageNameAndParameters[0]))
                    {
                        views.get(i).draw(parameters);
                        break;
                    }
                }
                //ДОБАВИТЬ СЮДА ПЕРЕХОД НА 404 СТРАНИЦУ
            }
        }
        else
        {
            //ДОБАВИТЬ ВЫЗОВ ИСКЛЮЧЕНИЯ-ОТСУТСТВИЕ ВИДОВ
        }
    }
    
    public void addView(View view)
    {
        views.add(view);
    }
}
