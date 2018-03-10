/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.exception.receipe.view;

/**
 *
 * @author Artem
 */
public class ConvertDataException extends ShortViewException{
    public ConvertDataException(){};
    public ConvertDataException(String msg){
        super(msg);
    };
    
    @Override
    public String toString()
    {
        return "Exception from IU-Service, ShortViewOfReceipe, DataConveter. "
                + "This object cannot be coerced into the format.";
    }
}
