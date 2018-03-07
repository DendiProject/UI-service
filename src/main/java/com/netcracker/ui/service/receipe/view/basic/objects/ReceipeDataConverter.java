/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.DataConverter;
import com.netcracker.ui.service.UiServiceApplication;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Artem
 */
@Component
public class ReceipeDataConverter implements DataConverter{

    @Autowired
    private ObjectMapper objectMapper;
    
    @Override
    public Receipe convert(Object object) {
        //ObjectMapper mapper = new ObjectMapper();
        try {
            Receipe receipe = (Receipe) objectMapper.readValue(object.toString(),Receipe.class);
            return receipe;
        }
        catch (IOException ex) {
            
        }

        return null;
    }
    
}
