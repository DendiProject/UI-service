/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.exception.navigator;

/**
 * 401 ошибка
 * @author Artem
 */
public class Unauthorized extends NavigatorException{
    public Unauthorized(){};
    public Unauthorized(String msg){
        super(msg);
    };
    
    @Override
    public String toString()
    {
        return "Exception from IU-Service, Navigator. Unauthorized.";
    }
}