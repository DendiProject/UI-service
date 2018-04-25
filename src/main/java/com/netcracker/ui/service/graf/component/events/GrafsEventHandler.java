/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events;

import elemental.json.JsonArray;

/**
 * Интерфейс для реализации ячейки из паттерна "цепочка обязанностей"
 * @author Artem
 */
public interface GrafsEventHandler {
    void handleEvent(JsonArray arguments);
}

