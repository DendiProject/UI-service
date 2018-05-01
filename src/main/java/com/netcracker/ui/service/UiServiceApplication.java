package com.netcracker.ui.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.beans.factory.ButtonsClickListenerBean;
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
        bf.addBean(new ButtonsClickListenerBean());
        bf.addBean(new ObjectMapperBean());
        bf.addBean(new RestTemplateBean());
        bf.addBean(new ContentManadgerControllerBean());
        bf.addBean(new TokenStoreBean());
        ExceptionHandler ex = ExceptionHandler.getInstance(); 
        ConcreteException connectionErrorException = 
                new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling(Exception exception) {
                        
                    }
                }, ConnectionErrorException.class, "Не удалось создать соединение."
                        + " Пожалуйста, повторите попытку позже.",
                        "Failed to create connection",
                BasicImportanceClass.errorMessage);
        ex.addException(connectionErrorException);

        ConcreteException convertDataException = 
                new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling(Exception exception) {
                        
                    }
                }, ConvertDataException.class, "Не удалось преобразовать "
                        + "входные данные. Ошибка со стороны сервера, "
                        + "пожалуйста, повторите попытку позже.",
                        "Unable to convert input data",
                BasicImportanceClass.errorMessage);
        ex.addException(convertDataException);

        ConcreteException shortViewException = 
                        new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling(Exception exception) {
                        
                    }
        }, ShortViewException.class, "Данная страница недоступна, пожалуйста, "
                + "повторите попытку позже","This page is not available",
                BasicImportanceClass.errorMessage);
        ex.addException(shortViewException);

        ConcreteException resourceAccessException = 
                        new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling(Exception exception) {
                        
                    }
        }, ResourceAccessException.class, "Ресурс временно недоступен, "
                + "пожалуйста, повторите попытку позже",
                                "Resource temporarily unavailable",
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
