/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.beans.factory.basic.objects.interfaces;


/**
 *
 * @author Artem
 */
public interface Factory {
    Object getBean(Class beansClass);
    void addBean(Product bean);
}
