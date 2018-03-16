/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.beans.factory.basic.objects.interfaces;

import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;


/**
 *
 * @author Artem
 */
public interface Factory <T>{
    T getBean(Class beansClass) throws NotFoundBean;
    void addBean(Product bean);
}
