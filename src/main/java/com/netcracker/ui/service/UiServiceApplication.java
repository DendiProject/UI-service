package com.netcracker.ui.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.beans.factory.ContentManadgerControllerBean;
import com.netcracker.ui.service.beans.factory.ObjectMapperBean;
import com.netcracker.ui.service.beans.factory.RestTemplateBean;
import com.netcracker.ui.service.beans.factory.TokenStoreBean;
import com.netcracker.ui.service.exception.ConcreteException;
import com.netcracker.ui.service.exception.ConcreteExceptionHandler;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.importanceTypes.BasicImportanceClass;
import com.netcracker.ui.service.exception.receipe.view.ConnectionErrorException;
import com.netcracker.ui.service.exception.receipe.view.ConvertDataException;
import com.netcracker.ui.service.exception.receipe.view.ShortViewException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@EnableZuulProxy
@SpringBootApplication
public class UiServiceApplication {

    public static void main(String[] args) {
        BeansFactory bf = BeansFactory.getInstance();
        bf.addBean(new ObjectMapperBean());
        bf.addBean(new RestTemplateBean());
        bf.addBean(new ContentManadgerControllerBean());
        bf.addBean(new TokenStoreBean());
        ExceptionHandler ex = ExceptionHandler.getInstance(); 
        ConcreteException connectionErrorException = 
                new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                }, ConnectionErrorException.class, "message","moreAboutException",
                BasicImportanceClass.errorMessage);
        ex.addException(connectionErrorException);

        ConcreteException convertDataException = 
                new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                }, ConvertDataException.class, "message","moreAboutException",
                BasicImportanceClass.errorMessage);
        ex.addException(convertDataException);

        ConcreteException shortViewException = 
                        new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
        }, ShortViewException.class, "message","moreAboutException",
                BasicImportanceClass.errorMessage);
        ex.addException(shortViewException);

        ConcreteException resourceAccessException = 
                        new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
        }, ResourceAccessException.class, "message","moreAboutException",
                BasicImportanceClass.errorMessage);
        ex.addException(resourceAccessException);
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
