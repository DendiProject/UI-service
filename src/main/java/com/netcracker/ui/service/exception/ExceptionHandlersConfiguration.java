/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.exception;

/**
 *
 * @author Artem
 */
public abstract class ExceptionHandlersConfiguration {
    public abstract void doOnExceptionAlreadyExists(String type);
    public abstract void doOnNoFoundException(String type);
}
