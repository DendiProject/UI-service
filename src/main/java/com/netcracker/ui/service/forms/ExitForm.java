/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.vaadin.server.Page;
import com.vaadin.ui.UI;

/**
 *
 * @author ArtemShevelyukhin
 */
public class ExitForm {

  public ExitForm(UI ui) {
    ui.getPage().setUriFragment("Main");
    Page q = Page.getCurrent();
    Page.getCurrent().reload();
  }
  
  
}
