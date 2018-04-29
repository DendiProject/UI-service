package com.netcracker.ui.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.beans.factory.ContentManadgerControllerBean;
import com.netcracker.ui.service.beans.factory.ObjectMapperBean;
import com.netcracker.ui.service.beans.factory.RestTemplateBean;
import com.netcracker.ui.service.beans.factory.TokenStoreBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UiServiceApplication {

    public static void main(String[] args) {
        BeansFactory bf = BeansFactory.getInstance();
        bf.addBean(new ObjectMapperBean());
        bf.addBean(new RestTemplateBean());
        bf.addBean(new ContentManadgerControllerBean());
        bf.addBean(new TokenStoreBean());
        SpringApplication.run(UiServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder)
    {
      
        return builder.build();
    }
     
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        
        return objectMapper;
    }
}
