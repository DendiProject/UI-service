/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.exception.importanceTypes;

/**
 *
 * @author Artem
 */
public abstract class BasicImportanceClass {
    public static InformationMessage informationMessage = new InformationMessage();
    public static WarningMessage warningMessage = new WarningMessage();
    public static ErrorMessage errorMessage = new ErrorMessage();
}
