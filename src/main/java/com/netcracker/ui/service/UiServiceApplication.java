package com.netcracker.ui.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.beans.factory.ButtonsClickListenerBean;
import com.netcracker.ui.service.beans.factory.ContentManadgerControllerBean;
import com.netcracker.ui.service.beans.factory.GMFacadeBean;
import com.netcracker.ui.service.beans.factory.ObjectMapperBean;
import com.netcracker.ui.service.beans.factory.PropertiesBean;
import com.netcracker.ui.service.beans.factory.RestTemplateBean;
import com.netcracker.ui.service.beans.factory.TokenStoreBean;
import com.netcracker.ui.service.exception.ConcreteException;
import com.netcracker.ui.service.exception.ConcreteExceptionHandler;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.exception.importanceTypes.BasicImportanceClass;
import com.netcracker.ui.service.exception.navigator.InternalServerError;
import com.netcracker.ui.service.exception.navigator.NotFound;
import com.netcracker.ui.service.exception.receipe.view.ConnectionErrorException;
import com.netcracker.ui.service.exception.receipe.view.ConvertDataException;
import com.netcracker.ui.service.exception.receipe.view.ShortViewException;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.jsonwebtoken.IncorrectClaimException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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
        bf.addBean(new GMFacadeBean("http://localhost:8083/"));
        bf.addBean(new ButtonsClickListenerBean());
        bf.addBean(new ObjectMapperBean());
        bf.addBean(new RestTemplateBean());
        bf.addBean(new ContentManadgerControllerBean());
        bf.addBean(new TokenStoreBean());
        bf.addBean(new PropertiesBean());
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
                        try {
                            throw new NotFound("Exception from IU-Service, "
                                    + "Navigator. Not found.");
                        } catch (NotFound ex1) {
                            ExceptionHandler.getInstance().
                                    runExceptionhandling(ex1);
                        }
                    }
        }, ShortViewException.class, "Данная страница недоступна, пожалуйста, "
                + "повторите попытку позже","This page is not available",
                BasicImportanceClass.errorMessage);
        ex.addException(shortViewException);

        ConcreteException resourceAccessException = 
                    new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling(Exception exception) {
                        try {
                            throw new InternalServerError("Exception from "
                                    + "IU-Service, Navigator. Internal server "
                                    + "error");
                        } catch (InternalServerError ex1) {
                            ExceptionHandler.getInstance().
                                    runExceptionhandling(ex1);
                        }
                    }
        }, ResourceAccessException.class, "Ресурс временно недоступен, "
                + "пожалуйста, повторите попытку позже",
                                "Resource temporarily unavailable",
                BasicImportanceClass.errorMessage);
        ex.addException(resourceAccessException);
        
        ConcreteException IOException = 
                new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling(Exception exception) {
                        
                    }
                }, IOException.class, "Произошла ошибка ввода-вывода",
                        "I/O exception of some sort has occurred",
                BasicImportanceClass.errorMessage);
        ex.addException(IOException);
        
        ConcreteException ProtocolException = 
                new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling(Exception exception) {
                        
                    }
                }, ProtocolException.class, "Произошла ошибка в протоколе связи",
                        "An error in the underlying protocol has occured",
                BasicImportanceClass.errorMessage);
        ex.addException(ProtocolException);
        
        ConcreteException MalformedURLException = 
                new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling(Exception exception) {
                        
                    }
                }, MalformedURLException.class, "Введен неверный URL ",
                        "A malformed URL has occurred."
                      + " Either no legal protocol could be found in a specification "
                      + " string or the string could not be parsed.",
                BasicImportanceClass.errorMessage);
        ex.addException(MalformedURLException);
        
        ConcreteException NullPointerException = 
                new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling(Exception exception) {
                        
                    }
                }, NullPointerException.class, "Произошла ошибка при обращении к объекту (NullPointerException)",
                        "NullPointerException",
                BasicImportanceClass.errorMessage);
        ex.addException(NullPointerException);
        
        ConcreteException UnsupportedEncodingException = 
                new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling(Exception exception) {
                        
                    }
                }, UnsupportedEncodingException.class, "Произошла ошибка при распозновании кодировки ",
                        "The Character Encoding is not supported.",
                BasicImportanceClass.errorMessage);
        ex.addException(UnsupportedEncodingException);
        
        ConcreteException InterruptedException = 
                new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling(Exception exception) {
                        
                    }
                }, InterruptedException.class, "Произошла ошибка при ожидании потока ",
                        "Еhread is waiting, sleeping, or otherwise occupied, and the thread is "
                                + " interrupted, either before or during the activity..",
                BasicImportanceClass.errorMessage);
        ex.addException(InterruptedException);
        
        ConcreteException NotFoundBean = 
                new ConcreteException(new ConcreteExceptionHandler() {
                    @Override
                    public void handling(Exception exception) {
                        
                    }
                }, NotFoundBean.class, "Произошла ошибка внутри приложения ",
                        "NotFoundBean exception",
                BasicImportanceClass.errorMessage);
        ex.addException(NotFoundBean);
 
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
