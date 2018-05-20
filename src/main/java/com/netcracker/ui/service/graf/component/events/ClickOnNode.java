/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.events;

import com.netcracker.ui.service.graf.component.Node;

/**
 * Для создания стратегии обработки клика по ноде, вызывается при событии Click
 * по ЛЮБОЙ ноде
 * @author Artem
 */
public interface ClickOnNode {
    abstract public void onEventDo(Node node);
}
