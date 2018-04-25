/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events;

/**
 * Реализация паттерна стратегия для обработки событий
 * @author Artem
 */
public interface EventListener {
    abstract public void onEventDo();
}