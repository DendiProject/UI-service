/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.exception.beans.factory;

/**
 *
 * @author Artem
 */
public class NotFoundBean  extends BeansFactoryException{
    public NotFoundBean(){};
    public NotFoundBean(String msg){
        super(msg);
    };
    
    @Override
    public String toString()
    {
        return "Not found bean";
    }
}
