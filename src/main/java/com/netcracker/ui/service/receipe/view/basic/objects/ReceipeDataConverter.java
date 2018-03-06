/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.DataConverter;
import com.vaadin.ui.Notification;
import java.io.IOException;

/**
 *
 * @author Artem
 */
public class ReceipeDataConverter implements DataConverter{

    @Override
    public Receipe convert(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Receipe receipe = (Receipe) mapper.readValue(object.toString(),Receipe.class);
            return receipe;
        }
        catch (IOException ex) {
            
        }

        return null;
    }
    
}
