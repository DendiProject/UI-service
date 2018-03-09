/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

/**
 *
 * @author Artem
 */
@SpringComponent
@UIScope
public class Greeter {
    public String sayHello() {
        return "Hello from bean " + toString();
    }
}
