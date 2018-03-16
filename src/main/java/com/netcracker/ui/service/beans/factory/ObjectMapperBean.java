/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.beans.factory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.basic.objects.interfaces.Product;

/**
 *
 * @author Artem
 */
public class ObjectMapperBean implements Product<ObjectMapper>{
    
    private ObjectMapper content;
    
    public ObjectMapperBean()
    {
        setContent();
    }
    
    @Override
    public void setContent() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        content = objectMapper;
    }

    @Override
    public ObjectMapper getContent() {
        return content;
    }
    
}
