/*
 * To change this license header, choose License Headers in Project PropertiesBean.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.beans.factory;

import com.netcracker.ui.service.beans.factory.basic.objects.interfaces.Product;
import com.netcracker.ui.service.components.Properties;

/**
 *
 * @author ArtemShevelyukhin
 */
public class PropertiesBean implements Product<Properties>{
  
  private Properties pb;

  public PropertiesBean() {
    setContent();
  }

  @Override
  public Properties getContent() {
    return pb;
  }

  @Override
  public void setContent() {
    pb = new Properties();
  }
  
  

}
