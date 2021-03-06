/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.netcracker.ui.service.content.handler.CookieHandler;
import com.netcracker.ui.service.content.handler.JWTHandler;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.navigator.InternalServerError;
import com.netcracker.ui.service.exception.receipe.view.ConnectionErrorException;
import com.netcracker.ui.service.graf.component.gmfacade.GMFacade;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.Proxy;
import javax.servlet.http.Cookie;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Artem
 */

public class ReceipeProxy  implements Proxy{
    private String connectionUrl;
    private String userId;
    private String receipeId;
    boolean loadParallelGraf;
    boolean checkAutentification;
    
    public String getUserId() {
        return userId;
    }

    public String getReceipeId() {
        return receipeId;
    }
    
    @Override
    public boolean isLoadParallelGraf() {
        return loadParallelGraf;
    }
    
    public void setConfig(String connectionUrl, String userId, String receipeId, 
            boolean checkAutentification)
    {
        setConfig(connectionUrl, userId, receipeId, false, checkAutentification);
    }
    
    @Override
    public void setConfig(String connectionUrl, String userId, String receipeId, 
            boolean loadParallelGraf, boolean checkAutentification)
    {
        this.connectionUrl = connectionUrl;
        this.userId = userId;
        this.receipeId = receipeId;
        this.loadParallelGraf = loadParallelGraf;
        this.checkAutentification = checkAutentification;
    }
    
    //Проверка прав пользователя
    @Override
    public Boolean connect(){
        if(!checkAutentification){
            return true;
        }
        try
        {
            CookieHandler ch = new CookieHandler();
            JWTHandler jwth = new JWTHandler();
            Cookie userCookie = ch.getCookieByName("userInfo");
            //user = true  - это пользователь
            //uesr = false - это гость
            boolean user = jwth.checkUser(userCookie.getValue(), "test");
            if(user)
            {
                return true;
            }
            else
            {
                ConnectionErrorException ex1 = new 
                ConnectionErrorException("Access error: insufficient permissions "
                    + "or connection loss");
                InternalServerError exception = new InternalServerError("Exception from "
                                        + "IU-Service, Navigator. Internal server "
                                        + "error");
                exception.initCause(ex1);
                ExceptionHandler.getInstance().runExceptionhandling(exception);
                return false;
            }
        }
        catch(Exception ex)
        {
            ConnectionErrorException ex1 = new 
            ConnectionErrorException("Access error: insufficient permissions "
                + "or connection loss");
            InternalServerError exception = new InternalServerError("Exception from "
                                    + "IU-Service, Navigator. Internal server "
                                    + "error");
            exception.initCause(ex1);
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return false;
        }
    }

    //Загрузка с бэкенда данных о конкретном рецепте, используя конфигарцию, определенную через функцию connect()
    @Override
    public Object load() {
        if(connect())
        {
            GMFacade gm = new GMFacade(connectionUrl);
            if(loadParallelGraf){
                return gm.getGmGrafFacade().getParallelGraph(userId, receipeId);
            }
            else{
                return gm.getGmGrafFacade().getGraph(userId, receipeId);
            }
        }
        else
        {
            ConnectionErrorException ex1 = new 
            ConnectionErrorException("Access error: insufficient "
                    + "permissions or connection loss");
            InternalServerError exception = new InternalServerError(
                    "Exception from IU-Service, Navigator. Internal server "
                                    + "error");
            exception.initCause(ex1);
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return false;
        }
    }

    @Override
    public String getConnectionUrl() {
        return connectionUrl;
    }
}
