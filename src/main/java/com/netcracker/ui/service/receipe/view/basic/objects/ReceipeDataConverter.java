/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.beans.factory.ObjectMapperBean;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.DataConverter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.UI;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Artem
 */
public class ReceipeDataConverter implements DataConverter{
    
    @Override
    public Receipe convert(Object object) {   
        ObjectMapper mapper = (ObjectMapper)BeansFactory.getInstance().getBean(ObjectMapper.class);
        try {
            Receipe receipe = (Receipe) mapper.readValue(object.toString(),Receipe.class);
            return receipe;
        }
        catch (IOException ex) {
            
        }

        return null;
    }

}
