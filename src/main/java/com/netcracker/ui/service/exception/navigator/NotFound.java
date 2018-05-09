/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.exception.navigator;

/**
 * 404 ошибка
 * @author Artem
 */
public class NotFound extends NavigatorException{
    public NotFound(){};
    public NotFound(String msg){
        super(msg);
    };
    
    @Override
    public String toString()
    {
        return "Exception from IU-Service, Navigator. Not found.";
    }
}
