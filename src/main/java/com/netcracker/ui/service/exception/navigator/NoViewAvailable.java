/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.exception.navigator;

/**
 *
 * @author Artem
 */
public class NoViewAvailable extends NavigatorException{
    public NoViewAvailable(){};
    public NoViewAvailable(String msg){
        super(msg);
    };
    
    @Override
    public String toString()
    {
        return "Exception from IU-Service, Navigator. No view available, "
                + "add them";
    }
}