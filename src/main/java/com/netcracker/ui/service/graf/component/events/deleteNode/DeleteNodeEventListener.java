/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events.deleteNode;

/**
 * Реализация паттерна стратегия для обработки события удаления ноды
 * @author Artem
 */
public interface DeleteNodeEventListener {
    abstract public void onEventDo();
}
