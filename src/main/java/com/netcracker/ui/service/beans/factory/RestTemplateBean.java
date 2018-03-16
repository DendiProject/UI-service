/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.beans.factory;

import com.netcracker.ui.service.beans.factory.basic.objects.interfaces.Product;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Artem
 */
public class RestTemplateBean implements Product<RestTemplate>{
    
    private RestTemplate content;
    
    public RestTemplateBean()
    {
        setContent();
    }
    
    @Override
    public void setContent() {
        content = new RestTemplate();
    }

    @Override
    public RestTemplate getContent() {
        return content;
    }
    
}