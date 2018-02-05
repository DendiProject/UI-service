/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
/**
 *
 * @author eliza
 */
@Service
public class SimpleRestServise {
  /*  @Autowired
    private RestTemplate restTemplate;
    public String getMessage() {
        String result;
        try {
            String httpResult = restTemplate.getForObject("http://localhost:8082/v1/Receipe", String.class);
            result = "Message SUCCESS result: " + httpResult;
        } catch (HttpStatusCodeException e) {
            result = "Get FAILED with HttpStatusCode: " + e.getStatusCode() + "|" + e.getStatusText();
        } catch (RuntimeException e) {
            result = "Get FAILED\n" + ExceptionUtils.getFullStackTrace(e);
        }
        return result;
    }*/
}
