/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.exception;

import java.io.IOException;

/**
 *
 * @author Artem
 */
public class UiServiceException extends IOException{
    public UiServiceException(){};
    public UiServiceException(String msg){
        super(msg);
    };
    
    @Override
    public String toString()
    {
        return "Exception from IU-Service";
    }
}
