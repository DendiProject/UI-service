/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects.interfaces;

import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.exception.navigator.InternalServerError;
import com.netcracker.ui.service.exception.receipe.view.ConnectionErrorException;

/**
 *
 * @author Artem
 */
public interface Proxy {
    Boolean connect() throws InternalServerError;
    Object load() throws NotFoundBean,InternalServerError;//Выполнение запроса на бэкенд
}
