/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.exception.beans.factory;

import com.netcracker.ui.service.exception.UiServiceException;

/**
 *
 * @author Artem
 */
public class BeansFactoryException extends UiServiceException{
    public BeansFactoryException(){};
    public BeansFactoryException(String msg){
        super(msg);
    };
    
    @Override
    public String toString()
    {
        return "Exception from IU-Service, Beans factory";
    }
}
