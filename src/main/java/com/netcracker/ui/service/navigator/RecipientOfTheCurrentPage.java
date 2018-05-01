/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.navigator;

import com.vaadin.server.Page;

/**
 * Позволяет компоненту Navigator при помощи стратегии получить текущий path
 * @author Artem
 */
public interface RecipientOfTheCurrentPage {
    Page getCurrentPath();
}
